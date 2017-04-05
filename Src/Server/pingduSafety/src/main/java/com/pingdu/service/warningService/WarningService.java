package com.pingdu.service.warningService;

import java.util.List;
import java.util.Map;

import com.pingdu.entity.warning.Warning;
import com.pingdu.view.WarningView;


public interface WarningService {

	public List<WarningView> getWarningList(int page);

	public boolean deleteByWarningCode(int warningCode);

	public List<WarningView> searchByKey(String keyWord, String searchType, String beginTime, String endTime,
			int page);

	public int sumPage();

	public int searchPage(String keyWord, String searchType, String beginTime, String endTime);

	public Map<String, Object> getLastWarningTitle();

	public WarningView getWarningInfo(int warningCode);

	public boolean addWarning(Warning warning);

}
