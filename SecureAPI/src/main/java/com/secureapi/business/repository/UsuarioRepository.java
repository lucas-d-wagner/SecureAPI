package com.secureapi.business.repository;

import javax.annotation.ManagedBean;
import javax.persistence.TypedQuery;

import com.secureapi.core.persistence.AbstractRepository;
import com.secureapi.model.entity.Usuario;

@ManagedBean
public class UsuarioRepository extends AbstractRepository<Usuario> {

	@Override
	public Class<Usuario> getEntityClass() {
		return Usuario.class;
	}

	public Usuario findByLogin(String login) {
		TypedQuery<Usuario> typedQuery = createTypedQuery("SELECT u FROM Usuario u WHERE u.login = :login");
		typedQuery.setParameter("login", login);
		return getSingleResult(typedQuery);
	}
	
}
