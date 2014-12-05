package fr.sii.sonar.report.core.test.save;

import java.util.List;

import org.sonar.api.batch.SensorContext;
import org.sonar.api.measures.CoreMetrics;
import org.sonar.api.measures.Measure;
import org.sonar.api.resources.File;
import org.sonar.api.resources.Project;
import org.sonar.api.test.MutableTestPlan;
import org.sonar.api.utils.ParsingUtils;

import com.google.common.collect.Lists;

import fr.sii.sonar.report.core.common.PluginContext;
import fr.sii.sonar.report.core.common.save.Saver;
import fr.sii.sonar.report.core.test.domain.TestCase;
import fr.sii.sonar.report.core.test.domain.TestFile;
import fr.sii.sonar.report.core.test.domain.TestReport;
import fr.sii.sonar.report.core.test.domain.TestStats;

/**
 * Saver that stores test measures into sonar using the provided {@link TestReport}
 * 
 * @author Aurélien Baudet
 *
 */
public class TestSaver implements Saver<TestReport> {
	/**
	 * The sonar plugin context
	 */
	private PluginContext pluginContext;

	public TestSaver(PluginContext pluginContext) {
		super();
		this.pluginContext = pluginContext;
	}


	public void save(TestReport report, Project project, SensorContext context) {
		for(TestFile testFile : report.getFiles()) {
			// get sonar source file
			File sourceFile = File.fromIOFile(getAnalyzedFilePath(report, testFile), project);
			// save general measures for the test file
			for(Measure measure : generateMeasures(testFile.getStats())) {
				context.saveMeasure(sourceFile, measure);
			}
			// save each test case information
			for(TestCase testCase : testFile.getTestCases()) {
				MutableTestPlan testPlan = pluginContext.getResourcePerspective().as(MutableTestPlan.class, sourceFile);
				if (testPlan != null) {
					testPlan.addTestCase(testCase.getName())
						.setDurationInMs(testCase.getDuration())
						.setStatus(testCase.getStatus())
						.setMessage(testCase.getMessage())
						.setType(testCase.getType().getSonarValue())
						.setStackTrace(testCase.getStackTrace());
				}
			}
		}
	}

	/**
	 * Generate the measures from the report statistics for the test file
	 * 
	 * @param testStats
	 *            the test statistics
	 * @return the list of Sonar measures
	 */
	public List<Measure> generateMeasures(TestStats testStats) {
		List<Measure> measures = Lists.newArrayList();
		if (testStats.getTotal() > 0) {
			measures.add(new Measure(CoreMetrics.SKIPPED_TESTS, (double) testStats.getSkipped()));
			measures.add(new Measure(CoreMetrics.TESTS, (double) testStats.getTotal()));
			measures.add(new Measure(CoreMetrics.TEST_ERRORS, (double) testStats.getErrors()));
			measures.add(new Measure(CoreMetrics.TEST_FAILURES, (double) testStats.getFailures()));
			measures.add(new Measure(CoreMetrics.TEST_EXECUTION_TIME, (double) testStats.getDuration()));
			double percentage = testStats.getPassed() * 100d / (testStats.getTotal() - testStats.getSkipped());
			measures.add(new Measure(CoreMetrics.TEST_SUCCESS_DENSITY, ParsingUtils.scaleValue(percentage)));
		}
		return measures;
	}


	/**
	 * Get the real path to the file
	 * 
	 * @param report
	 *            the test report
	 * @param file
	 *            the file test information that contains raw path (may be
	 *            relative to the report)
	 * @return the real path to the file
	 */
	private java.io.File getAnalyzedFilePath(TestReport report, TestFile file) {
		java.io.File f = new java.io.File(pluginContext.getFilesystem().baseDir(), file.getPath());
		return f.exists() ? f : new java.io.File(file.getPath());
	}
}
