package fr.sii.sonar.report.test.helper;

import org.sonar.api.test.TestCase.Status;

import fr.sii.sonar.report.core.test.domain.TestCase;

public class ExpectedTestCase extends TestCase {

	public ExpectedTestCase() {
		super(null, 0);
	}
	
	public ExpectedTestCase(String name, long duration, Status status, String message, String stackTrace) {
		super(name, duration, status, message, stackTrace);
	}

	public ExpectedTestCase(String name, long duration, Status status, String message) {
		super(name, duration, status, message);
	}

	public ExpectedTestCase(String name, long duration) {
		super(name, duration);
	}

	public ExpectedTestCase name(String name) {
		this.name = name;
		return this;
	}
	
	public ExpectedTestCase duration(long duration) {
		this.duration = duration;
		return this;
	}
	
	public ExpectedTestCase status(Status status) {
		this.status = status;
		return this;
	}
	
	public ExpectedTestCase message(String message) {
		this.message = message;
		return this;
	}
	
	public ExpectedTestCase stacktrace(String stacktrace) {
		this.stackTrace = stacktrace;
		return this;
	}
}
