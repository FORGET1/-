package com.pingdu.service.licenseService;

import java.util.List;

import com.pingdu.entity.license.LicType_entType;


public interface LicType_entTypeService {

	public Integer calSum();
	public List<LicType_entType> getLicType_entTypeList(int page,int rows);
}
