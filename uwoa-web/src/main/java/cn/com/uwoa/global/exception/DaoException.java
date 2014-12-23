package cn.com.uwoa.global.exception;

public class DaoException extends RuntimeException {

	private static final long serialVersionUID = -1710141121114550002L;

	public DaoException(String message, Throwable cause) {
		super(message, cause);
	}

	public DaoException(String message) {
		super(message);
	}

	public DaoException(Throwable cause) {
		super(cause);
	}

}
