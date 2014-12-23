package cn.com.uwoa.global.exception;

public class ServiceException extends RuntimeException {

	private static final long serialVersionUID = 7842139134982395218L;

	public ServiceException(String message, Throwable cause) {
		super(message, cause);
	}

	public ServiceException(String message) {
		super(message);
	}

	public ServiceException(Throwable cause) {
		super(cause);
	}

}
