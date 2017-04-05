package com.pingdu.serviceImpl.licenseServiceImpl;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pingdu.dao.licenseDao.LicType_entTypeDao;
import com.pingdu.entity.license.LicType_entType;
import com.pingdu.service.licenseService.LicType_entTypeService;
import com.pingdu.utility.PublicVariable;

@Service
public class LicType_entTypeServiceImpl implements LicType_entTypeService{
	@Autowired
	private LicType_entTypeDao licType_entTypeDao;
	@Override
	public Integer calSum() {
		// TODO Auto-generated method stub
		return licType_entTypeDao.sum(PublicVariable.rows);
	}

	@Override
	public List<LicType_entType> getLicType_entTypeList(int page, int rows) {
		// 获取证照列表
		List<LicType_entType> all = licType_entTypeDao.getLicType_entTypeList(page, rows);
		return all;
	}



}
