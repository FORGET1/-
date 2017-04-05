package com.pingdu.serviceImpl.itemServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pingdu.dao.itemDao.ItemDao;
import com.pingdu.entity.item.Item;
import com.pingdu.entity.point.Point;
import com.pingdu.service.itemService.ItemService;
@Service
public class ItemServiceImpl implements ItemService{

	@Autowired
	private ItemDao itemdao;
	/**
	 * 获取条目列表
	 */
	@Override
	public List<Item> getItemList(int page) {
		
		return itemdao.getItems(page);
	}
	/**
	 * 查询条目
	 */
	@Override
	public List<Item> searchItem(String content, int page) {
		return itemdao.searchItems(content, page);
	}
	
	/**
	 * 获得页面总数
	 */
	@Override
	public int calPage() {
		return itemdao.sum();
	}
	/**
	 * 获得当前搜索内容的总页数
	 */
	@Override
	public Integer calPageOfSearch(String content) {
		return itemdao.sumOfSearch(content);
	}
	/**
	 * 删除条目
	 */
	@Override
	public void deleleItem(Integer itemCode) {
		itemdao.delete(itemCode);
	}

	
	@Override
	public Item getItemInfo(Integer itemCode) {
		return  itemdao.getItem(itemCode);
	}
	@Override
	public Set<Point> getPoints(Item item) {
	return null;// item.getPoints();
	}
	@Override
	public void modifyItem(Item item) {
		itemdao.update(item);
	}
	@Override
	public void add(Item item)
	{
		itemdao.insert(item);
	}
	@Override
	public List<Item> getItemByCodes(List<Integer> itemCodes) {
		List<Item> items = new ArrayList<Item>();
	    for (int i = 0; i < itemCodes.size(); i++) {
		items.add(itemdao.getItem(itemCodes.get(i)));
	    }
		return items;
	}
	


}
