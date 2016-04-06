package fr.sii.sonar.report.core.test.domain;

/**
 * The test statistics that provides the following information :
 * <ul>
 * <li>The total number of tests</li>
 * <li>The number of passed tests</li>
 * <li>The number of skipped tests</li>
 * <li>The number of failed tests</li>
 * <li>The number of tests in error (uncaught error)</li>
 * <li>the duration for all tests execution (in milliseconds)</li>
 * </ul>
 * 
 * @author Aurélien Baudet
 *
 */
public class TestStats {
	/**
	 * The number of skipped tests
	 */
	protected int skipped;

	/**
	 * The total number of tests
	 */
	protected int total;

	/**
	 * The number of tests in error (uncaught error)
	 */
	protected int errors;

	/**
	 * The number of failed tests (assertion failure)
	 */
	protected int failures;

	/**
	 * The number of passed tests
	 */
	protected int passed;

	/**
	 * The duration for all tests execution (in milliseconds)
	 */
	protected long duration;

	public TestStats(int total, int failures, int errors, long duration) {
		this(total, failures, errors, 0, duration);
	}

	public TestStats(int total, int failures, int errors, int skipped, long duration) {
		this(total, total - errors - failures - skipped, failures, errors, skipped, duration);
	}

	public TestStats(int total, int passed, int failures, int errors, int skipped, long duration) {
		super();
		this.skipped = skipped;
		this.total = total;
		this.errors = errors;
		this.failures = failures;
		this.passed = passed;
		this.duration = duration;
	}

	public int getSkipped() {
		return skipped;
	}

	public int getTotal() {
		return total;
	}

	public int getErrors() {
		return errors;
	}

	public int getFailures() {
		return failures;
	}

	public int getPassed() {
		return passed;
	}

	public long getDuration() {
		return duration;
	}

	@Override
	public String toString() {
		return "{total=" + total + ", errors=" + errors + ", failures=" + failures + ", skipped=" + skipped + ", passed=" + passed + ", duration=" + duration + "}";
	}
}
