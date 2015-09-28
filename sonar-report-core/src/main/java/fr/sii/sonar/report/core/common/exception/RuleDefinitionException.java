package fr.sii.sonar.report.core.common.exception;

public class RuleDefinitionException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4426700828332473730L;

	public RuleDefinitionException(String message, Throwable cause) {
		super(message, cause);
	}

	public RuleDefinitionException(String message) {
		super(message);
	}

	public RuleDefinitionException(Throwable cause) {
		super(cause);
	}

}
