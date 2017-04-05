package com.pingdu.service.licenseService;

import java.util.List;

import com.pingdu.entity.license.License;

public interface LicenseService {
	public List<License> getLicenseList(int page,int rows);
	public boolean del(int entCode,int licenseCode);
	public String getLicenseImg(int entCode,int licenseCode);
	public List<License> search(String searchType,Object keyWord,int page,int rows);
	public Integer calSum();
	public License getLicenseInfo(int entCode,int licenseCode);
	public Integer calPageOfSearch(String type, Object key);


}
