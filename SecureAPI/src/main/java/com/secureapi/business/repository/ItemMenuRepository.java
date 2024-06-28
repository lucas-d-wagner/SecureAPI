package com.secureapi.business.repository;

import java.util.List;

import javax.annotation.ManagedBean;
import javax.persistence.TypedQuery;

import com.secureapi.core.persistence.AbstractRepository;
import com.secureapi.model.entity.ItemMenu;
import com.secureapi.model.entity.Usuario;

@ManagedBean
public class ItemMenuRepository extends AbstractRepository<ItemMenu> {

	@Override
	public Class<ItemMenu> getEntityClass() {
		return ItemMenu.class;
	}
	
	public List<ItemMenu> findByUsuarioComPermissao(Usuario usuario) {
		StringBuilder sb = new StringBuilder();
		
		sb.append("SELECT p.itemMenu FROM PermissaoUsuario p WHERE p.usuario = :usuario");
		
		TypedQuery<ItemMenu> typedQuery = createTypedQuery(sb.toString());
		typedQuery.setParameter("usuario", usuario);
		return getResultList(typedQuery);
	}

}
