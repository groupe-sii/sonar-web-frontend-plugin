package fr.sii.sonar.web.client.core;

import java.io.File;
import java.io.FileNotFoundException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sonar.api.batch.Sensor;
import org.sonar.api.batch.SensorContext;
import org.sonar.api.component.ResourcePerspectives;
import org.sonar.api.config.Settings;
import org.sonar.api.resources.Project;
import org.sonar.api.rules.RuleFinder;
import org.sonar.api.scan.filesystem.ModuleFileSystem;

import fr.sii.sonar.web.client.core.domain.report.WebClientReport;
import fr.sii.sonar.web.client.core.exception.ProviderException;
import fr.sii.sonar.web.client.core.provider.Provider;
import fr.sii.sonar.web.client.core.provider.WebClientReportProvider;
import fr.sii.sonar.web.client.core.save.WebClientReportSaver;

public class ReportSensor implements Sensor {

	private static final Logger LOG = LoggerFactory.getLogger(ReportSensor.class);

	private final Settings settings;
	private final ResourcePerspectives resourcePerspective;
	private final RuleFinder ruleFinder;
	private final ModuleFileSystem filesystem;
	private final WebClientConstants constants;


	/**
	 * Use of IoC to get Settings
	 */
	public ReportSensor(WebClientConstants constants, Settings settings, RuleFinder ruleFinder, ModuleFileSystem filesystem, ResourcePerspectives resourcePerspective) {
		super();
		this.constants = constants;
		this.settings = settings;
		this.ruleFinder = ruleFinder;
		this.resourcePerspective = resourcePerspective;
		this.filesystem = filesystem;
	}

	public boolean shouldExecuteOnProject(Project project) {
		try {
			File reportFile = getReportFile(project);
			return reportFile.exists();
		} catch(Exception e) {
			LOG.error("The report file doesn't exist => skip the sonar plugin", e.getMessage());
			return false;
		}
	}

	protected File getReportFile(Project project) {
		return new File(filesystem.baseDir(), settings.getString(constants.getReportPathKey()));
	}

	public void analyse(Project project, SensorContext sensorContext) {
		try {
			File reportFile = getReportFile(project);
			Provider<WebClientReport> provider = new WebClientReportProvider(reportFile);
			WebClientReport report = provider.get();
			WebClientReportSaver saver = new WebClientReportSaver(constants, ruleFinder, filesystem, resourcePerspective, settings);
			saver.save(report, project, sensorContext);
		} catch (FileNotFoundException e) {
			LOG.error(e.getMessage());
		} catch (ProviderException e) {
			LOG.error(e.getMessage());
		}
	}

	@Override
	public String toString() {
		return getClass().getSimpleName();
	}

}
