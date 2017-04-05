package com.pingdu.serviceImpl.licenseServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pingdu.dao.licenseDao.LicenseDao;
import com.pingdu.entity.license.License;
import com.pingdu.service.licenseService.LicenseService;
import com.pingdu.utility.PublicVariable;

@Service
public class LicenseServiceImpl implements LicenseService{

	@Autowired
	private LicenseDao licenseDao;
	
	@Override
	public Integer calSum() {
		// TODO Auto-generated method stub
		return licenseDao.sum(PublicVariable.rows);
	}
	
	@Override
	public List<License> getLicenseList(int page,int rows) {
		// 获取证照列表
		List<License> allLicenses = licenseDao.getLicenseList(page, rows);
		return allLicenses;
	}

	@Override
	public boolean del(int entCode, int licenseCode) {
		// 删除证照
		return licenseDao.delete(entCode,licenseCode);
	}

	@Override
	public String getLicenseImg(int entCode, int licenseCode) {
		// 获取证照照片
		return licenseDao.getLicenseImgSrc(entCode, licenseCode);
	}
	@Override
	public License getLicenseInfo(int entCode, int licenseCode) {
		// TODO Auto-generated method stub
		return licenseDao.getLicenseInfo(entCode, licenseCode);
	}
	@Override
	public Integer calPageOfSearch(String type, Object key) {
		return licenseDao.sumOfSearch(type,key);
	}

	@Override
	public List<License> search(String searchType, Object keyWord, int page, int rows) {
		// 通过关键字和查询类型查询用户
		return licenseDao.searchLicenses( searchType,keyWord, page, rows);
	}

}
