package fr.sii.sonar.report.core.test.domain;

import org.sonar.api.test.TestCase.Status;

/**
 * Provides information for a single test :
 * <ul>
 * <li>the test name</li>
 * <li>the test duration (in milliseconds)</li>
 * <li>the test status (OK, ERROR, FAILURE, SKIPPED)</li>
 * <li>a message for the test</li>
 * <li>the error/failure stack trace</li>
 * </ul>
 * 
 * @author Aurélien Baudet
 *
 */
public class TestCase {
	/**
	 * The test name
	 */
	private String name;

	/**
	 * The test duration (in milliseconds)
	 */
	private long duration;

	/**
	 * The test status
	 */
	private Status status;

	/**
	 * The test message
	 */
	private String message;

	/**
	 * The stack trace when test has failed
	 */
	private String stackTrace;


	/**
	 * Initialize a test case with provided name and duration. The test case is
	 * initialized with OK status, no message and no stack trace.
	 * 
	 * @param name
	 *            the test name
	 * @param duration
	 *            the test duration
	 * @return the test case
	 */
	public static TestCase ok(String name, long duration) {
		return new TestCase(name, duration);
	}

	/**
	 * Initialize a test case for integration testing with provided name. The
	 * test case is initialized with SKIPPED status, no message, no duration and
	 * no stack trace.
	 * 
	 * @param name
	 *            the test name
	 * @return the test case
	 */
	public static TestCase skipped(String name) {
		return new TestCase(name, 0, Status.SKIPPED, null);
	}

	/**
	 * Initialize a test case with provided name and message. The test case is
	 * initialized with SKIPPED status, no duration and no stack trace.
	 * 
	 * @param name
	 *            the test name
	 * @param message
	 *            the information message indicating why the test was skipped
	 * @param duration
	 *            the test duration
	 * @return the test case
	 */
	public static TestCase skipped(String name, String message) {
		return new TestCase(name, 0, Status.SKIPPED, message);
	}

	/**
	 * Initialize a test case with provided name, duration, assertion failure
	 * message. The test case is initialized with ERROR status and no stack
	 * trace.
	 * 
	 * @param name
	 *            the test name
	 * @param duration
	 *            the test duration
	 * @param message
	 *            the assertion failure message
	 * @return the test case
	 */
	public static TestCase failure(String name, long duration, String message) {
		return new TestCase(name, duration, Status.FAILURE, message);
	}

	/**
	 * Initialize a test case with provided name, duration, message and stack
	 * trace. The test case is initialized with FAILURE status.
	 * 
	 * @param name
	 *            the test name
	 * @param duration
	 *            the test duration
	 * @param message
	 *            the error message
	 * @param stackTrace
	 *            the error stack trace
	 * @return the test case
	 */
	public static TestCase error(String name, long duration, String message, String stackTrace) {
		return new TestCase(name, duration, Status.ERROR, message, stackTrace);
	}

	protected TestCase(String name, long duration) {
		this(name, duration, Status.OK, null);
	}

	protected TestCase(String name, long duration, Status status, String message) {
		this(name, duration, status, message, null);
	}

	protected TestCase(String name, long duration, Status status, String message, String stackTrace) {
		super();
		this.name = name;
		this.duration = duration;
		this.status = status;
		this.message = message;
		this.stackTrace = stackTrace;
	}

	public String getName() {
		return name;
	}

	public long getDuration() {
		return duration;
	}

	public Status getStatus() {
		return status;
	}

	public String getMessage() {
		return message;
	}

	public String getStackTrace() {
		return stackTrace;
	}
}
