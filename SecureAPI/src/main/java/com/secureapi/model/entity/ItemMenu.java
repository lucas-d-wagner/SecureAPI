package com.secureapi.model.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

import com.secureapi.core.entity.AbstractEntity;

@Entity
public class ItemMenu extends AbstractEntity {

	private static final long serialVersionUID = 1L;

	@Id
	private Long idItemMenu;

	@NotBlank
	private String descricao;
	
	@NotBlank
	private String path;
	
	public ItemMenu() {}
	
	public ItemMenu(Long idItemMenu, String path, String descricao) {
		this.idItemMenu = idItemMenu;
		this.path = path;
		this.descricao = descricao;
	}
	
	public Long getIdItemMenu() {
		return idItemMenu;
	}

	public void setIdItemMenu(Long idItemMenu) {
		this.idItemMenu = idItemMenu;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	@Override
	public Long getId() {
		return getIdItemMenu();
	}
}
