package com.pingdu.serviceImpl.entTypeServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pingdu.dao.entTypeDao.EntTypeDao;
import com.pingdu.entity.entType.EntType;
import com.pingdu.service.entTypeService.EntTypeService;

@Service
public class EntTypeServiceImpl implements EntTypeService {

	@Autowired
	EntTypeDao entTypeDao;
	
	@Override
	public EntType getEntTypeByCode(int entTypeCode) {
		
		return entTypeDao.getEntTypeByCode(entTypeCode);
	}

}
