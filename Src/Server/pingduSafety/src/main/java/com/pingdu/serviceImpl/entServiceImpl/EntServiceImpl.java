package com.pingdu.serviceImpl.entServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pingdu.dao.archiveDao.ArchiveDao;
import com.pingdu.dao.archiveDao.ArchiveTypeEntTypeDao;
import com.pingdu.dao.entDao.EntDao;
import com.pingdu.dao.licenseDao.LicType_entTypeDao;
import com.pingdu.dao.licenseDao.LicenseDao;
import com.pingdu.entity.archive.Archive;
import com.pingdu.entity.enterprise.Enterprise;
import com.pingdu.entity.license.License;
import com.pingdu.service.entService.EntService;
import com.pingdu.view.EnterpriseView;

@Service
public class EntServiceImpl implements EntService {

	
	@Autowired
	EntDao entDao;
	@Autowired
	ArchiveDao archiveDao;
	@Autowired
	LicenseDao licenseDao;
	@Autowired
	LicType_entTypeDao licType_entTypeDao;
	@Autowired
	ArchiveTypeEntTypeDao archiveTypeEntTypeDao;
	
	
	
	@Override
	public List<EnterpriseView> getEnterpriseListByParentCode(int page, int parentCode) {
		
		return entDao.getEnterpriseListByParent(page, parentCode);
	}

	@Override
	public int getSumOfPage(int parentCode) {
		return entDao.getSumOfPage(parentCode);
	}
	
	@Override
	public List<EnterpriseView> getEnterpriseByDept(int page, int deptCode){
		
		return entDao.getEnterpriseByDept(page, deptCode);
	}

	@Override
	public int getSumOfPageByDept(int deptCode) {
		return entDao.getSumOfPageByDept(deptCode);
	}

	@Override
	public boolean deleteEnterprisement(int entCode) {
		return entDao.deleteEnterprisement(entCode);
	}

	@Override
	public boolean addEnterprise(Enterprise enterprise) {
		
		int entCode = entDao.addEnterprisement(enterprise);
		
		List<Integer> licTypeCodes = licType_entTypeDao.getLicenceTypeListByEntTypeCode(enterprise.getEntType().getEntTypeCode());
		List<Integer> archTypeCodes = archiveTypeEntTypeDao.getArchTypeCodeByEntTypeCode(enterprise.getEntType().getEntTypeCode());
		
		for(Integer licTypeCode:licTypeCodes){

			License license = new License();
			license.setEntCode(entCode);
			license.setIsUpload(false);
			license.setLicTypeCode(licTypeCode);
			license.setEntName(enterprise.getEntName());
			licenseDao.mod(license);
		}
		for(Integer archTypeCode:archTypeCodes){

			Archive archive = new Archive();
			archive.setEntCode(entCode);
			archive.setArchTypeCode(archTypeCode);
			archiveDao.addArchive(archive);
		}

		return entCode!=0?true:false;
	}

	@Override
	public EnterpriseView getEnterpriseInfo(Integer entCode) {
		return entDao.getEnterpriseByCode(entCode);
	}

	@Override
	public Enterprise getEnterpriseByCode(Integer entCode) {
		return entDao.findByCode(entCode);
	}

	@Override
	public List<EnterpriseView> ajjSearchEnterpriseByKey(String searchType, String keyword, int entLevel,
			int parentCode) {
		
		return entDao.ajjSearchEnterpriseByKey(searchType, keyword, entLevel, parentCode);
	}

	@Override
	public List<EnterpriseView> ajbSearchEnterpriseByKey(String searchType, String keyword, int entLevel, int deptCode,
			int parentCode) {
		return entDao.ajbSearchEnterpriseByKey(searchType, keyword, entLevel, deptCode, parentCode);
	}

	@Override
	public boolean modifyEnterprise(Enterprise enterprise) {
		return entDao.modifyEnterprise(enterprise);
	}

	@Override
	public List<EnterpriseView> getEnterpriseByDepNoPage(int deptCode) {
		return entDao.getEnterpriseByDepNoPage(deptCode);
	}
	
}
