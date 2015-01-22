package fr.sii.sonar.report.core.duplication.save;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sonar.api.batch.SensorContext;
import org.sonar.api.measures.CoreMetrics;
import org.sonar.api.measures.Measure;
import org.sonar.api.measures.PersistenceMode;
import org.sonar.api.resources.File;
import org.sonar.api.resources.Project;

import fr.sii.sonar.report.core.common.PluginContext;
import fr.sii.sonar.report.core.common.exception.DuplicationException;
import fr.sii.sonar.report.core.common.save.Saver;
import fr.sii.sonar.report.core.common.util.FileUtil;
import fr.sii.sonar.report.core.duplication.domain.DuplicationReport;

public class DuplicationSaver implements Saver<DuplicationReport> {
	private static final Logger LOG = LoggerFactory.getLogger(DuplicationSaver.class);

	private final PluginContext pluginContext;

	private Project project;

	public DuplicationSaver(PluginContext pluginContext) {
		super();
		this.pluginContext = pluginContext;
	}

	public void save(DuplicationReport report, Project project, SensorContext context) {
		this.project = project;
		for (DuplicationFileInformation file : GroupByFileHelper.group(report)) {
			File sonarFile = getSourceFile(report, project, file);
			if (FileUtil.checkMissing(pluginContext, sonarFile, file.getPath(), "No duplication will be generated for this file")) {
				// save general information about duplications for the file
				saveGeneralMetrics(context, file, sonarFile);
				// save duplication details (duplicated lines on the file)
				saveDetails(context, report, file, sonarFile);
			}
		}
	}

	/**
	 * Save general metrics for the file (total number of duplicate lines and
	 * total number of duplicated blocks)
	 * 
	 * @param context
	 *            the Sonar context
	 * @param file
	 *            the code duplication information about the file (total number
	 *            of duplicated lines and total number of duplicated blocks)
	 * @param sonarFile
	 *            the Sonar file used to store information in Sonar
	 */
	private void saveGeneralMetrics(SensorContext context, DuplicationFileInformation file, File sonarFile) {
		context.saveMeasure(sonarFile, CoreMetrics.DUPLICATED_FILES, 1.0);
		context.saveMeasure(sonarFile, CoreMetrics.DUPLICATED_LINES, Integer.valueOf(file.getLines()).doubleValue());
		context.saveMeasure(sonarFile, CoreMetrics.DUPLICATED_BLOCKS, Integer.valueOf(file.getBlocks().size()).doubleValue());
	}

	/**
	 * Save details of duplicated code for the provided file. It generates Sonar
	 * internal xml data that contains following information for each duplicated
	 * block:
	 * <ul>
	 * <li>The start line of duplicated block</li>
	 * <li>The number of duplicated lines for the block</li>
	 * <li>The Sonar resource key that corresponds to real file that contains
	 * the duplicated block</li>
	 * </ul>
	 * 
	 * @param context
	 *            the Sonar context
	 * @param report
	 *            the whole code duplication report
	 * @param file
	 *            the current file that contains duplications
	 * @param sonarFile
	 *            the Sonar file used to store information in Sonar
	 */
	private void saveDetails(SensorContext context, DuplicationReport report, DuplicationFileInformation file, File sonarFile) {
		try {
			String xml = DuplicationDetailsHelper.toXml(project, pluginContext.getFilesystem(), report, file);
			if (xml != null) {
				Measure data = new Measure(CoreMetrics.DUPLICATIONS_DATA, xml).setPersistenceMode(PersistenceMode.DATABASE);
				context.saveMeasure(sonarFile, data);
			}
		} catch (DuplicationException e) {
			LOG.error(e.getMessage(), e);
		}
	}

	/**
	 * Get the sonar file from either absolute path or relative path to source
	 * directories
	 * 
	 * @param report
	 *            the duplication report
	 * @param project
	 *            the project under plugin execution
	 * @param file
	 *            the file information that contains path
	 * @return the sonar file
	 */
	private File getSourceFile(DuplicationReport report, Project project, DuplicationFileInformation file) {
		return FileUtil.getSonarFile(file.getPath(), pluginContext.getFilesystem());
	}

}
