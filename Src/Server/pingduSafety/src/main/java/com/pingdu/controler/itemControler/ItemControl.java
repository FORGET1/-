package com.pingdu.controler.itemControler;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pingdu.baseModel.Root;
import com.pingdu.entity.item.Item;
import com.pingdu.entity.point.Point;
import com.pingdu.service.itemService.ItemService;
import com.pingdu.service.pointService.PointService;


@Controller
@RequestMapping(value = "item")
public class ItemControl {

	@Autowired
	private ItemService itemService;
	@Autowired
	private PointService pointService;
	@Autowired
	private Root root;
	
	
	/**
	 * 获得条目列表
	 * @param page
	 * @return
	 */
	@RequestMapping(value="getItemList",method = RequestMethod.GET)
	@ResponseBody
	public Root get(@RequestParam("page") int page) {
		Integer sumpage = itemService.calPage();
		if(page > sumpage || page == 0){
			page = sumpage;
		}
		List<Item> items = itemService.getItemList(page);
		if(items.size() == 0 && sumpage == 1){
			page = 0;
			sumpage = 0;
		}	
//	    ItemList itemList = new ItemList();
//		itemList.itemList = items;
//		itemList.page  = page;
//		itemList.lastpage = sumpage;
		root.setCurrentPage(page);
		root.setSumPage(sumpage);
		root.setObject(items);
		return root;
	}
	
	@RequestMapping(value="searchItem",method = RequestMethod.GET)
	@ResponseBody
	public Root search(@RequestParam("content") String content,@RequestParam("page") int page)
	{
		System.out.println(content);
		Integer sumpage = itemService.calPageOfSearch(content);
		if(page > sumpage || page == 0){
			page = sumpage;
		}
		List<Item> items = itemService.searchItem(content , page);
		for (int i = 0; i < items.size(); i++) {
			System.out.println(items.get(i).getItemInfo());
		}
		System.out.println(items.size());
		if(items.size() == 0 && sumpage == 1){
			page = 0;
			sumpage = 0;
		}
		 root.setCurrentPage(page);
		 root.setSumPage(sumpage);
		 root.setObject(items);
		 return root;
	}
	
	@RequestMapping(value = "deleteItem",method = RequestMethod.GET)
	@ResponseBody
	public Root delete(@RequestParam("itemCode") Integer itemCode)
	{
		try {
			itemService.deleleItem(itemCode);
		} catch (Exception e) {
			
			e.printStackTrace();
			root.setMessage(1, "FAILED");
			return root;
		}
		root.setMessage(0, "SUCCESS");
		return root;
	}
												/** app **/
	@RequestMapping(value = "appGetItemListByItemCodes", method = RequestMethod.POST)
	@ResponseBody
	public Root getItemListByItemCodes(@RequestParam("itemCodeList[]") List<Integer> itemCodes)
	{
		List<Item> items =itemService.getItemByCodes(itemCodes);
	    root.setObject(items);
		return root;
	}

	
	@RequestMapping(value = "itemInfo",method = RequestMethod.GET)
	@ResponseBody
	public Root getItemInfo(@RequestParam("itemCode") Integer itemCode)
	{
		
		Item item = itemService.getItemInfo(itemCode);
		Set<Point> points = item.getPoints();
		root.setObject(item);
		root.setJsonBeanSet(points);
		return root;
	}
	
	@RequestMapping(value = "modifyItem",method = RequestMethod.POST)
	@ResponseBody
	public Root modify(@RequestParam("itemCode") Integer itemCode,@RequestParam("itemContent") String itemContent,
			@RequestParam("pointCodes[]") List<Integer> pointCodes)
	{
		Item item = null;
		try {
			item  = itemService.getItemInfo(itemCode);
			item.setItemInfo(itemContent);
			item.getPoints().clear();
			Set<Point> points = pointService.getListByPointCodeList(pointCodes);
			if(points.size()!=0)
			{
				for (Iterator<Point> iterator = points.iterator(); iterator.hasNext();) {
					Point point = (Point) iterator.next();
					item.getPoints().add(point);
				}
			}
			itemService.modifyItem(item);
		} catch (Exception e) {
			e.printStackTrace();
			root.setMessage(1, "FAILED");
			return root;
		}
		root.setMessage(0, "SUCCESS");
		return root;
	}
	@RequestMapping(value = "addItem",method = RequestMethod.POST)
	@ResponseBody
	public Root add(@RequestParam("itemContent") String itemContent,@RequestParam("pointCodes[]") List<Integer> pointCodes)
	{
		try {
			Item item = new Item(itemContent);
			itemService.add(item);
			Set<Point> points = pointService.getListByPointCodeList(pointCodes);
			item.getPoints().addAll(points);
			itemService.modifyItem(item);
		} catch (Exception e) {
			e.printStackTrace();
			root.setMessage(1, "FAILED");
			return root;
		}
		root.setMessage(0, "SUCCESS");
		return root;
	}
}

