package com.pingdu.dao.entTypeDao;

import static com.pingdu.manager.ThreadLocalManager.em;

import org.springframework.stereotype.Repository;

import com.pingdu.entity.entType.EntType;


@Repository
public class EntTypeDao {

	public EntType getEntTypeByCode(Integer entTypeCode){
		
		return em().find(EntType.class, entTypeCode);
	}
	
}
