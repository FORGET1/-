package com.pingdu.controler.pointControler;

import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pingdu.baseModel.Root;
import com.pingdu.entity.item.Item;
import com.pingdu.entity.point.Point;
import com.pingdu.entity.user.User;
import com.pingdu.service.pointService.PointService;
import com.pingdu.service.userService.UserService;
import com.pingdu.view.PointView;
@Controller
@RequestMapping(value = "point")
public class PointControler {

	@Autowired
	private PointService pointService ;
	@Autowired
	private UserService userService;
	
	@Autowired
	private Root root;	
	/*
	 * 通过企业编号和当前页数获得项点列表
	 * @param entCode 	
	 * @param page
	 * @return 
	 */
	@RequestMapping(value = "getPointList",method = RequestMethod.GET)
	@ResponseBody
	public Root getPointList(HttpServletRequest req, @RequestParam("page") Integer page){
		
		//当前用户是否是安监局，安监局则查询所有项点
		String userCode = null;

//		Cookie[] cookies = req.getCookies();
//		for(Cookie cookie: cookies){
//			if(cookie.getName() == "userCode"){
//				userCode = cookie.getValue(); 
//			}
//		}
		
		User user =  userService.getUserByCode("12");
		
		Integer deptCode = user.getDept().getDeptCode();
		
		
		
		Integer sumpage = pointService.getPageByDeptCode(deptCode);
		if(page > sumpage || page == 0){
			page = sumpage;
		}
		List<PointView>points = pointService.getByDeptCode(deptCode,page);
		if(points.size() == 0 && sumpage == 1){
			page = 0;
			sumpage = 0;
		}
		root.setCurrentPage(page);
		root.setSumPage(sumpage);
		
		root.setObject(points);
		
		return root;
	}
	/*
	 * 通过项点编号获得单个项点信息
	 * @param pointCode
	 * @return 
	 */
	@RequestMapping(value = "pointInfo",method = RequestMethod.GET)
	@ResponseBody
	public Root pointInfo(@RequestParam("pointCode") Integer pointCode){
		PointView pv = pointService.getPointInfo(pointCode);
		root.setObject(pv);
		
		return root;
	}

	/*
	 *修改项点信息 
	 *@param Point
	 *@return 
	 */
	@RequestMapping(value = "modifyPoint",method = RequestMethod.GET)
	@ResponseBody
	public Root modifyPoint(@ModelAttribute("point") Point point,@RequestParam("entCode") Integer entCode){		
		
		String result = pointService.update(point,entCode);
		
		root.setMessage(result);
		
		return root;
	}

	/*
	 * 删除项点
	 * 
	 */
	@RequestMapping(value = "deletePoint",method = RequestMethod.GET)
	@ResponseBody
	public Root deletePoint(@RequestParam("pointCode") Integer pointCode){
		
		String result = pointService.delete(pointCode);
		root.setMessage(result);
		return root;
	}
	/*
	 * 根据企业编号获得 所有 项点
	 */
	@RequestMapping(value = "getPoints",method = RequestMethod.GET)
	@ResponseBody
	public Root getPoints(@RequestParam("entCode") Integer entCode){
		List<Point> points = pointService.getAllPointsByEntCode(entCode);
		root.setObject(points);
		return root;
	}
	/*
	 * 添加一个项点
	 * @param Point
	 * @return
	 */
	@RequestMapping(value = "addPoint",method = RequestMethod.POST)
	@ResponseBody
	public  Root addPoint(@ModelAttribute("point") Point point,@RequestParam("entCode")Integer entCode){
		String result = pointService.add(point, entCode);
		root.setMessage(result);
		return root;
	}

	@RequestMapping(value = "searchPoint",method = RequestMethod.GET)
	@ResponseBody
	public Root searchPoint(@RequestParam("type") String type,@RequestParam("key") String key,
								@RequestParam("page") Integer page){
		
		Integer sumpage = pointService.getSearchPage(type, key);
		if(page > sumpage || page == 0){
			page = sumpage;
		}
		List<PointView> points = pointService.search(type, key, page);
		if(points.size() == 0 && sumpage == 1){
			page = 0;
			sumpage = 0;
		}
		root.setObject(points);
		root.setCurrentPage(page);
		root.setSumPage(sumpage);
		return root;
	}


	/*
	 *从一个项点中删除一个条目
	 *@param pointCode
	 *@param itemCode
	 *@return
	 */
	@RequestMapping(value = "deletePointItem",method = RequestMethod.GET)
	@ResponseBody
	public Root deletePointItem(@RequestParam("pointCode") Integer pointCode,
								@RequestParam("itemCode") Integer itemCode){
		
		String msg = pointService.deleteItemFromPoint(pointCode, itemCode);
		Root root = new Root();
		root.setMessage(msg);
		return root;
	}
	/*
	 * 向一个项点中添加多个条目
	 * @param pointCode
	 * @param itemCodes
	 */
	@RequestMapping(value="addPointItem",method = RequestMethod.POST)
	@ResponseBody
	public Root addPointItem(@RequestParam("pointCode") Integer pointCode,
							 @RequestParam("itemCodes") List<Integer> itemCodes){
		String msg = pointService.addItemsToPoint(pointCode, itemCodes);
		Root root = new Root();
		root.setMessage(msg);
		return root;
	}
	/*
	 * 获得一个项点的所有条目
	 * @param pointCode
	 */
	@RequestMapping(value="getItemByPointCode",method = RequestMethod.GET)
	@ResponseBody
	public Root getItemByPointCode(@RequestParam("pointCode") Integer pointCode){
		
		List<Item> items = pointService.getItemsByPointCode(pointCode);
		
		Root root = new Root();
		root.setObject(items);
		return root;
	}
	
}
