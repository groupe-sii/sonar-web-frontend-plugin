package fr.sii.sonar.coverage.lcov.saver;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sonar.api.batch.SensorContext;
import org.sonar.api.measures.CoreMetrics;
import org.sonar.api.measures.CoverageMeasuresBuilder;
import org.sonar.api.measures.Measure;
import org.sonar.api.measures.PropertiesBuilder;
import org.sonar.api.resources.File;
import org.sonar.api.resources.Project;
import org.sonar.api.scan.filesystem.FileQuery;

import fr.sii.sonar.coverage.lcov.domain.BranchDetails;
import fr.sii.sonar.coverage.lcov.domain.FileInfo;
import fr.sii.sonar.coverage.lcov.domain.LcovReport;
import fr.sii.sonar.coverage.lcov.domain.LineDetails;
import fr.sii.sonar.report.core.PluginContext;
import fr.sii.sonar.report.core.exception.SaveException;
import fr.sii.sonar.report.core.save.Saver;

public class LcovSaver implements Saver<LcovReport> {
	private static final Logger LOG = LoggerFactory.getLogger(LcovSaver.class);

	private PluginContext pluginContext;

	public LcovSaver(PluginContext pluginContext) {
		super();
		this.pluginContext = pluginContext;
	}

	public void save(LcovReport report, Project project, SensorContext context) {
		// save coverage information provided by lcov file
		for (FileInfo file : report.getFiles()) {
			saveCoverage(report, project, context, file);
		}
		// the list of files in the lcov file may not cover all sources files => must create empty coverage entry for each file that is not present in lcov file
		for (java.io.File sourceFile : pluginContext.getFilesystem().files(FileQuery.onSource().onLanguage(pluginContext.getConstants().getLanguageKey()))) {
			try {
				if(!hasCoverage(report, sourceFile)) {
					saveZeroValueForResource(File.fromIOFile(sourceFile, project), context);
				}
			} catch (IOException e) {
				LOG.warn("failed to determine if coverage is provided by LCOV report for "+sourceFile.getAbsolutePath());
				saveZeroValueForResource(File.fromIOFile(sourceFile, project), context);
			}
		}
	}

	protected boolean hasCoverage(LcovReport report, java.io.File sourceFile) throws IOException {
		for (FileInfo file : report.getFiles()) {
			java.io.File lcovFile = getAnalyzedFilePath(report, file);
			if(sourceFile.getCanonicalPath().equals(lcovFile.getCanonicalPath())) {
				return true;
			}
		}
		return false;
	}

	protected void saveCoverage(LcovReport report, Project project, SensorContext context, FileInfo file) {
		File sonarFile = File.fromIOFile(getAnalyzedFilePath(report, file), project);
		if (sonarFile == null) {
			LOG.error("The file " + getAnalyzedFilePath(report, file) + " doesn't exist. No coverage will be generated for this file");
			if (pluginContext.getSettings().getBoolean(pluginContext.getConstants().getMissingFileFailKey())) {
				throw new SaveException("The file " + getAnalyzedFilePath(report, file) + " doesn't exist");
			}
		} else {
			CoverageMeasuresBuilder result = CoverageMeasuresBuilder.create();
			// generate line coverage measure
			for (LineDetails lines : file.getLines().getDetails()) {
				result.setHits(lines.getLine(), lines.getHit());
			}
			// generate branch coverage measure
			for (Entry<Integer, BranchCoverage> entry : groupByLine(file.getBranches().getDetails()).entrySet()) {
				result.setConditions(entry.getKey(), entry.getValue().getConditions(), entry.getValue().getCovered());
			}
			for (Measure measure : result.createMeasures()) {
				context.saveMeasure(sonarFile, measure);
			}
		}
	}

	protected Map<Integer, BranchCoverage> groupByLine(List<BranchDetails> branches) {
		Map<Integer, BranchCoverage> map = new HashMap<Integer, BranchCoverage>();
		for (BranchDetails branch : branches) {
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

	protected void saveZeroValueForResource(File resource, SensorContext context) {
		PropertiesBuilder<Integer, Integer> lineHitsData = new PropertiesBuilder<Integer, Integer>(CoreMetrics.COVERAGE_LINE_HITS_DATA);

		Measure measure = context.getMeasure(resource, CoreMetrics.LINES);
		if(measure!=null) {
			for (int x = 1; x < measure.getIntValue(); x++) {
				lineHitsData.add(x, 0);
			}
		}

		// use non comment lines of code for coverage calculation
		Measure ncloc = context.getMeasure(resource, CoreMetrics.NCLOC);
		if(ncloc!=null) {
			context.saveMeasure(resource, lineHitsData.build());
			context.saveMeasure(resource, CoreMetrics.LINES_TO_COVER, ncloc.getValue());
			context.saveMeasure(resource, CoreMetrics.UNCOVERED_LINES, ncloc.getValue());
		}
	}

	private java.io.File getAnalyzedFilePath(LcovReport report, FileInfo file) {
		java.io.File f = new java.io.File(pluginContext.getFilesystem().baseDir(), file.getPath());
		return f.exists() ? f : new java.io.File(file.getPath());
	}

}
