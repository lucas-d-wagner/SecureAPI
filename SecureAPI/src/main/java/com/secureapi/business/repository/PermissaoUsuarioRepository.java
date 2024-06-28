package com.secureapi.business.repository;

import java.util.List;

import javax.annotation.ManagedBean;
import javax.persistence.TypedQuery;

import com.secureapi.core.persistence.AbstractRepository;
import com.secureapi.model.entity.ItemMenu;
import com.secureapi.model.entity.PermissaoUsuario;
import com.secureapi.model.entity.Usuario;

@ManagedBean
public class PermissaoUsuarioRepository extends AbstractRepository<PermissaoUsuario> {

	@Override
	public Class<PermissaoUsuario> getEntityClass() {
		return PermissaoUsuario.class;
	}

	public List<PermissaoUsuario> findByUsuario(Usuario usuario) {
		TypedQuery<PermissaoUsuario> typedQuery = createTypedQuery("SELECT p FROM PermissaoUsuario p WHERE p.usuario = :usuario");
		typedQuery.setParameter("usuario", usuario);
		return getResultList(typedQuery);
	}
	
	public PermissaoUsuario findByUsuarioAndItemMenu(Usuario usuario, ItemMenu itemMenu) {
		TypedQuery<PermissaoUsuario> typedQuery = createTypedQuery("SELECT p FROM PermissaoUsuario p WHERE p.usuario = :usuario AND p.itemMenu = :itemMenu");
		typedQuery.setParameter("usuario", usuario);
		typedQuery.setParameter("itemMenu", itemMenu);
		return getSingleResult(typedQuery);
	}
	
}
