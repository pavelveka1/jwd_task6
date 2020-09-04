package by.epamtr.text.controller.exception;

public class ServerControllerException extends Exception {

	private static final long serialVersionUID = 1L;

	public ServerControllerException() {
		super();
	}

	public ServerControllerException(String message, Throwable cause) {
		super(message, cause);
	}

	public ServerControllerException(String message) {
		super(message);
	}

	public ServerControllerException(Throwable cause) {
		super(cause);
	}

}
