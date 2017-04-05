package com.pingdu.controler.entControler;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pingdu.baseModel.Root;
import com.pingdu.entity.department.Department;
import com.pingdu.entity.entType.EntType;
import com.pingdu.entity.enterprise.Enterprise;
import com.pingdu.service.deptService.DeptService;
import com.pingdu.service.entService.EntService;
import com.pingdu.service.entTypeService.EntTypeService;
import com.pingdu.view.EnterpriseView;

@Controller
@RequestMapping(value = "enterprise")
public class EntControl {
	
	@Autowired
	EntService entService;
	@Autowired
	EntTypeService entTypeService;
	@Autowired
	DeptService deptService;
	@Autowired
	Root root;
	
	@RequestMapping(value ="getEntpriseListByParentCode",method = RequestMethod.GET)
	@ResponseBody
	public Root getEntpriseListByParentCode(@RequestParam("page") int page,
			@RequestParam("parentCode") int parentCode){
		
		List<EnterpriseView> enterprises = entService.getEnterpriseListByParentCode(page, parentCode);
		
		int sumPage = entService.getSumOfPage(parentCode);
		
	
		root.setObject(enterprises);
		root.setCurrentPage(page);
		root.setSumPage(sumPage);
		return root;
	}
	
	
	@RequestMapping(value = "getEnterpriseByDep",method = RequestMethod.GET)
	@ResponseBody
	public Root getEnterpriseByDep(@RequestParam("page") int page,@RequestParam("deptCode") int deptCode) {
		
		List<EnterpriseView> enterprises =  entService.getEnterpriseByDept(page,deptCode);
		
		int sumPage = entService.getSumOfPageByDept(deptCode);
		
		root.setObject(enterprises);
		root.setCurrentPage(page);
		root.setSumPage(sumPage);
		return root;
	}
	
	@RequestMapping(value = "getEnterpriseByDepNoPage",method = RequestMethod.GET)
	public Root getEnterpriseByDepNoPage(@RequestParam("deptCode") int deptCode) {
		
		List<EnterpriseView> enterprises =  entService.getEnterpriseByDepNoPage(deptCode);
		
		root.setObject(enterprises);
		return root;
	}
		
		
	@RequestMapping(value="deleteEnterprise",method=RequestMethod.GET)
	@ResponseBody
	public Root deleteEnterprise(@RequestParam("entCode")int entCode) {
	    	
		boolean bool=entService.deleteEnterprisement(entCode);

		Root root = new Root();
		root.setMessage(bool?0:1, bool?"success":"fail");
		return root;
	}
	
	@RequestMapping(value="addEnterprise",method=RequestMethod.POST)
	@Transactional(value="transactionManager", rollbackFor = Exception.class)
	@ResponseBody
	public Root addEnterprise(@ModelAttribute("enterprise")Enterprise enterprise,
			@RequestParam("deptCode") Integer deptCode,
			@RequestParam("entTypeCode") Integer entTypeCode) {
	    	
		Root root = new Root();
		Department department =  deptService.getDepartmentInfo(deptCode);
		EntType entType = entTypeService.getEntTypeByCode(entTypeCode);
		if(department == null || entType == null){
			root.setMessage(1,"no department or no entType");
			return root;
		}
		enterprise.setDepartment(department);
		enterprise.setEntType(entType);
		boolean bool=entService.addEnterprise(enterprise);
		
		

		root.setMessage(bool?0:1, bool?"success":"fail");
		return root;
	}
	
	@RequestMapping(value="modifyEnterprise",method=RequestMethod.POST)
	@ResponseBody
	public Root modifyEnterprise(
			@ModelAttribute("enterprise")Enterprise enterprise,
			@RequestParam("deptCode") Integer deptCode) {
	    	
		Root root = new Root();
		Department department =  deptService.getDepartmentInfo(deptCode);
		
		if(department == null){
			root.setMessage(1,"no department or no entType");
			return root;
		}
		enterprise.setDepartment(department);
		boolean bool=entService.modifyEnterprise(enterprise);

		root.setMessage(bool?0:1, bool?"success":"fail");
		return root;
	}
	
	
	
	@RequestMapping(value="getEnterpriseByCode",method=RequestMethod.GET)
	@ResponseBody
	public Root getEnterpriseByCode(@RequestParam("entCode") Integer entCode) {
	    	
		Root root = new Root();

		EnterpriseView enterprise = entService.getEnterpriseInfo(entCode);

		root.setObject(enterprise);
		return root;
	}
	
	
	@RequestMapping(value="searchEnterpriseByKey")
	@ResponseBody
	public Root searchEnterpriseByKey(
			@RequestParam("page") Integer page,
			@RequestParam("manageCode") String manageCode,
			@RequestParam("searchType") String searchType,
			@RequestParam("keyword") String keyword,
			@RequestParam("keyword") Integer entLevel,
			@RequestParam("keyword") Integer parentCode){
		List<EnterpriseView> enterprises;
		int sumPage;
		
		if("super".equals(manageCode) || "".equals(manageCode)){
			
			enterprises = entService.ajjSearchEnterpriseByKey(searchType, keyword, entLevel, parentCode);
			sumPage = 0;
			
		}
		else{
			enterprises = entService.ajbSearchEnterpriseByKey(searchType, keyword, entLevel, Integer.parseInt(manageCode), parentCode);
			sumPage =0;
		}
		
		Root root = new Root();
		root.setObject(enterprises);
		root.setSumPage(sumPage);
		root.setCurrentPage(page);
		
		return root;
	}
	
}
