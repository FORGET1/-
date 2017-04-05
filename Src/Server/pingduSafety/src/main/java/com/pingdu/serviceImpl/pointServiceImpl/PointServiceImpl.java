package com.pingdu.serviceImpl.pointServiceImpl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pingdu.dao.pointDao.PointDao;
import com.pingdu.entity.item.Item;
import com.pingdu.entity.point.Point;
import com.pingdu.service.pointService.PointService;

import com.pingdu.view.PointView;

@Service
public class PointServiceImpl implements PointService{

	@Autowired
	private PointDao pointDao;
	
	@Override
	public List<PointView> getByEntCode(Integer entCode, Integer page ) {
		
		return pointDao.getByEntCode(entCode, page);
	}
	@Override
	public Point find(Integer pointCode) {
		Point p = pointDao.find(pointCode);
		return p;
	}
	
	@Override
	public String delete(Integer pointCode) {
		String result = pointDao.delete(pointCode);
		return result;
	}

	@Override
	public List<PointView> search(String type, String key, Integer page) {
		
		List<PointView> points = pointDao.search(type, key, page);
		return points;
	}
	@Override
	public List<Point> getPointByEntCode(Integer entCode, Integer page) {
		
		List<Point> points = pointDao.getPointByEntCode(entCode, page);
		return points;
	}
	@Override
	public Integer getSearchPage(String type, String key) {
		int page = pointDao.getSearchPage(type, key);
		return page;
	}
	@Override
	public Integer getPageByEntCode(Integer entCode) {

		int page = pointDao.getPageByEntCode(entCode);
		return page;
	}
	@Override
	public String update(Point point,Integer entCode) {
		String result = pointDao.update(point,entCode);
		return result;
	}
	@Override
	public PointView getByPointCode(Integer pointCode) {
		PointView pv = pointDao.getByPointCode(pointCode);
		return pv;
	}
	
	@Override
	public PointView getPointInfo(Integer pointCode) {
		
		return pointDao.getPointInfo(pointCode);
		
	}
	@Override
	public List<Point> getAllPointsByEntCode(Integer entCode) {
		
		return pointDao.getAllByEntCode(entCode);
	}
	@Override
	public String add(Point point, Integer entCode) {
		
		
		return pointDao.add(point, entCode);
	}
	@Override
	public List<PointView> getAllPoints(Integer entCode) {
		return pointDao.getAllPointView(entCode);
	}
	@Override
	public List<Point> getAllPoint() {
		return pointDao.getAllPoint();
	}
	@Override
	public List<PointView> getByDeptCode(Integer deptCode, Integer page) {
		
		return pointDao.getByDeptCode(deptCode, page);
	}
	@Override
	public Integer getPageByDeptCode(Integer deptCode) {
		
		return pointDao.getPageByDeptCode(deptCode);
	}
	
	@Override
	public Set<Point> getListByPointCodeList(List<Integer> pointCodes) {
		
		Set<Point> points = new HashSet<Point>();
		
		for(Integer code : pointCodes){
			points.add(pointDao.find(code));
		}
		return points;
	}
	@Override
	public List<Item> getItemsByPointCode(Integer pointCode) {

		
		return pointDao.getItemsByPointCode(pointCode);
		
	}
	@Override
	public String deleteItemFromPoint(Integer pointCode, Integer itemCode) {

		return pointDao.deletePointItem(pointCode, itemCode);
	}
	@Override
	public String addItemsToPoint(Integer pointCode, List<Integer> itemCodes) {
		
		return pointDao.addItemsToPoint(pointCode, itemCodes);
	}


}



