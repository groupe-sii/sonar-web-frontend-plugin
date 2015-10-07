package fr.sii.sonar.report.test.helper;

import fr.sii.sonar.report.core.test.domain.TestStats;

public class ExpectedTestStats extends TestStats {

	public ExpectedTestStats() {
		super(0, 0, 0, 0);
	}
	
	public ExpectedTestStats(int total, int passed, int failures, int errors, int skipped, long duration) {
		super(total, passed, failures, errors, skipped, duration);
	}

	public ExpectedTestStats(int total, int failures, int errors, int skipped, long duration) {
		super(total, failures, errors, skipped, duration);
	}

	public ExpectedTestStats(int total, int failures, int errors, long duration) {
		super(total, failures, errors, duration);
	}

	public ExpectedTestStats total(int total) {
		this.total = total;
		return this;
	}
	
	public ExpectedTestStats failures(int failures) {
		this.failures = failures;
		return this;
	}
	
	public ExpectedTestStats errors(int errors) {
		this.errors = errors;
		return this;
	}
	
	public ExpectedTestStats duration(long duration) {
		this.duration = duration;
		return this;
	}
	
	public ExpectedTestStats skipped(int skipped) {
		this.skipped = skipped;
		return this;
	}
	
	public ExpectedTestStats passed(int passed) {
		this.passed = passed;
		return this;
	}
}
