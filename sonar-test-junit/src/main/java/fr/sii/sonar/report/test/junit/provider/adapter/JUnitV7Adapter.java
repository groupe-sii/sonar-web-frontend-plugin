package fr.sii.sonar.report.test.junit.provider.adapter;

import java.util.ArrayList;
import java.util.List;

import fr.sii.sonar.report.core.test.domain.TestCase;
import fr.sii.sonar.report.core.test.domain.TestFile;
import fr.sii.sonar.report.core.test.domain.TestReport;
import fr.sii.sonar.report.core.test.domain.TestStats;
import fr.sii.sonar.test.junit.domain.v7.Error;
import fr.sii.sonar.test.junit.domain.v7.Failure;
import fr.sii.sonar.test.junit.domain.v7.Skipped;
import fr.sii.sonar.test.junit.domain.v7.Testcase;
import fr.sii.sonar.test.junit.domain.v7.Testsuite;
import fr.sii.sonar.test.junit.domain.v7.Testsuites;

public class JUnitV7Adapter implements JUnitAdapter<Testsuites> {

	public TestReport adapt(Testsuites root) {
		return new TestReport(convert(root.getTestsuite()));
	}

	private List<TestFile> convert(List<Testsuite> suites) {
		List<TestFile> testFiles = new ArrayList<TestFile>();
		for(Testsuite suite : suites) {
			testFiles.addAll(convert(suite));
		}
		return testFiles;
	}
	
	private List<TestFile> convert(Testsuite suite) {
		List<TestFile> testFiles = new ArrayList<TestFile>();
		TestFile file = new TestFile(suite.getFile(), stats(suite));
		for(Object any : suite.getTestsuiteOrPropertiesOrTestcase()) {
			if (any instanceof Testsuite) {
				// go into to find test cases
				testFiles.addAll(convert((Testsuite) any));
			} else if (any instanceof Testcase) {
				file.addTestCase(convert((Testcase) any));
			}
		}
		if(file.getTestCases().size()>0) {
			testFiles.add(file);
		}
		return testFiles;
	}

	private TestCase convert(Testcase testCase) {
		String name = testCase.getName();
		long duration = AdapterHelper.getDuration(testCase.getTime());
		for(Object any : testCase.getSkippedOrErrorOrFailure()) {
			if(any instanceof Failure) {
				return TestCase.failure(name, duration, getMessage((Failure) any));
			} else if(any instanceof Error) {
				Error err = (Error) any;
				return TestCase.error(name, duration, getMessage(err), getStackTrace(err));
			} else if(any instanceof Skipped) {
				return TestCase.skipped(name, getMessage((Skipped) any));
			}
		}
		return TestCase.ok(name, duration);
	}
	

	private String getMessage(Skipped skipped) {
		return skipped.getMessage();
	}

	private String getMessage(Error err) {
		return err.getMessage();
	}

	private String getStackTrace(Error err) {
		return err.getContent();
	}

	private String getMessage(Failure failure) {
		return failure.getContent();
	}

	private TestStats stats(Testsuite suite) {
		int total = AdapterHelper.getInt(suite.getTests());
		int failures = AdapterHelper.getInt(suite.getFailures());
		int errors = AdapterHelper.getInt(suite.getErrors());
		int skipped = AdapterHelper.getInt(suite.getSkipped());
		return new TestStats(total, failures, errors, skipped, AdapterHelper.getDuration(suite.getTime()));
	}
	

	
}
