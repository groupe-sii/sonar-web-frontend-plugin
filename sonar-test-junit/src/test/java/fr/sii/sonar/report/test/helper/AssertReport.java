package fr.sii.sonar.report.test.helper;

import static org.junit.Assert.assertEquals;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import fr.sii.sonar.report.core.test.domain.TestCase;
import fr.sii.sonar.report.core.test.domain.TestFile;
import fr.sii.sonar.report.core.test.domain.TestReport;
import fr.sii.sonar.report.core.test.domain.TestStats;

public class AssertReport {
	public static void assertTestReport(TestReport expected, TestReport actual) {
		assertEquals("report type should be "+expected.getType(), expected.getType(), actual.getType());
		assertTestFiles(expected.getFiles(), actual.getFiles());
	}

	public static void assertTestFiles(List<TestFile> expected, List<TestFile> actual) {
		assertEquals("Should have same number of files", expected.size(), actual.size());
		Collections.sort(expected, new TestFileComparator());
		Collections.sort(actual, new TestFileComparator());
		for(int i=0 ; i<expected.size() ; i++) {
			assertTestFile(expected.get(i), actual.get(i));
		}
	}

	public static void assertTestFile(TestFile expected, TestFile actual) {
		assertEquals("test file "+expected.getPath()+" path should be "+expected.getPath(), expected.getPath(), actual.getPath());
		assertStats("for file "+expected.getPath(), expected.getStats(), actual.getStats());
		assertTestCases(expected.getTestCases(), actual.getTestCases());
	}

	public static void assertStats(String name, List<TestStats> expected, List<TestStats> actual) {
		assertEquals("Should have same number of stats", expected.size(), actual.size());
		for(int i=0 ; i<expected.size() ; i++) {
			assertStats(name, expected.get(i), actual.get(i));
		}
	}

	public static void assertStats(String name, TestStats expected, TestStats actual) {
		assertEquals("test stats "+name+" duration should be "+expected.getDuration(), expected.getDuration(), actual.getDuration());
		assertEquals("test stats "+name+" errors should be "+expected.getErrors(), expected.getErrors(), actual.getErrors());
		assertEquals("test stats "+name+" failures should be '"+expected.getFailures()+"'", expected.getFailures(), actual.getFailures());
		assertEquals("test stats "+name+" passed should be '"+expected.getPassed()+"'", expected.getPassed(), actual.getPassed());
		assertEquals("test stats "+name+" skipped should be '"+expected.getSkipped()+"'", expected.getSkipped(), actual.getSkipped());
		assertEquals("test stats "+name+" total should be '"+expected.getTotal()+"'", expected.getTotal(), actual.getTotal());
	}

	public static void assertTestCases(List<TestCase> expected, List<TestCase> actual) {
		assertEquals("Should have same number of test cases", expected.size(), actual.size());
		Collections.sort(expected, new TestCaseComparator());
		Collections.sort(actual, new TestCaseComparator());
		for(int i=0 ; i<expected.size() ; i++) {
			assertTestCase(expected.get(i), actual.get(i));
		}
	}

	public static void assertTestCase(TestCase expected, TestCase actual) {
		assertEquals("test case '"+expected.getName()+"' name should be "+expected.getName(), expected.getName(), actual.getName());
		assertEquals("test case '"+expected.getName()+"' status should be "+expected.getStatus(), expected.getStatus(), actual.getStatus());
		assertEquals("test case '"+expected.getName()+"' duration should be '"+expected.getDuration()+"'", expected.getDuration(), actual.getDuration());
		assertEquals("test case '"+expected.getName()+"' message should be '"+expected.getMessage()+"'", expected.getMessage(), actual.getMessage());
		assertEquals("test case '"+expected.getName()+"' stacktrace should be '"+expected.getStackTrace()+"'", expected.getStackTrace(), actual.getStackTrace());
	}
	
	private static class TestFileComparator implements Comparator<TestFile> {

		@Override
		public int compare(TestFile o1, TestFile o2) {
			return o1.getPath().compareTo(o2.getPath());
		}
		
	}
	
	private static class TestCaseComparator implements Comparator<TestCase> {

		@Override
		public int compare(TestCase o1, TestCase o2) {
			return o1.getName().compareTo(o2.getName());
		}
		
	}
}
