package fr.sii.sonar.report.core.common.exception;

public class ProviderException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8659327815280791037L;

	public ProviderException() {
	}

	public ProviderException(String message) {
		super(message);
	}

	public ProviderException(Throwable cause) {
		super(cause);
	}

	public ProviderException(String message, Throwable cause) {
		super(message, cause);
	}

}
