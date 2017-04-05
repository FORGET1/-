package com.pingdu.utility;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.pingdu.manager.ThreadLocalManager;


public class EntityManagerAspect {

	
	private EntityManager entityManager;
	public void initEm() {
		if (getEntityManager() != null) {
			ThreadLocalManager.associateWithThread(getEntityManager());
			
			
		}
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}

	
	@PersistenceContext(unitName = "pingduSafety")
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
}
