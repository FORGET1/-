package com.pingdu.serviceImpl.rescueServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pingdu.dao.rescueDao.RescueDao;
import com.pingdu.entity.rescue.Rescue;
import com.pingdu.service.rescueService.RescueService;
import com.pingdu.utility.PublicVariable;

@Service
public class RescueServiceImpl implements RescueService{

	@Autowired 
	RescueDao dao;
	@Override
	public Integer calSum() {
		// TODO Auto-generated method stub
		return dao.sum(PublicVariable.rows);
	}

	@Override
	public List<Rescue> getRescuesList(int page, int rows) {
		// TODO Auto-generated method stub
		return dao.getRescuesList(page,rows);
	}
	
	@Override
	public boolean delete(int rescueCode) {
		// TODO Auto-generated method stub
		return dao.delete(rescueCode);
	}

	@Override
	public Integer sumOfSearch(String type, String keyWord) {
		// TODO Auto-generated method stub
		return dao.sumOfSearch(type,keyWord);
	}

	@Override
	public List<Rescue> search(String searchType, String keyWord, int page, int rows) {
		// TODO Auto-generated method stub
		return dao.search(searchType,keyWord,page,rows);
	}

	@Override
	public boolean modify(Rescue rescue) {
		// TODO Auto-generated method stub
		return dao.modify(rescue);
	}

	@Override
	public boolean add(Rescue rescue) {
		// TODO Auto-generated method stub
		return dao.add(rescue);
	}

	@Override
	public List<Rescue> getAllRescuesList() {
		
		
		return dao.getAllRescuesList();
	}

}
