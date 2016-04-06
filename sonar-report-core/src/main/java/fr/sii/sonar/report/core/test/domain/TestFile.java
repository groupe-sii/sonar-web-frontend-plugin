package fr.sii.sonar.report.core.test.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Provides information about a test file (the file that contains the
 * unit/integration test code) :
 * <ul>
 * <li>The path to the test file</li>
 * <li>The global statistics for the file</li>
 * <li>The list of test cases that are included in the file</li>
 * </ul>
 * 
 * @author Aurélien Baudet
 *
 */
public class TestFile {
	/**
	 * The path to the file
	 */
	protected String path;

	/**
	 * The global statistics for all the included test cases
	 */
	protected TestStats stats;
	
	/**
	 * The list of test cases
	 */
	protected List<TestCase> testCases;

	public TestFile(String path, TestStats stats, TestCase... testCases) {
		this(path, stats, new ArrayList<TestCase>(Arrays.asList(testCases)));
	}

	public TestFile(String path, TestStats stats, List<TestCase> testCases) {
		super();
		this.path = path;
		this.stats = stats;
		this.testCases = testCases;
	}

	public String getPath() {
		return path;
	}

	public TestStats getStats() {
		return stats;
	}

	public List<TestCase> getTestCases() {
		return testCases;
	}

	public TestFile addTestCase(TestCase testCase) {
		testCases.add(testCase);
		return this;
	}

	public void setStats(TestStats stats) {
		this.stats = stats;
	}

	@Override
	public String toString() {
		return "{path=" + path + ", stats=" + stats + ", testCases=" + testCases + "}";
	}
}
