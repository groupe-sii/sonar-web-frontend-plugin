package fr.sii.sonar.report.core.test.domain;

import org.sonar.api.test.TestCase.Status;

/**
 * Provides information for a single test :
 * <ul>
 * <li>the test name</li>
 * <li>the test duration</li>
 * <li>the test status (OK, ERROR, FAILURE, SKIPPED)</li>
 * <li>a message for the test</li>
 * <li>the type of the test (unit test or integration test)</li>
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
	 * The test duration
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
	 * The test type
	 */
	private Type type;

	/**
	 * The stack trace when test has failed
	 */
	private String stackTrace;

	/**
	 * Initialize a test case for unit testing with provided name and duration.
	 * The test case is initialized with OK status, no message and no stack
	 * trace.
	 * 
	 * @param name
	 *            the test name
	 * @param duration
	 *            the test duration
	 * @return the test case
	 */
	public static TestCase unitTestOk(String name, long duration) {
		return new TestCase(name, duration, Type.UNIT);
	}

	/**
	 * Initialize a test case for unit testing with provided name. The test case
	 * is initialized with SKIPPED status, no message, no duration and no stack
	 * trace.
	 * 
	 * @param name
	 *            the test name
	 * @param duration
	 *            the test duration
	 * @return the test case
	 */
	public static TestCase unitTestSkipped(String name) {
		return new TestCase(name, 0, Status.SKIPPED, null, Type.UNIT);
	}

	/**
	 * Initialize a test case for unit testing with provided name and message.
	 * The test case is initialized with SKIPPED status, no duration and no
	 * stack trace.
	 * 
	 * @param name
	 *            the test name
	 * @param message
	 *            the information message indicating why the test was skipped
	 * @param duration
	 *            the test duration
	 * @return the test case
	 */
	public static TestCase unitTestSkipped(String name, String message) {
		return new TestCase(name, 0, Status.SKIPPED, message, Type.UNIT);
	}

	/**
	 * Initialize a test case for unit testing with provided name, duration,
	 * assertion failure message. The test case is initialized with ERROR status
	 * and no stack trace.
	 * 
	 * @param name
	 *            the test name
	 * @param duration
	 *            the test duration
	 * @param message
	 *            the assertion failure message
	 * @return the test case
	 */
	public static TestCase unitTestFailure(String name, long duration, String message) {
		return new TestCase(name, duration, Status.ERROR, message, Type.UNIT);
	}

	/**
	 * Initialize a test case for unit testing with provided name, duration,
	 * message and stack trace. The test case is initialized with FAILURE
	 * status.
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
	public static TestCase unitTestError(String name, long duration, String message, String stackTrace) {
		return new TestCase(name, duration, Status.ERROR, message, Type.UNIT, stackTrace);
	}

	/**
	 * Initialize a test case for integration testing with provided name and duration.
	 * The test case is initialized with OK status, no message and no stack
	 * trace.
	 * 
	 * @param name
	 *            the test name
	 * @param duration
	 *            the test duration
	 * @return the test case
	 */
	public static TestCase integrationTestOk(String name, long duration) {
		return new TestCase(name, duration, Type.UNIT);
	}

	/**
	 * Initialize a test case for integration testing with provided name. The test case
	 * is initialized with SKIPPED status, no message, no duration and no stack
	 * trace.
	 * 
	 * @param name
	 *            the test name
	 * @param duration
	 *            the test duration
	 * @return the test case
	 */
	public static TestCase integrationTestSkipped(String name) {
		return new TestCase(name, 0, Status.SKIPPED, null, Type.UNIT);
	}

	/**
	 * Initialize a test case for integration testing with provided name and message.
	 * The test case is initialized with SKIPPED status, no duration and no
	 * stack trace.
	 * 
	 * @param name
	 *            the test name
	 * @param message
	 *            the information message indicating why the test was skipped
	 * @param duration
	 *            the test duration
	 * @return the test case
	 */
	public static TestCase integrationTestSkipped(String name, String message) {
		return new TestCase(name, 0, Status.SKIPPED, message, Type.UNIT);
	}

	/**
	 * Initialize a test case for integration testing with provided name, duration,
	 * assertion failure message. The test case is initialized with ERROR status
	 * and no stack trace.
	 * 
	 * @param name
	 *            the test name
	 * @param duration
	 *            the test duration
	 * @param message
	 *            the assertion failure message
	 * @return the test case
	 */
	public static TestCase integrationTestFailure(String name, long duration, String message) {
		return new TestCase(name, duration, Status.ERROR, message, Type.UNIT);
	}

	/**
	 * Initialize a test case for integration testing with provided name, duration,
	 * message and stack trace. The test case is initialized with FAILURE
	 * status.
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
	public static TestCase integrationTestError(String name, long duration, String message, String stackTrace) {
		return new TestCase(name, duration, Status.ERROR, message, Type.UNIT, stackTrace);
	}

	protected TestCase(String name, long duration, Type type) {
		this(name, duration, Status.OK, null, type);
	}

	protected TestCase(String name, long duration, Status status, String message, Type type) {
		this(name, duration, status, message, type, null);
	}

	protected TestCase(String name, long duration, Status status, String message, Type type, String stackTrace) {
		super();
		this.name = name;
		this.duration = duration;
		this.status = status;
		this.message = message;
		this.type = type;
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

	public Type getType() {
		return type;
	}

	public String getStackTrace() {
		return stackTrace;
	}
}
