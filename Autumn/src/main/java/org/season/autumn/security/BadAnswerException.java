package org.season.autumn.security;

import org.springframework.security.core.AuthenticationException;

public class BadAnswerException extends AuthenticationException {

	private static final long serialVersionUID = 1L;

	public BadAnswerException(String msg) {
		super(msg);
	}

}
