package com.pingdu.service.rescueService;

import java.util.List;

import com.pingdu.entity.rescue.Rescue;


public interface RescueService {
	public Integer calSum();
	public List<Rescue> getRescuesList(int page,int rows);
	public boolean delete(int rescueCode);
	public Integer sumOfSearch(String type, String keyWord);
	public List<Rescue> search(String searchType, String keyWord,int page,int rows);
	public boolean modify(Rescue rescue);
	public boolean add(Rescue rescue);
	public List<Rescue> getAllRescuesList();
}
