package com.secureapi.business.exception;

import javax.ejb.ApplicationException;

@ApplicationException(rollback = true)
public class BusinessCheckedException extends Exception {

	private static final long serialVersionUID = 1L;

	public BusinessCheckedException() { }

	public BusinessCheckedException(String message, Throwable cause) {
		super(message, cause);
	}

	public BusinessCheckedException(String message) {
		super(message);
	}

	public BusinessCheckedException(Throwable cause) {
		super(cause);
	}
	
}
