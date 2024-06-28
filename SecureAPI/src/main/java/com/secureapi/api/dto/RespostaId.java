package com.secureapi.api.dto;

import java.io.Serializable;

import com.secureapi.core.entity.AbstractEntity;

public class RespostaId implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long id;

	public RespostaId() {}

	public RespostaId(Long id) {
		this.id = id;
	}
	
	public <E extends AbstractEntity> RespostaId(E entity) {
		this.id = entity.getId();
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
}
