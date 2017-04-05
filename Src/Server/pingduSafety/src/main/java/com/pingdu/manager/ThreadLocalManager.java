package com.pingdu.manager;

import javax.persistence.EntityManager;

public class ThreadLocalManager {


	private static final ThreadLocal<EntityManager> THREAD_WITH_EM = new ThreadLocal<EntityManager>();
	
	
	private ThreadLocalManager() {
		
	}

	public static void associateWithThread(EntityManager entityManager) {
		THREAD_WITH_EM.set(entityManager);
	}

	public static EntityManager em() { 
		return THREAD_WITH_EM.get();
	}

	public static void cleanupThread() {
		THREAD_WITH_EM.remove();
	}
}
