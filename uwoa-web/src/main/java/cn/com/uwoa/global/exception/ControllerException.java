package cn.com.uwoa.global.exception;

public class ControllerException extends RuntimeException {

	private static final long serialVersionUID = 8014753093323150186L;
	
	public ControllerException(String message, Throwable cause) {
		super(message, cause);
	}

	public ControllerException(String message) {
		super(message);
	}

	public ControllerException(Throwable cause) {
		super(cause);
	}

}
