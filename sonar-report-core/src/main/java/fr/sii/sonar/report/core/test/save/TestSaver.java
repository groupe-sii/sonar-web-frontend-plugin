package fr.sii.sonar.report.core.test.save;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sonar.api.batch.SensorContext;
import org.sonar.api.measures.CoreMetrics;
import org.sonar.api.measures.Measure;
import org.sonar.api.resources.File;
import org.sonar.api.resources.Project;
import org.sonar.api.test.MutableTestPlan;
import org.sonar.api.utils.ParsingUtils;

import com.google.common.collect.Lists;

import fr.sii.sonar.report.core.common.PluginContext;
import fr.sii.sonar.report.core.common.exception.SaveException;
import fr.sii.sonar.report.core.common.save.Saver;
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
	private static final Logger LOG = LoggerFactory.getLogger(TestSaver.class);

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
			File sourceFile = getTestFile(testFile);
			if(sourceFile==null) {
				LOG.error("The file "+testFile.getPath()+" doesn't exist. No test result will be generated for this test file");
				if(pluginContext.getSettings().getBoolean(pluginContext.getConstants().getMissingFileFailKey())) {
					throw new SaveException("The file "+testFile.getPath()+" doesn't exist");
				}
			} else {
				// save general measures for the test file
				saveGlobalStats(context, testFile, sourceFile);
				// save each test case information
				for(TestCase testCase : testFile.getTestCases()) {
					saveTestCase(report, sourceFile, testCase);
				}
				// save the file sources
				saveSource(context, testFile, sourceFile);
			}
		}
	}

	/**
	 * Save the source of the test file
	 * 
	 * @param context
	 *            the sensor context
	 * @param testFile
	 *            the test file information
	 * @param sourceFile
	 *            the sonar file associated to the test file information
	 */
	private void saveSource(SensorContext context, TestFile testFile, File sourceFile) {
		try {
			java.io.File file = getFile(testFile);
			if(file==null) {
				LOG.error("failed to save source for file "+testFile.getPath()+". Cause: file is not found");
			} else {
				context.saveSource(sourceFile, FileUtils.readFileToString(file));
			}
		} catch (IOException e) {
			LOG.error("failed to save source for file "+testFile.getPath()+". Cause: "+e.getMessage());
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
	private void saveTestCase(TestReport report, File sourceFile, TestCase testCase) {
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
	private void saveGlobalStats(SensorContext context, TestFile testFile, File sourceFile) {
		for(Measure measure : generateMeasures(testFile.getStats())) {
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
	 * Get the sonar source file
	 * 
	 * @param file
	 *            the file test information that contains raw path (may be
	 *            relative to the report)
	 * @return the sonar source file
	 */
	private File getTestFile(TestFile testFile) {
		java.io.File file = new java.io.File(testFile.getPath());
		File sourceFile = File.fromIOFile(file, pluginContext.getFilesystem().testDirs());
		if(sourceFile==null) {
			sourceFile = File.fromIOFile(file, pluginContext.getFilesystem().sourceDirs());
		}
		return sourceFile;
	}

	/**
	 * Search for real file on the file system from test directories and then in
	 * source directories
	 * 
	 * @param testFile
	 *            the file to find
	 * @return the java file if found, null if not found
	 */
	private java.io.File getFile(TestFile testFile) {
		List<java.io.File> dirs = new ArrayList<java.io.File>();
		dirs.add(pluginContext.getFilesystem().baseDir());
		dirs.addAll(pluginContext.getFilesystem().testDirs());
		dirs.addAll(pluginContext.getFilesystem().sourceDirs());
		for(java.io.File testDir : dirs) {
			java.io.File file = new java.io.File(testDir, testFile.getPath());
			if(file.exists()) {
				return file;
			}
		}
		return null;
	}


}