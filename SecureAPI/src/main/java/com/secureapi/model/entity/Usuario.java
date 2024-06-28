package com.secureapi.model.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotBlank;

import com.secureapi.core.entity.AbstractEntity;

@Entity
public class Usuario extends AbstractEntity {

	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "USUARIO_GENERATOR", sequenceName = "IDUSUARIO", allocationSize = 1)
	@GeneratedValue(generator = "USUARIO_GENERATOR", strategy = GenerationType.SEQUENCE)
	private Long idUsuario;

	@NotBlank
	private String nome;
	
	private boolean administrador;
	
	@NotBlank
	private String login;
	
	@NotBlank
	private String senha;
	
	public Long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public boolean isAdministrador() {
		return administrador;
	}

	public void setAdministrador(boolean administrador) {
		this.administrador = administrador;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	@Override
	public Long getId() {
		return getIdUsuario();
	}

}
