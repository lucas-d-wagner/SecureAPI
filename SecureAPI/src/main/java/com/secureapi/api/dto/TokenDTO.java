package com.secureapi.api.dto;

import java.io.Serializable;

public class TokenDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private String token;
	
	public TokenDTO() {}
	
	public TokenDTO(String token) {
		this.token = token;
	}

	public String getToken() {
		return token;
	}
	
	public void setToken(String token) {
		this.token = token;
	}

}
