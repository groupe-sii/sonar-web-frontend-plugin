package fr.sii.sonar.report.core.common.util.compat;

public class UnhandledException extends RuntimeException {

	public UnhandledException(String message, Throwable cause) {
		super(message, cause);
	}

	public UnhandledException(String message) {
		super(message);
	}

	public UnhandledException(Throwable cause) {
		super(cause);
	}

}
