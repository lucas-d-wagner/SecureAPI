package com.secureapi.core.persistence;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

import javax.annotation.ManagedBean;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.secureapi.business.exception.BusinessException;
import com.secureapi.core.entity.AbstractEntity;

@ManagedBean
public abstract class AbstractRepository<E extends AbstractEntity> {

	@PersistenceContext
	private EntityManager entityManager;
	
	public List<E> findAll() {
		StringBuilder query = new StringBuilder();
		query.append("select entity from ").append(getEntityName()).append(" entity");
		return getResultList(createTypedQuery(query));
	}

	public E find(Long id) {
		return entityManager.find(getEntityClass(), id);
	}

	public E findNotNull(Long id) {
		E entity = find(id);
		if (Objects.isNull(entity)) {
			throw new BusinessException("Não encontrou entidade " + getEntityName() + " com o id " + id + ".");
		}
		return entity;
	}

	public E persist(E entity) {
		entityManager.persist(entity);
		return entity;
	}

	public E merge(E entity) {
		entityManager.merge(entity);
		return entity;
	}

	public E mergeOrPersist(E entity) {
		if(entity.getId() == null) {
			entity = persist(entity);
		} else {
			entity = merge(entity);
		}
		return entity;
	}

	public void remove(E entity) {
		entityManager.remove(entity);
	}
	
	public TypedQuery<E> createTypedQuery(StringBuilder query) {
		return createTypedQuery(query.toString());
	}

	public TypedQuery<E> createTypedQuery(String query) {
		return entityManager.createQuery(query, getEntityClass());
	}
	
	public List<E> getResultList(TypedQuery<E> typedQuery) {
		try {
			return typedQuery.getResultList();
		} catch (NoResultException e) {
			return Collections.emptyList();
		}
	}

	public E getSingleResult(TypedQuery<E> typedQuery) {
		try {
			return typedQuery.getSingleResult();
		} catch (NoResultException e) {
			return null;
		} catch (NonUniqueResultException e) {
			throw new BusinessException("Foi encontrado mais de um resultado para a entidade " + getEntityName() + ".");
		}
	}
	
	public abstract Class<E> getEntityClass();
	
	private String getEntityName() {
		return getEntityClass().getSimpleName();
	}
}