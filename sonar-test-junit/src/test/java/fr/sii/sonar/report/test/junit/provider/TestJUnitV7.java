package fr.sii.sonar.report.test.junit.provider;

import java.io.InputStream;

import org.junit.Test;
import org.sonar.api.test.TestCase.Status;

import fr.sii.sonar.report.core.common.exception.ProviderException;
import fr.sii.sonar.report.core.test.domain.TestReport;
import fr.sii.sonar.report.test.helper.AssertReport;
import fr.sii.sonar.report.test.helper.ExpectedTestCase;
import fr.sii.sonar.report.test.helper.ExpectedTestFile;
import fr.sii.sonar.report.test.helper.ExpectedTestStats;
import fr.sii.sonar.report.test.junit.provider.adapter.JUnitV7Adapter;
import fr.sii.sonar.test.junit.domain.v7.Testsuites;

public class TestJUnitV7 {
	
	@Test
	public void jasmine() throws ProviderException {
		InputStream sample = getClass().getResourceAsStream("/jasmine.xml");
		JUnitProvider<Testsuites> provider = new JUnitProvider<Testsuites>(sample, Testsuites.class, new JUnitV7Adapter());
		TestReport actual = provider.get();
		// @formatter:off
		TestReport expected = new TestReport().
									addFile(new ExpectedTestFile().
											path("test/spec/services/taskService.js").
											stats(new ExpectedTestStats().
												duration(576L).
												errors(0).
												failures(1).
												passed(9).
												skipped(0).
												total(10)).
											// test cases for taskService defined in PhantomJS test suite
											addTestCase(new ExpectedTestCase().
												duration(178L).
												name("should instantiate rg").
												status(Status.OK)).
											addTestCase(new ExpectedTestCase().
												duration(7L).
												name("should contain a AwardsService").
												status(Status.OK)).
											addTestCase(new ExpectedTestCase().
												duration(2L).
												name("should contain 12 awards").
												status(Status.OK)).
											addTestCase(new ExpectedTestCase().
												duration(1L).
												name("should contain a NotificationService").
												status(Status.OK)).
											addTestCase(new ExpectedTestCase().
												duration(1L).
												name("should contain 4 notifications").
												status(Status.FAILURE).
												message("expected value was 4 but got 3").
												stacktrace("AssertionError: expected value was 4 but got 3")).
											// test cases for taskService defined in Chrome test suite
											addTestCase(new ExpectedTestCase().
												duration(178L).
												name("should instantiate rg 2").
												status(Status.OK)).
											addTestCase(new ExpectedTestCase().
												duration(7L).
												name("should contain a AwardsService 2").
												status(Status.OK)).
											addTestCase(new ExpectedTestCase().
												duration(2L).
												name("should contain 12 awards 2").
												status(Status.OK)).
											addTestCase(new ExpectedTestCase().
												duration(1L).
												name("should contain a NotificationService 2").
												status(Status.OK)).
											addTestCase(new ExpectedTestCase().
												duration(1L).
												name("should contain 4 notifications 2").
												status(Status.OK))).
									addFile(new ExpectedTestFile().
											path("test/spec/services/userService.js").
											stats(new ExpectedTestStats().
												duration(297L).
												errors(1).
												failures(1).
												passed(5).
												skipped(1).
												total(8)).
											// test cases for userService defined in PhantomJS test suite
											addTestCase(new ExpectedTestCase().
												duration(178L).
												name("create basic user").
												status(Status.OK)).
											addTestCase(new ExpectedTestCase().
												duration(7L).
												name("create admin").
												status(Status.OK)).
											addTestCase(new ExpectedTestCase().
												duration(2L).
												name("update user").
												status(Status.FAILURE).
												message("user should be updated").
												stacktrace("AssertionError: user name not updated")).
											addTestCase(new ExpectedTestCase().
												duration(1L).
												name("delete user").
												status(Status.ERROR).
												message("Error: uncaught error").
												stacktrace("Error: uncaught error. stacktrace: ...")).
											// test cases for userService defined in Chrome test suite
											addTestCase(new ExpectedTestCase().
												duration(0).
												name("create basic user 2").
												status(Status.SKIPPED).
												message("skip message")).
											addTestCase(new ExpectedTestCase().
												duration(7L).
												name("create admin 2").
												status(Status.OK)).
											addTestCase(new ExpectedTestCase().
												duration(2L).
												name("update user 2").
												status(Status.OK)).
											addTestCase(new ExpectedTestCase().
												duration(1L).
												name("delete user 2").
												status(Status.OK)));
		// @formatter:on
		AssertReport.assertTestReport(expected, actual);
	}

}
