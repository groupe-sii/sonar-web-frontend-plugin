package fr.sii.sonar.web.client.core.exception;

public class RuleException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3294454761734818290L;

	public RuleException() {
		super();
	}

	public RuleException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public RuleException(String message, Throwable cause) {
		super(message, cause);
	}

	public RuleException(String message) {
		super(message);
	}

	public RuleException(Throwable cause) {
		super(cause);
	}

}
