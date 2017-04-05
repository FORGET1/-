package com.pingdu.service.licenseService;

import java.util.List;

import com.pingdu.entity.license.LicenseType;
import com.pingdu.entity.license.LicenseTypeReturn;

public interface LicenseTypeService {

	public LicenseType getLicenseTypeInfo(int licTypeCode);
	public LicenseType findByName(String licName) ;

	public int calPage(int page);// 计算总页数
	public int calPageSearch(String searchType, String keyword);//计算总页数
	public List<LicenseTypeReturn> LicenseTypeSpecList(String searchType,String keyword);// 根据特定条件查询
//	public boolean insertaddLicense(String licName, int validTerm,String entTypeName) ;// 添加证照类型
//	public boolean deleteLicenseType(String entTypeName, int licTyoeCode);// 删除证照类型
	public List<LicenseTypeReturn> getLicenseTypeList(int page);
}
