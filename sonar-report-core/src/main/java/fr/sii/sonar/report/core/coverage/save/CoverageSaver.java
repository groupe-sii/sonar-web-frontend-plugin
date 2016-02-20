package fr.sii.sonar.report.core.coverage.save;

import java.io.IOException;

import org.sonar.api.batch.Sensor;
import org.sonar.api.batch.SensorContext;
import org.sonar.api.batch.fs.FilePredicates;
import org.sonar.api.batch.fs.InputFile;
import org.sonar.api.batch.fs.InputFile.Type;
import org.sonar.api.measures.CoreMetrics;
import org.sonar.api.measures.Measure;
import org.sonar.api.resources.Project;
import org.sonar.api.utils.log.Logger;
import org.sonar.api.utils.log.Loggers;

import fr.sii.sonar.report.core.common.PluginContext;
import fr.sii.sonar.report.core.common.save.Saver;
import fr.sii.sonar.report.core.common.util.FileUtil;
import fr.sii.sonar.report.core.coverage.CoverageConstants;
import fr.sii.sonar.report.core.coverage.domain.CoverageReport;
import fr.sii.sonar.report.core.coverage.domain.FileCoverage;
import fr.sii.sonar.report.core.coverage.domain.LineCoverage;

/**
 * Generic coverage saver that generates Sonar measures for code coverage from
 * any coverage report. The report may contain only lines for the covered files.
 * If a file is not in the report, it doesn't mean that the file is not in the
 * project. That's why this saver must also loop through all source files and
 * generate no coverage for each source file that is not in the report.
 * 
 * @author Aurélien Baudet
 *
 */
public class CoverageSaver implements Saver<CoverageReport> {
	private static final Logger LOG = Loggers.get(CoverageSaver.class);

	/**
	 * The sonar plugin context
	 */
	private PluginContext pluginContext;

	/**
	 * Builder for generating coverage measures
	 */
	private CoverageMeasureBuilder builder;

	public CoverageSaver(PluginContext pluginContext, CoverageMeasureBuilder builder) {
		super();
		this.pluginContext = pluginContext;
		this.builder = builder;
	}

	public void save(CoverageReport report, Project project, SensorContext context) {
		// save coverage information provided by report file
		for (FileCoverage file : report.getFiles()) {
			saveCoverage(project, context, report, file);
		}
		// the list of files in the report file may not cover all sources files =>
		// must create empty coverage entry for each file that is not present in
		// report file
		for (InputFile sourceFile : getFilesForLanguage()) {
			try {
				if (!hasCoverage(report, sourceFile)) {
					saveZeroValueForResource(sourceFile, context);
				}
			} catch (IOException e) {
				LOG.warn("failed to determine if coverage is provided by coverage report for " + sourceFile.absolutePath());
				saveZeroValueForResource(sourceFile, context);
			}
		}
	}

	private Iterable<InputFile> getFilesForLanguage() {
		String languageKey = ((CoverageConstants) pluginContext.getConstants()).getLanguageKey();
		FilePredicates predicates = pluginContext.getFilesystem().predicates();
		return pluginContext.getFilesystem().inputFiles(predicates.and(predicates.hasLanguage(languageKey), predicates.hasType(Type.MAIN)));
	}

	/**
	 * Check if the source file appears in the coverage file
	 * 
	 * @param report
	 *            the coverage report
	 * @param sourceFile
	 *            the project source file
	 * @return true if the source file is present in the coverage report, false
	 *         otherwise
	 * @throws IOException
	 *             when a file can't be read
	 */
	protected boolean hasCoverage(CoverageReport report, InputFile sourceFile) throws IOException {
		for (FileCoverage file : report.getFiles()) {
			String coverageFilePath = getAnalyzedFilePath(report, file);
			if (sourceFile.absolutePath().equals(coverageFilePath)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Save coverage information for the provided source file
	 * 
	 * @param project
	 *            the sonar project
	 * @param context
	 *            the sonar context
	 * @param report
	 *            the coverage report
	 * @param file
	 *            the file coverage information to save
	 */
	protected void saveCoverage(Project project, SensorContext context, CoverageReport report, FileCoverage file) {
		// try to load the sonar file from real file system
		InputFile sonarFile = getSourceFile(report, file);
		if (FileUtil.checkMissing(pluginContext, sonarFile, getAnalyzedFilePath(report, file), "No coverage will be generated for this file")) {
			CoverageMeasureBuilder builder = this.builder.reset();
			for (LineCoverage line : file.getLines()) {
				// generate line coverage measure
				builder.setHits(line.getLine(), line.getExecutionCount());
				// generate branch coverage measure
				if(line.getBranchCoverage()!=null) {
					builder.setConditions(line.getLine(), line.getBranchCoverage().getConditions(), line.getBranchCoverage().getCovered());
				}
			}
			// save generated measures
			for (Measure measure : builder.createMeasures()) {
				context.saveMeasure(sonarFile, measure);
			}
		}
	}

	/**
	 * Save a no coverage entry for a source file that is not present in the
	 * coverage report. This information can only be added if there are
	 * {@link CoreMetrics}.LINE and {@link CoreMetrics}.NCLOC information
	 * already stored about the source file in sonar. This is done with another
	 * {@link Sensor} that provides quality information
	 * 
	 * @param sourceFile
	 *            the sonar source file
	 * @param context
	 *            the sonar context
	 */
	protected void saveZeroValueForResource(InputFile sourceFile, SensorContext context) {
		CoverageMeasureBuilder builder = this.builder.reset();
		for (int l=0 ; l<sourceFile.lines() ; l++) {
			builder.setHits(l, 0);
		}
		// save generated measures
		for (Measure measure : builder.createMeasures()) {
			context.saveMeasure(sourceFile, measure);
		}
	}

	/**
	 * Get the sonar file from either absolute path or relative path to source
	 * directories
	 * 
	 * @param project
	 *            the project under plugin execution
	 * @param file
	 *            the report file information that contains path
	 * @return the sonar file
	 */
	private InputFile getSourceFile(CoverageReport report, FileCoverage file) {
		return FileUtil.getInputFile(pluginContext.getFilesystem(), file.getPath(), Type.MAIN);
	}

	/**
	 * Get the real path to the file
	 * 
	 * @param report
	 *            the coverage report
	 * @param file
	 *            the file coverage information that contains raw path (may be
	 *            relative to the report)
	 * @return the real path to the file
	 */
	private String getAnalyzedFilePath(CoverageReport report, FileCoverage file) {
		InputFile sourceFile = getSourceFile(report, file);
		return sourceFile==null ? file.getPath() : sourceFile.absolutePath();
	}

}
