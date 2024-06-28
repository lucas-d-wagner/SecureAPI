package com.secureapi.api.context;

import java.io.Serializable;
import java.util.Date;

public class LoginContext implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long idUsuario;
	private String usuario;
	private Date dataLogin;
	private boolean admin;
	
	public LoginContext() { }
	
	public LoginContext(Long idUsuario, String usuario) {
		this.idUsuario = idUsuario;
		this.usuario = usuario;
		this.dataLogin = new Date();
		this.admin = false;
	}
	
	public LoginContext(Long idUsuario, String usuario, boolean admin) {
		this.idUsuario = idUsuario;
		this.usuario = usuario;
		this.dataLogin = new Date();
		this.admin = admin;
	}

	public Long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public Date getDataLogin() {
		return dataLogin;
	}

	public void setDataLogin(Date dataLogin) {
		this.dataLogin = dataLogin;
	}

	public boolean isAdmin() {
		return admin;
	}

	public void setAdmin(boolean admin) {
		this.admin = admin;
	}

}
