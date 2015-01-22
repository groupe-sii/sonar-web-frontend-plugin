package fr.sii.sonar.report.core.common.exception;

public class DuplicationException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3849034942618613565L;

	public DuplicationException() {
		super();
	}

	public DuplicationException(String message, Throwable cause) {
		super(message, cause);
	}

	public DuplicationException(String message) {
		super(message);
	}

	public DuplicationException(Throwable cause) {
		super(cause);
	}

}
