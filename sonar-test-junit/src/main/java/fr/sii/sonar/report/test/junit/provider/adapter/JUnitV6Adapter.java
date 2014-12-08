package fr.sii.sonar.report.test.junit.provider.adapter;

import java.util.ArrayList;
import java.util.List;

import fr.sii.sonar.report.core.test.domain.TestCase;
import fr.sii.sonar.report.core.test.domain.TestFile;
import fr.sii.sonar.report.core.test.domain.TestReport;
import fr.sii.sonar.report.core.test.domain.TestStats;
import fr.sii.sonar.test.junit.domain.v6.Error;
import fr.sii.sonar.test.junit.domain.v6.Failure;
import fr.sii.sonar.test.junit.domain.v6.Skipped;
import fr.sii.sonar.test.junit.domain.v6.Testcase;
import fr.sii.sonar.test.junit.domain.v6.Testsuite;
import fr.sii.sonar.test.junit.domain.v6.Testsuites;

public class JUnitV6Adapter implements JUnitAdapter<Testsuites> {

	public TestReport adapt(Testsuites root) {
		return new TestReport(convert(root.getTestsuite()));
	}

	private List<TestFile> convert(List<Testsuite> suites) {
		List<TestFile> testFiles = new ArrayList<TestFile>();
		for(Testsuite suite : suites) {
			testFiles.add(convert(suite));
		}
		return testFiles;
	}
	
	private TestFile convert(Testsuite suite) {
		List<TestCase> testCases = new ArrayList<TestCase>();
		for(Testcase testcase : suite.getTestcase()) {
			testCases.add(convert(testcase));
		}
		return new TestFile(suite.getPackage().replaceAll("\\.", "/")+"/"+suite.getName(), stats(suite), testCases);
	}

	private TestCase convert(Testcase testCase) {
		String name = testCase.getName();
		long duration = AdapterHelper.getDuration(testCase.getTime());
		if(testCase.getFailure()!=null && testCase.getFailure().size()>0) {
			return TestCase.failure(name, duration, getFailureMessage(testCase.getFailure()));
		} else if(testCase.getError()!=null && testCase.getError().size()>0) {
			List<Error> errors = testCase.getError();
			return TestCase.error(name, duration, getMessage(errors), getStackTrace(errors));
		} else if(testCase.getSkipped()!=null) {
			return TestCase.skipped(name, getMessage(testCase.getSkipped()));
		}
		return TestCase.ok(name, duration);
	}
	

	private String getMessage(Skipped skipped) {
		return skipped.getMessage();
	}

	private String getMessage(List<Error> errors) {
		String message = "";
		for(Error err : errors) {
			message += err.getMessage();
		}
		return message;
	}

	private String getStackTrace(List<Error> errors) {
		String message = "";
		for(Error err : errors) {
			message += err.getContent();
		}
		return message;
	}

	private String getFailureMessage(List<Failure> failures) {
		String message = "";
		for(Failure f : failures) {
			message += f.getContent();
		}
		return message;
	}

	private TestStats stats(Testsuite suite) {
		int total = AdapterHelper.getInt(suite.getTests());
		int failures = AdapterHelper.getInt(suite.getFailures());
		int errors = AdapterHelper.getInt(suite.getErrors());
		int skipped = AdapterHelper.getInt(suite.getSkipped());
		return new TestStats(total, failures, errors, skipped, AdapterHelper.getDuration(suite.getTime()));
	}
	

	
}
