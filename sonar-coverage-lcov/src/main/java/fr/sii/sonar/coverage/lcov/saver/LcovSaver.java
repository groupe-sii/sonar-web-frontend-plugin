package fr.sii.sonar.coverage.lcov.saver;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sonar.api.batch.Sensor;
import org.sonar.api.batch.SensorContext;
import org.sonar.api.measures.CoreMetrics;
import org.sonar.api.measures.CoverageMeasuresBuilder;
import org.sonar.api.measures.Measure;
import org.sonar.api.measures.PropertiesBuilder;
import org.sonar.api.resources.File;
import org.sonar.api.resources.Project;
import org.sonar.api.scan.filesystem.FileQuery;

import fr.sii.sonar.coverage.lcov.domain.BranchCoverageDetail;
import fr.sii.sonar.coverage.lcov.domain.FileCoverage;
import fr.sii.sonar.coverage.lcov.domain.LcovReport;
import fr.sii.sonar.coverage.lcov.domain.LineCoverageDetail;
import fr.sii.sonar.report.core.common.PluginContext;
import fr.sii.sonar.report.core.common.exception.SaveException;
import fr.sii.sonar.report.core.common.save.Saver;

/**
 * Specific LCOV saver that generates Sonar measures for code coverage from a
 * LCOV report. The LCOV report contains only lines for the covered files. If a
 * file is not in the LCOV report doesn't mean that it is not in the project.
 * That's why this saver must also loop through all source files and generate no
 * coverage for each source file that is not in the LCOV report.
 * 
 * @author Aurélien Baudet
 *
 */
public class LcovSaver implements Saver<LcovReport> {
	private static final Logger LOG = LoggerFactory.getLogger(LcovSaver.class);

	/**
	 * The sonar plugin context
	 */
	private PluginContext pluginContext;

	public LcovSaver(PluginContext pluginContext) {
		super();
		this.pluginContext = pluginContext;
	}

	public void save(LcovReport report, Project project, SensorContext context) {
		// save coverage information provided by lcov file
		for (FileCoverage file : report.getFiles()) {
			saveCoverage(project, context, report, file);
		}
		// the list of files in the lcov file may not cover all sources files =>
		// must create empty coverage entry for each file that is not present in
		// lcov file
		for (java.io.File sourceFile : pluginContext.getFilesystem().files(FileQuery.onSource().onLanguage(pluginContext.getConstants().getLanguageKey()))) {
			try {
				if (!hasCoverage(report, sourceFile)) {
					saveZeroValueForResource(File.fromIOFile(sourceFile, project), context);
				}
			} catch (IOException e) {
				LOG.warn("failed to determine if coverage is provided by LCOV report for " + sourceFile.getAbsolutePath());
				saveZeroValueForResource(File.fromIOFile(sourceFile, project), context);
			}
		}
	}

	/**
	 * Check if the source file appears in the LCOV file
	 * 
	 * @param report
	 *            the LCOV report
	 * @param sourceFile
	 *            the project source file
	 * @return true if the source file is present in the LCOV report, false
	 *         otherwise
	 * @throws IOException
	 *             when a file can't be read
	 */
	protected boolean hasCoverage(LcovReport report, java.io.File sourceFile) throws IOException {
		for (FileCoverage file : report.getFiles()) {
			java.io.File lcovFile = getAnalyzedFilePath(report, file);
			if (sourceFile.getCanonicalPath().equals(lcovFile.getCanonicalPath())) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Save LCOV coverage information for the provided source file
	 * 
	 * @param project
	 *            the sonar project
	 * @param context
	 *            the sonar context
	 * @param report
	 *            the LCOV report
	 * @param file
	 *            the LCOV file coverage information to save
	 */
	protected void saveCoverage(Project project, SensorContext context, LcovReport report, FileCoverage file) {
		// try to load the sonar file from real file system
		File sonarFile = File.fromIOFile(getAnalyzedFilePath(report, file), project);
		if (sonarFile == null) {
			LOG.error("The file " + getAnalyzedFilePath(report, file) + " doesn't exist. No coverage will be generated for this file");
			if (pluginContext.getSettings().getBoolean(pluginContext.getConstants().getMissingFileFailKey())) {
				throw new SaveException("The file " + getAnalyzedFilePath(report, file) + " doesn't exist");
			}
		} else {
			CoverageMeasuresBuilder result = CoverageMeasuresBuilder.create();
			// generate line coverage measure
			for (LineCoverageDetail lines : file.getLines().getDetails()) {
				result.setHits(lines.getLine(), lines.getExecutionCount());
			}
			// generate branch coverage measure
			for (Entry<Integer, BranchCoverage> entry : groupByLine(file.getBranches().getDetails()).entrySet()) {
				result.setConditions(entry.getKey(), entry.getValue().getConditions(), entry.getValue().getCovered());
			}
			// save generated measures
			for (Measure measure : result.createMeasures()) {
				context.saveMeasure(sonarFile, measure);
			}
		}
	}

	/**
	 * Transform the list of branch coverage detail into a map indexed by the
	 * branch line number
	 * 
	 * @param branches
	 *            the list of branch details to transform
	 * @return the map indexed by the branch line number and with
	 *         {@link BranchCoverage} value
	 */
	protected Map<Integer, BranchCoverage> groupByLine(List<BranchCoverageDetail> branches) {
		Map<Integer, BranchCoverage> map = new HashMap<Integer, BranchCoverage>();
		for (BranchCoverageDetail branch : branches) {
			if (!map.containsKey(branch.getLine())) {
				map.put(branch.getLine(), new BranchCoverage());
			}
			BranchCoverage branchCoverage = map.get(branch.getLine());
			branchCoverage.addConditions(1);
			if (branch.getTaken() > 0) {
				branchCoverage.addCovered(1);
			}
		}
		return map;
	}

	/**
	 * Save a no coverage entry for a source file that is not present in the
	 * LCOV report. This information can only be added if there are
	 * {@link CoreMetrics}.LINE and {@link CoreMetrics}.NCLOC information
	 * already stored about the source file in sonar. This is done with another
	 * {@link Sensor} that provides quality information
	 * 
	 * @param resource
	 *            the sonar source file
	 * @param context
	 *            the sonar context
	 */
	protected void saveZeroValueForResource(File resource, SensorContext context) {
		PropertiesBuilder<Integer, Integer> lineHitsData = new PropertiesBuilder<Integer, Integer>(CoreMetrics.COVERAGE_LINE_HITS_DATA);

		Measure measure = context.getMeasure(resource, CoreMetrics.LINES);
		if (measure != null) {
			for (int x = 1; x < measure.getIntValue(); x++) {
				lineHitsData.add(x, 0);
			}
		}

		// use non comment lines of code for coverage calculation
		Measure ncloc = context.getMeasure(resource, CoreMetrics.NCLOC);
		if (ncloc != null) {
			context.saveMeasure(resource, lineHitsData.build());
			context.saveMeasure(resource, CoreMetrics.LINES_TO_COVER, ncloc.getValue());
			context.saveMeasure(resource, CoreMetrics.UNCOVERED_LINES, ncloc.getValue());
		}
	}

	/**
	 * Get the real path to the file
	 * 
	 * @param report
	 *            the LCOV report
	 * @param file
	 *            the file coverage information that contains raw path (may be
	 *            relative to the report)
	 * @return the real path to the file
	 */
	private java.io.File getAnalyzedFilePath(LcovReport report, FileCoverage file) {
		java.io.File f = new java.io.File(pluginContext.getFilesystem().baseDir(), file.getPath());
		return f.exists() ? f : new java.io.File(file.getPath());
	}

}
