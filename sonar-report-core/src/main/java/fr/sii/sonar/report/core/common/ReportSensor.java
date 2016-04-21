package fr.sii.sonar.report.core.common;

import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sonar.api.batch.Sensor;
import org.sonar.api.batch.SensorContext;
import org.sonar.api.resources.Project;

import fr.sii.sonar.report.core.common.domain.Report;
import fr.sii.sonar.report.core.common.exception.CreateException;
import fr.sii.sonar.report.core.common.exception.ProviderException;
import fr.sii.sonar.report.core.common.factory.ProviderFactory;
import fr.sii.sonar.report.core.common.factory.SaverFactory;
import fr.sii.sonar.report.core.common.provider.Provider;
import fr.sii.sonar.report.core.common.save.Saver;

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
 * @author Aurélien Baudet
 *
 * @param <R>
 *            The type of handled report
 */
public abstract class ReportSensor<R extends Report> implements Sensor {

	private static final Logger LOG = LoggerFactory.getLogger(ReportSensor.class);

	private final PluginContext pluginContext;
	private final ProviderFactory<R> providerFactory;
	private final SaverFactory<R> saverFactory;

	/**
	 * Use of IoC to get Settings
	 * 
	 * @param constants
	 *            the constants for the current plugin
	 * @param pluginDependencies
	 *            the Sonar dependencies
	 * @param providerFactory
	 *            the factory for creating a new provider
	 * @param saverFactory
	 *            the factory for creating a new saver
	 */
	public ReportSensor(ReportConstants constants, PluginDependencies pluginDependencies, ProviderFactory<R> providerFactory, SaverFactory<R> saverFactory) {
		super();
		this.pluginContext = new PluginContext(constants, pluginDependencies);
		this.providerFactory = providerFactory;
		this.saverFactory = saverFactory;
	}

	/**
	 * Executes the plugin only if the project provides a report file
	 */
	public boolean shouldExecuteOnProject(Project project) {
		try {
			File reportFile = getReportFile(project);
			boolean exists = reportFile.exists();
			if (exists) {
				LOG.info("Loading and storing " + reportFile.getAbsolutePath() + " in Sonar");
			} else if (!pluginContext.getSettings().getBoolean(ReportSensorConstants.SKIP_LOG_MISSING_REPORT_KEY)) {
				LOG.warn("The report file " + reportFile.getAbsolutePath() + " doesn't exist");
			}
			return exists;
		} catch (Exception e) {
			LOG.error("Failed to find report file " + e.getMessage());
			return false;
		}
	}

	/**
	 * Get the report file from the file system based on project path and
	 * relative path to the report file provided by the sonar configuration)
	 * 
	 * @param project
	 *            the sonar project
	 * @return the report file
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
		File reportFile = getReportFile(project);
		try {
			Provider<R> provider = providerFactory.create(reportFile);
			Saver<R> saver = saverFactory.create(pluginContext);
			saver.save(provider.get(), project, sensorContext);
		} catch (ProviderException e) {
			LOG.error(e.getMessage());
			throw new IllegalArgumentException("Cannot parse report " + reportFile, e);
		} catch (CreateException e) {
			LOG.error(e.getMessage());
			throw new IllegalStateException("Cannot initialize provider or saver", e);
		}
	}

	@Override
	public String toString() {
		return getClass().getSimpleName();
	}

}
