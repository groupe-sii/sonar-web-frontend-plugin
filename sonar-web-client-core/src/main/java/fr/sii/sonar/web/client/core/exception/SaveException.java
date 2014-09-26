package fr.sii.sonar.web.client.core.exception;

public class SaveException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3294454761734818290L;

	public SaveException() {
		super();
	}

	public SaveException(String message, Throwable cause) {
		super(message, cause);
	}

	public SaveException(String message) {
		super(message);
	}

	public SaveException(Throwable cause) {
		super(cause);
	}

}
