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

/**
 * Implementation of a sonar {@link Sensor} that is based on an external report
 * file. This implementation doesn't really analyze the code. It just loads and
 * parses a previously generated report and then save the measures into sonar.
 * Any kind of report can be managed this way and any sonar measure can be
 * generated. This sensor can either be used for quality or for coverage.
 * 
 * This sensor works by delegating report loading and parsing to a specialized
 * class ({@link Provider}) and also by delegating the measure generation to a
 * specialized class ({@link Saver})
 * 
 * This sensor checks the existence of the report file on the file system to
 * indicate if the plugin should be executed or not on the provided project
 * 
 * @author aurelien
 *
 */
public abstract class ReportSensor implements Sensor {

	private static final Logger LOG = LoggerFactory.getLogger(ReportSensor.class);

	private final PluginContext pluginContext;
	private final ProviderFactory<Report> providerFactory;
	private final SaverFactory<Report> saverFactory;

	/**
	 * Use of IoC to get Settings
	 */
	public ReportSensor(ReportConstants constants, Settings settings, RuleFinder ruleFinder, ModuleFileSystem filesystem, ResourcePerspectives resourcePerspective,
			ProviderFactory<Report> providerFactory, SaverFactory<Report> saverFactory) {
		super();
		this.pluginContext = new PluginContext(settings, resourcePerspective, ruleFinder, filesystem, constants);
		this.providerFactory = providerFactory;
		this.saverFactory = saverFactory;
	}

	/**
	 * Executes the plugin only if the project provides a report file
	 */
	public boolean shouldExecuteOnProject(Project project) {
		try {
			File reportFile = getReportFile(project);
			return reportFile.exists();
		} catch (Exception e) {
			LOG.error("The report file doesn't exist => skip the sonar plugin", e.getMessage());
			return false;
		}
	}

	/**
	 * Get the report file from the file system based on project path and
	 * relative path to the report file provided by the sonar configuration)
	 * 
	 * @param project
	 *            the sonar project
	 * @return the reoprt file
	 */
	protected File getReportFile(Project project) {
		return new File(pluginContext.getFilesystem().baseDir(), pluginContext.getSettings().getString(pluginContext.getConstants().getReportPathKey()));
	}

	/**
	 * Start the analysis of the project. In real, just parse the report file to
	 * get analysis information and save this information (transform into sonar
	 * measures) into sonar
	 */
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
