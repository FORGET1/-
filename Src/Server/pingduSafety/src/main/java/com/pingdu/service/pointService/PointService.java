package com.pingdu.service.pointService;

import java.util.List;
import java.util.Set;

import com.pingdu.entity.item.Item;
import com.pingdu.entity.point.Point;
import com.pingdu.view.PointView;

public interface PointService {

	
	public List<PointView> getByEntCode(Integer entCode,Integer page);
	
	public Point find(Integer pointCode);
	
	public String update( Point point,Integer entCode);
	
	public String delete(Integer pointCode);
	
	public String add(Point point,Integer entCode);
	
	public List<PointView> search(String type, String key,Integer page);
	
	public List<Point> getPointByEntCode(Integer entCode,Integer page);
	
	public Integer getSearchPage(String type, String key);
	
	public Integer getPageByEntCode(Integer entCode);
	
	public PointView getByPointCode(Integer pointCode);

	public PointView getPointInfo(Integer pointCode);
	
	public List<Point> getAllPointsByEntCode(Integer entCode);
	
	public List<Point> getAllPoint();
	
	public Set<Point> getListByPointCodeList(List<Integer> pointCodes);
	
	public List<PointView> getAllPoints(Integer entCode);
	
	public List<PointView> getByDeptCode(Integer deptCode,Integer page);
	
	public Integer getPageByDeptCode(Integer deptCode);
	
	public List<Item> getItemsByPointCode(Integer pointCode);
	
	public String deleteItemFromPoint(Integer pointCode, Integer itemCode);
	
	public String addItemsToPoint(Integer pointCode, List<Integer> itemCodes);
		
}
