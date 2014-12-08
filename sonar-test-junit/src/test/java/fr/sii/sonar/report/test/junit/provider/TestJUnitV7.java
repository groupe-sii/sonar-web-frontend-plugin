package fr.sii.sonar.report.test.junit.provider;

import java.io.InputStream;

import org.junit.Assert;
import org.junit.Test;
import org.sonar.api.test.TestCase.Status;

import fr.sii.sonar.report.core.common.exception.ProviderException;
import fr.sii.sonar.report.core.test.domain.TestCase;
import fr.sii.sonar.report.core.test.domain.TestFile;
import fr.sii.sonar.report.core.test.domain.TestReport;
import fr.sii.sonar.report.test.junit.provider.adapter.JUnitV7Adapter;
import fr.sii.sonar.test.junit.domain.v7.Testsuites;

public class TestJUnitV7 {
	@Test
	public void sample() throws ProviderException {
		InputStream sample = getClass().getResourceAsStream("/sample.xml");
		JUnitProvider<Testsuites> provider = new JUnitProvider<Testsuites>(sample, Testsuites.class, new JUnitV7Adapter());
		TestReport testReport = provider.get();
		Assert.assertEquals("test report should contain one file", 1, testReport.getFiles().size());
		TestFile firstFile = testReport.getFiles().get(0);
		Assert.assertEquals("first file should contain 3 test cases", 3, firstFile.getTestCases().size());
		TestCase firstTestCase = firstFile.getTestCases().get(0);
		Assert.assertEquals("first test case should be a failure", Status.FAILURE, firstTestCase.getStatus());
		Assert.assertEquals("first test case message should be 'Assertion failed'", "Assertion failed", firstTestCase.getMessage());
		Assert.assertEquals("first test case duration should be 6 ms", 6, firstTestCase.getDuration());
		TestCase secondTestCase = firstFile.getTestCases().get(1);
		Assert.assertEquals("second test case should be skipped", Status.SKIPPED, secondTestCase.getStatus());
		TestCase thirdTestCase = firstFile.getTestCases().get(2);
		Assert.assertEquals("second test case should be ok", Status.OK, thirdTestCase.getStatus());
	}
}
