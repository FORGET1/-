package com.pingdu.service.entService;

import java.util.List;

import com.pingdu.entity.enterprise.Enterprise;
import com.pingdu.view.EnterpriseView;

public interface EntService {

	public List<EnterpriseView> getEnterpriseListByParentCode(int page,int parentCode);

	public int getSumOfPage(int parentCode);
	
	public List<EnterpriseView> getEnterpriseByDept(int page,int deptCode);

	public int getSumOfPageByDept(int deptCode);
	
	public boolean deleteEnterprisement (int entCode);

	public boolean addEnterprise(Enterprise enterprise);

	public EnterpriseView getEnterpriseInfo(Integer entCode);
	
	public Enterprise getEnterpriseByCode(Integer entCode);
	
	public List<EnterpriseView> ajjSearchEnterpriseByKey(String searchType,String keyword,int entLevel,int parentCode);
	public List<EnterpriseView> ajbSearchEnterpriseByKey(String searchType,String keyword,int entLevel,int deptCode,int parentCode);

	public boolean modifyEnterprise(Enterprise enterprise);

	public List<EnterpriseView> getEnterpriseByDepNoPage(int deptCode);

		
}
