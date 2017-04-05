package com.pingdu.serviceImpl.warningServiceImpl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pingdu.dao.warningDao.WarningDao;
import com.pingdu.entity.warning.Warning;
import com.pingdu.service.warningService.WarningService;
import com.pingdu.view.WarningView;

@Service
public class WarningServiceImpl implements WarningService {

	@Autowired
	WarningDao warningDao;
	
	@Override
	public List<WarningView> getWarningList(int page) {
		
		return warningDao.getAll(page);
	}

	@Override
	public boolean deleteByWarningCode(int warningCode) {
		return warningDao.deleteByWarningCode(warningCode);
	}

	@Override
	public List<WarningView> searchByKey(String keyWord, String searchType, String beginTime, String endTime,
			int page) {
		return warningDao.searchByKey(keyWord, searchType, beginTime, endTime, page);
	}

	@Override
	public int sumPage() {
		return warningDao.sumOfPage();
	}

	@Override
	public int searchPage(String keyWord, String searchType, String beginTime, String endTime) {
		return warningDao.searchSum(keyWord, searchType, beginTime, endTime);
	}

	@Override
	public Map<String, Object> getLastWarningTitle() {
		return warningDao.getLastWarningTitle();
	}

	@Override
	public WarningView getWarningInfo(int warningCode) {
		return warningDao.findByWarningCode(warningCode);
	}

	@Override
	public boolean addWarning(Warning warning) {
		return warningDao.addWaining(warning);
	}

}
