package fr.sii.sonar.report.core.test.save;

import java.util.List;

import org.sonar.api.batch.SensorContext;
import org.sonar.api.batch.fs.InputFile;
import org.sonar.api.measures.CoreMetrics;
import org.sonar.api.measures.Measure;
import org.sonar.api.resources.Project;
import org.sonar.api.test.MutableTestPlan;
import org.sonar.api.utils.ParsingUtils;

import com.google.common.collect.Lists;

import fr.sii.sonar.report.core.common.PluginContext;
import fr.sii.sonar.report.core.common.save.Saver;
import fr.sii.sonar.report.core.common.util.FileUtil;
import fr.sii.sonar.report.core.test.TestConstants;
import fr.sii.sonar.report.core.test.domain.TestCase;
import fr.sii.sonar.report.core.test.domain.TestFile;
import fr.sii.sonar.report.core.test.domain.TestReport;
import fr.sii.sonar.report.core.test.domain.TestStats;
import fr.sii.sonar.report.core.test.domain.Type;

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
	
	private Type type;

	public TestSaver(PluginContext pluginContext) {
		super();
		this.pluginContext = pluginContext;
		this.type = ((TestConstants) pluginContext.getConstants()).getType();
	}


	public void save(TestReport report, Project project, SensorContext context) {
		for(TestFile testFile : report.getFiles()) {
			// get sonar source file
			InputFile sourceFile = getTestFile(testFile);
			if(FileUtil.checkMissing(pluginContext, sourceFile, testFile.getPath(), "No test result will be generated for this test file")) {
				// save general measures for the test file
				saveGlobalStats(context, testFile, sourceFile);
				// save each test case information
				for(TestCase testCase : testFile.getTestCases()) {
					saveTestCase(report, sourceFile, testCase);
				}
			}
		}
	}


	/**
	 * Save information about the test case :
	 * <ul>
	 * <li>test name</li>
	 * <li>test duration</li>
	 * <li>test status (OK, ERROR, FAILURE, SKIPPED)</li>
	 * <li>the test message</li>
	 * <li>the stack trace</li>
	 * <li>the type of test (unit or integration)</li>
	 * </ul>
	 * 
	 * @param report
	 *            the report for all tests
	 * @param sourceFile
	 *            the source file associated to the test case
	 * @param testCase
	 *            the test case to save
	 */
	private void saveTestCase(TestReport report, InputFile sourceFile, TestCase testCase) {
		MutableTestPlan testPlan = pluginContext.getResourcePerspective().as(MutableTestPlan.class, sourceFile);
		if (testPlan != null) {
			testPlan.addTestCase(testCase.getName())
				.setDurationInMs(testCase.getDuration())
				.setStatus(testCase.getStatus())
				.setMessage(testCase.getMessage())
				.setType((report.getType()==null ? type : report.getType()).getSonarValue())
				.setStackTrace(testCase.getStackTrace());
		}
	}


	/**
	 * Save the global statistics for the whole test file (may contain several
	 * test cases) :
	 * <ul>
	 * <li>the total number of tests that are present in the test file</li>
	 * <li>the number of succeed tests</li>
	 * <li>the number of skipped tests</li>
	 * <li>the number of failed tests</li>
	 * <li>the number of tests in error</li>
	 * <li>the total duration for all tests present in the file</li>
	 * <li>the test success density (percentage of success)</li>
	 * </ul>
	 * 
	 * @param context
	 *            the sensor contest
	 * @param testFile
	 *            the test file information
	 * @param sourceFile
	 *            the sonar file
	 */
	private void saveGlobalStats(SensorContext context, TestFile testFile, InputFile sourceFile) {
		for(Measure<Double> measure : generateMeasures(testFile.getStats())) {
			context.saveMeasure(sourceFile, measure);
		}
	}


	/**
	 * Generate the measures from the report statistics for the test file
	 * 
	 * @param testStats
	 *            the test statistics
	 * @return the list of Sonar measures
	 */
	public List<Measure<Double>> generateMeasures(TestStats testStats) {
		List<Measure<Double>> measures = Lists.newArrayList();
		if (testStats.getTotal() > 0) {
			measures.add(new Measure<Double>(CoreMetrics.SKIPPED_TESTS, (double) testStats.getSkipped()));
			measures.add(new Measure<Double>(CoreMetrics.TESTS, (double) testStats.getTotal()));
			measures.add(new Measure<Double>(CoreMetrics.TEST_ERRORS, (double) testStats.getErrors()));
			measures.add(new Measure<Double>(CoreMetrics.TEST_FAILURES, (double) testStats.getFailures()));
			measures.add(new Measure<Double>(CoreMetrics.TEST_EXECUTION_TIME, (double) testStats.getDuration()));
			double percentage = testStats.getPassed() * 100d / (testStats.getTotal() - testStats.getSkipped());
			measures.add(new Measure<Double>(CoreMetrics.TEST_SUCCESS_DENSITY, ParsingUtils.scaleValue(percentage)));
		}
		return measures;
	}


	/**
	 * Get the sonar source file
	 * 
	 * @param file
	 *            the file test information that contains raw path (may be
	 *            relative to the report)
	 * @return the sonar source file
	 */
	private InputFile getTestFile(TestFile testFile) {
		return FileUtil.getInputFile(pluginContext.getFilesystem(), testFile.getPath());
	}

}