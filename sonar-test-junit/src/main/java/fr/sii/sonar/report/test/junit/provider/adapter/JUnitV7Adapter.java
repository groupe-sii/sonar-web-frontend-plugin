package fr.sii.sonar.report.test.junit.provider.adapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.xml.bind.JAXBElement;

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

	private Map<String, TestFile> testFilesByPath;
	
	public TestReport adapt(Testsuites root) {
		reset();
		return new TestReport(aggregate(convert(root.getTestsuite())));
	}

	private void reset() {
		testFilesByPath = new HashMap<String, TestFile>();
	}

	private TestFile createTestFile(Testsuite suite) {
		return new TestFile(suite.getFile(), stats(suite));
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
		TestFile file = createTestFile(suite);
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
		String stackTrace = "";
		TestCase newTestCase = null;
		for(Object any : testCase.getSkippedOrErrorOrFailure()) {
			if(any instanceof Failure) {
				newTestCase = TestCase.failure(name, duration, getMessage((Failure) any));
			} else if(any instanceof Error) {
				newTestCase = TestCase.error(name, duration, getMessage((Error) any), stackTrace);
			} else if(any instanceof Skipped) {
				newTestCase = TestCase.skipped(name, getMessage((Skipped) any));
			} else if(any instanceof JAXBElement) {
				stackTrace += ((JAXBElement<String>) any).getValue();
			}
		}
		if(newTestCase == null) {
			newTestCase = TestCase.ok(name, duration);
		}
		if(!stackTrace.isEmpty()) {
			newTestCase.setStackTrace(stackTrace);
		}
		return newTestCase;
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
		return failure.getMessage();
	}

	private TestStats stats(Testsuite suite) {
		int total = AdapterHelper.getInt(suite.getTests());
		int failures = AdapterHelper.getInt(suite.getFailures());
		int errors = AdapterHelper.getInt(suite.getErrors());
		int skipped = AdapterHelper.getInt(suite.getSkipped()) + AdapterHelper.getInt(suite.getDisabled());
		return new TestStats(total, failures, errors, skipped, AdapterHelper.getDuration(suite.getTime()));
	}
	
	private List<TestFile> aggregate(List<TestFile> files) {
		Set<TestFile> aggregated = new HashSet<TestFile>(files.size());
		for(TestFile file : files) {
			aggregated.add(merge(file));
		}
		return new ArrayList<TestFile>(aggregated);
	}
	
	private TestFile merge(TestFile file) {
		TestFile testFile = testFilesByPath.get(file.getPath());
		if(testFile == null) {
			// add the new file
			testFile = file;
			testFilesByPath.put(file.getPath(), testFile);
		} else {
			// aggregate stats and test cases
			testFile.setStats(merge(testFile.getStats(), file.getStats()));
			for(TestCase testCase : file.getTestCases()) {
				testFile.addTestCase(testCase);
			}
		}
		return testFile;
	}

	private TestStats merge(TestStats stats, TestStats statsToAdd) {
		return new TestStats(stats.getTotal()+statsToAdd.getTotal(), 
							stats.getFailures()+statsToAdd.getFailures(),
							stats.getErrors()+statsToAdd.getErrors(),
							stats.getSkipped()+statsToAdd.getSkipped(),
							stats.getDuration()+statsToAdd.getDuration());
	}

	
}
