package com.secureapi.core.config;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.secureapi.core.entity.AbstractEntity;
import com.secureapi.model.entity.ItemMenu;
import com.secureapi.model.enums.EnumItemMenu;

@Startup
@Singleton
public class ConfiguradorSecureAPI {

	@PersistenceContext
	private EntityManager entityManager;
	
    @PostConstruct
    public void init() {
        configurar();
    }
    
    private void configurar() {
    	popularEnums();
    }
    
    private void popularEnums() {
    	popularItemMenu();
    }
    
    private void popularItemMenu() {
    	for (EnumItemMenu numItemMenu : EnumItemMenu.values()) {
    		ItemMenu itemMenu = numItemMenu.getEntity();
    		mergeOrPersist(ItemMenu.class, itemMenu);
    	}
    }
    
    private <E extends AbstractEntity> void mergeOrPersist(Class<E> clazz, E entity) {
        E managedEntity = entityManager.find(clazz, entity.getId());
        if (managedEntity != null) {
            entityManager.merge(entity);
        } else {
            entityManager.persist(entity);
        }
    }

}
