package fr.sii.sonar.coverage.lcov.parser;

public class LcovParseException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4380579384584418325L;

	public LcovParseException() {
		super();
	}

	public LcovParseException(String message, Throwable cause) {
		super(message, cause);
	}

	public LcovParseException(String message) {
		super(message);
	}

	public LcovParseException(Throwable cause) {
		super(cause);
	}

}
