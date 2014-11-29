package fr.sii.sonar.report.core;

import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sonar.api.batch.Sensor;
import org.sonar.api.batch.SensorContext;
import org.sonar.api.component.ResourcePerspectives;
import org.sonar.api.config.Settings;
import org.sonar.api.resources.Project;
import org.sonar.api.rules.RuleFinder;
import org.sonar.api.scan.filesystem.ModuleFileSystem;

import fr.sii.sonar.report.core.domain.report.Report;
import fr.sii.sonar.report.core.exception.CreateException;
import fr.sii.sonar.report.core.exception.ProviderException;
import fr.sii.sonar.report.core.factory.ProviderFactory;
import fr.sii.sonar.report.core.factory.SaverFactory;
import fr.sii.sonar.report.core.provider.Provider;
import fr.sii.sonar.report.core.save.Saver;

public abstract class ReportSensor implements Sensor {

	private static final Logger LOG = LoggerFactory.getLogger(ReportSensor.class);

	private final PluginContext pluginContext;
	private final ProviderFactory<Report> providerFactory;
	private final SaverFactory<Report> saverFactory;

	/**
	 * Use of IoC to get Settings
	 */
	public ReportSensor(ReportConstants constants, Settings settings, RuleFinder ruleFinder, ModuleFileSystem filesystem, ResourcePerspectives resourcePerspective, ProviderFactory<Report> providerFactory, SaverFactory<Report> saverFactory) {
		super();
		this.pluginContext = new PluginContext(settings, resourcePerspective, ruleFinder, filesystem, constants);
		this.providerFactory = providerFactory;
		this.saverFactory = saverFactory;
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
		return new File(pluginContext.getFilesystem().baseDir(), pluginContext.getSettings().getString(pluginContext.getConstants().getReportPathKey()));
	}

	public void analyse(Project project, SensorContext sensorContext) {
		try {
			File reportFile = getReportFile(project);
			Provider<Report> provider = providerFactory.create(reportFile);
			Saver<Report> saver = saverFactory.create(pluginContext);
			saver.save(provider.get(), project, sensorContext);
		} catch (ProviderException e) {
			LOG.error(e.getMessage());
		} catch (CreateException e) {
			LOG.error(e.getMessage());
		}
	}

	@Override
	public String toString() {
		return getClass().getSimpleName();
	}

}
