package com.pingdu.service.itemService;

import java.util.List;
import java.util.Set;

import com.pingdu.entity.item.Item;
import com.pingdu.entity.point.Point;

public interface ItemService {
	
	
	public List<Item> getItemList(int page);

	public List<Item> searchItem(String content, int page);

	public int calPage();
	
	public Integer calPageOfSearch(String content);
	
	public void deleleItem(Integer itemCode);
	
	public List<Item> getItemByCodes(List<Integer> itemCodes);
	
	public  Item getItemInfo(Integer itemCode);
	
	public  Set<Point> getPoints(Item item);
	
	public void modifyItem(Item item);
	
	public void add(Item item);
}
