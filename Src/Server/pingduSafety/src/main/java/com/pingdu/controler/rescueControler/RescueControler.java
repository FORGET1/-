package com.pingdu.controler.rescueControler;

import java.util.List;


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
import com.pingdu.entity.rescue.Rescue;
import com.pingdu.service.rescueService.RescueService;
import com.pingdu.utility.PublicVariable;

@Controller
@RequestMapping(value = "rescue")
public class RescueControler {
	@Autowired 
	Root root;
	@Autowired
	RescueService service;
	/**
	 * 获得应急救援库列表
	 * @param page
	 * @return
	 */
	@RequestMapping(value = "getEmeRescueList",method=RequestMethod.GET)
	public @ResponseBody Root get(@RequestParam("page") int page,HttpServletRequest request, HttpServletResponse response) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		Integer sumpage = service.calSum();

		List<Rescue> rescues =service.getRescuesList(page,PublicVariable.rows);
		
		root.setObject(rescues);
		root.setCurrentPage(page);
		root.setSumPage(sumpage);
		return root;
	}
	
	/**
	 * 获得应急救援库列表
	 * @param page
	 * @return
	 */
	@RequestMapping(value = "getAllEmeRescueList",method=RequestMethod.GET)
	public @ResponseBody Root getAllEmeRescueList(HttpServletRequest request, HttpServletResponse response) {
		response.setHeader("Access-Control-Allow-Origin", "*");

		List<Rescue> rescues =service.getAllRescuesList();
		
		root.setObject(rescues);
		return root;
	}
	
	/**
	 * 搜索 应急救援接口
	 * @param searchType
	 * @param keyword
	 * @param page
	 * @return
	 */
	@RequestMapping(value = "searchEmeRescue",method=RequestMethod.GET)
	public @ResponseBody Root sea(@RequestParam("type") String type ,@RequestParam("keyWord") String keyWord,@RequestParam("page") int page
			,HttpServletRequest request, HttpServletResponse response) {	
		response.setHeader("Access-Control-Allow-Origin", "*");
		Integer sumpage = service.sumOfSearch(type,keyWord);
		if(page > sumpage || page == 0){
			page = sumpage;
		}
		List <Rescue> rescues =service.search(type,keyWord,page,PublicVariable.rows);
		if(rescues.size() == 0 && sumpage == 1){
			page = 0;
			sumpage = 0;
		}
		
		root.setObject(rescues);
		root.setCurrentPage(page);
		root.setSumPage(sumpage);
		return root;
	}
	
	/**
	 * 修改应急救援接口
	 * @param searchType
	 * @param keyword
	 * @param page
	 * @return
	 */
	@RequestMapping(value="modifyEmeRescue",method = RequestMethod.POST)
	@ResponseBody
	public Root modifyUser(@ModelAttribute("rescue") Rescue rescue, @RequestParam("page") int page,@RequestParam("lastpage") int lastpage
			,HttpServletRequest request, HttpServletResponse response){
		response.setHeader("Access-Control-Allow-Origin", "*");

		if(service.modify(rescue)){
			root.setMessage(0,"SUCCESS");}
		else{
			root.setMessage(1, "FAILED");}
		return root;
		
	}
	
	/**
	 * 删除应急救援接口
	 * @param searchType
	 * @param keyword
	 * @param page
	 * @return
	 */
	@RequestMapping(value="deleteEmeRescue",method = RequestMethod.POST)
	@ResponseBody
	public Root del(@RequestParam("rescueCode") int rescueCode,HttpServletRequest request, HttpServletResponse response){
		response.setHeader("Access-Control-Allow-Origin", "*");
		if(service.delete(rescueCode))
			root.setMessage(0,"SUCCESS");
		else
			root.setMessage(1, "FAILED");
		return root;
	}

	
	/**
	 * 添加应急救援接口
	 * @param searchType
	 * @param keyword
	 * @param page
	 * @return
	 */
	@RequestMapping(value="addEmeRescue",method = RequestMethod.POST)
	@ResponseBody
	public Root add(@ModelAttribute("rescue") Rescue rescue,@RequestParam("page") int page,HttpServletRequest request, HttpServletResponse response){
		if(service.add(rescue)){
			root.setMessage(0,"SUCCESS");}
		else{
			root.setMessage(1, "FAILED");}
		return root;
	}

	

}
