package com.secureapi.model.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotNull;

import com.secureapi.core.entity.AbstractEntity;

@Entity
public class PermissaoUsuario extends AbstractEntity {

	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "PERMISSAOUSUARIO_GENERATOR", sequenceName = "IDPERMISSAOUSUARIO", allocationSize = 1)
	@GeneratedValue(generator = "PERMISSAOUSUARIO_GENERATOR", strategy = GenerationType.SEQUENCE)
	private Long idPermissaoUsuario;

	@NotNull
    @ManyToOne
    @JoinColumn(name = "IDUSUARIO")
	private Usuario usuario;

	@NotNull
    @ManyToOne
    @JoinColumn(name = "IDITEMMENU")
	private ItemMenu itemMenu;

	public Long getIdPermissaoUsuario() {
		return idPermissaoUsuario;
	}

	public void setIdPermissaoUsuario(Long idPermissaoUsuario) {
		this.idPermissaoUsuario = idPermissaoUsuario;
	}
	
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public ItemMenu getItemMenu() {
		return itemMenu;
	}

	public void setItemMenu(ItemMenu itemMenu) {
		this.itemMenu = itemMenu;
	}

	@Override
	public Long getId() {
		return getIdPermissaoUsuario();
	}

}
