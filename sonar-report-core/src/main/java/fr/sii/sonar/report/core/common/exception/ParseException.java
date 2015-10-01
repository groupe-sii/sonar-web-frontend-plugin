package fr.sii.sonar.report.core.common.exception;

public class ParseException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4901519347306705513L;

	public ParseException(String message, Throwable cause) {
		super(message, cause);
	}

	public ParseException(String message) {
		super(message);
	}

	public ParseException(Throwable cause) {
		super(cause);
	}

}
