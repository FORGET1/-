package com.pingdu.serviceImpl.licenseServiceImpl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pingdu.dao.licenseDao.LicenseTypeDao;
import com.pingdu.entity.license.LicenseType;
import com.pingdu.entity.license.LicenseTypeReturn;
import com.pingdu.service.licenseService.LicenseTypeService;

@Service
public class LicenseTypeServiceImpl implements LicenseTypeService{
	@Autowired
	private LicenseTypeDao licenseTypeDao;
	
//	@Override
//	public String getNameByTypeCode(int licTypeCode) {
//		// 根据证照类型名获得证照名称
//		return licenseTypeDao.getNameByCode(licTypeCode);
//	}
//
//	@Override
//	public int getTypeCodeByName(String licName) {
//		// 根据证照名称获得证照类型
//		return licenseTypeDao.getCodeByName(licName);
//	}

	@Override
	public LicenseType getLicenseTypeInfo(int licTypeCode) {
		return licenseTypeDao.findById(licTypeCode);
	}

	@Override
	public LicenseType findByName(String licName) {
		return licenseTypeDao.findByName(licName);
	}

	@Override
	public int calPage(int page) {
		return licenseTypeDao.calPage(page);
	}

	@Override
	public int calPageSearch(String searchType, String keyword) {
		// TODO Auto-generated method stub
		return licenseTypeDao.calPageSearch(searchType, keyword);
	}

	@Override
	public List<LicenseTypeReturn> getLicenseTypeList(int page) {
		List<LicenseTypeReturn> getLicenseList=licenseTypeDao.getLicenseTypeList(page);	
		return getLicenseList;
	}

	@Override
	public List<LicenseTypeReturn> LicenseTypeSpecList(String searchType,
			String keyword) {
		// TODO Auto-generated method stub
		List<LicenseTypeReturn> LicenseTypeSpecList=licenseTypeDao.LicenseTypeSpecList(searchType, keyword);
		return LicenseTypeSpecList;
	}

//	@Override
//	public boolean insertaddLicense(String licName, int validTerm,
//			String entTypeName) {
//		// TODO Auto-generated method stub
//		return licenseTypeDao.insertaddLicense(licName, validTerm, entTypeName);
//		
//	}
//
//	@Override
//	public boolean deleteLicenseType(String entTypeName, int licTyoeCode) {
//		// TODO Auto-generated method stub
//		return licenseTypeDao.deleteLicenseType(entTypeName, licTyoeCode);
//		
//	}
}
