package com.secureapi.api.dto;

import java.io.Serializable;

public class ExceptionDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private String message;
		
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
}
