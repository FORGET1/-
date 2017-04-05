package com.pingdu.controler.deptControler;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pingdu.baseModel.Root;
import com.pingdu.entity.department.Department;
//import com.pingdu.entity.user.User;
import com.pingdu.service.deptService.DeptService;
//import com.pingdu.service.userService.UserService;
import com.pingdu.view.DepartmentView;

@Controller
@RequestMapping(value = "department")
public  class DeptControler {

	@Autowired
	private DeptService deptService;
	//@Autowired
	//private UserService userService;
	@Autowired
	Root root;
	
	/**
	 * 获取安监办信息列表列表
	 * @param page
	 * @return 
	 */
	
	@RequestMapping(value = "getDepartmentList", method = RequestMethod.GET)
	@ResponseBody
	public Root getDepartmentList(@RequestParam("page") Integer page)
	{
			
		//先检查一共有多少页数据
		
		int pagesize=deptService.getDepartmentPageSize();
		if(page>pagesize) page=pagesize;
		if(page<=0) page=1;
		//处理好页面后读取数据
		List<Department> depts = deptService.getDepartmentList( page);
		//检查数据是否为空
	    int size=depts.size();
	    int i=0;
	    if(size==0)
	    {
		    //数据为空返回的departmentlist为null
		    root.setObject(null);
		    root.setSumPage(1);
			root.setCurrentPage(1);
	    	return root;
	    }
	    List<DepartmentView> deptlist=new ArrayList<DepartmentView>();
	    for(i=0;i<size;i++)
	    {
	    	// String deptName, String deptaddr, String deptperson, String deptPTel,String deptTel
	    	//integer code
	    	Department temp=depts.get(i);
	    	deptlist.add(new DepartmentView(temp.getDeptName(),temp.getDeptAddr(),temp.getContact(),
	    			temp.getContactPhone(),temp.getDeptPhone(),temp.getDeptCode()));
	    }
	    root.setObject(deptlist);
	    root.setSumPage(pagesize);
		root.setCurrentPage( page);
    	return root;
	}
	
	
	/**
	 * 获取安监办信息列表列表
	 * @param page
	 * @return 
	 */
	
	@RequestMapping(value = "getAllDepartmentList", method = RequestMethod.GET)
	@ResponseBody
	public Root getAllDepartmentList()
	{
			
		//处理好页面后读取数据
		List<Department> depts = deptService.getAllDepartmentList();
		//检查数据是否为空
	    int size=depts.size();
	    int i=0;
	    if(size==0)
	    {
		    //数据为空返回的departmentlist为null
		    root.setObject(null);
	    	return root;
	    }
	    List<DepartmentView> deptlist=new ArrayList<DepartmentView>();
	    for(i=0;i<size;i++)
	    {
	    	// String deptName, String deptaddr, String deptperson, String deptPTel,String deptTel
	    	//integer code
	    	Department temp=depts.get(i);
	    	deptlist.add(new DepartmentView(temp.getDeptName(),temp.getDeptAddr(),temp.getContact(),
	    			temp.getContactPhone(),temp.getDeptPhone(),temp.getDeptCode()));
	    }
	    root.setObject(deptlist);
    	return root;
	}
	
	
	/**
	 * 搜索安监办信息列表
	 * @param page
	 * @return 
	 */
	
	@RequestMapping(value = "searchDepartmentList", method = RequestMethod.GET)
	@ResponseBody
	public Root searchDepartmentList(@RequestParam("searchType") String searchType,@RequestParam("keyWord") String keyWord,@RequestParam("page") Integer page)
	{
		//先检查一共有多少页数据
		int pagesize=deptService.getSearchDepartmentListPageSize(searchType, keyWord);
		if(page>pagesize) page=pagesize;
		if(page<=0) page=1;
		//处理好页面后读取数据
		List<Department> depts = deptService.searchDepartmentList(searchType, keyWord, page);
		//检查数据是否为空

	    List<DepartmentView> deptlist=new ArrayList<DepartmentView>();
	    for(Department department:depts)
	    {
	    	deptlist.add(new DepartmentView(department.getDeptName(),department.getDeptAddr(),department.getContact(),
	    			department.getContactPhone(),department.getDeptName(),department.getDeptCode()));
	    }
	    root.setObject(deptlist);
	    root.setSumPage(pagesize);
		root.setCurrentPage(page);
    	return root;
	  
	}
	
	
	/**
	 * 安监办详细信息界面
	 * @param userCode
	 * @return 
	 */
	
	@RequestMapping(value = "departmentInfo", method = RequestMethod.GET)
	@ResponseBody
	public Root departmentInfo(@RequestParam("departmentCode") Integer departmentCode)
	{
		//取出查找的department
		Department d=deptService.getDepartmentInfo(departmentCode);
		//判断是否存在此部门
		if(d==null)
		{
	    	root.setObject(null);
		    return root;
		}
		// String deptName, String deptaddr, String deptperson, String deptPTel,String deptTel
    	//integer code
		DepartmentView dv= new DepartmentView(d.getDeptName(),d.getDeptAddr(),d.getContact(),
				d.getContactPhone(),d.getDeptPhone(),d.getDeptCode());

    	root.setObject(dv);
	    return root;
		
	}
	
	/**
	 * 安监局详细信息界面,不用了，先不删，留着。
	 * @param userCode
	 * @return 
	 */
	
	@RequestMapping(value = "turnToModify", method = RequestMethod.GET)
	@ResponseBody
	public Root turnToModify(@RequestParam("userCode") String userCode)
	{
		//取出查找的department
		
		//User user = userService.getUserByCode(userCode);
		//   <4排除企业的情况
		//if(user.getRole().getRoleCode() < 4){
			Department d=deptService.turnToModify(userCode);
			//判断是否存在此部门，但理论上肯定存在的
			if(d==null)
			{
		    	root.setObject(null);
			    return root;
			}
			// String deptName, String deptaddr, String deptperson, String deptPTel,String deptTel
	    	//integer code
			DepartmentView dv= new DepartmentView(d.getDeptName(),d.getDeptAddr(),d.getContact(),
					d.getContactPhone(),d.getDeptPhone(),d.getDeptCode());
			
	    	root.setObject(dv);
		    return root;
		//}		
		//else
		//{
			
		//}
	}
	
	/**
	 * 安监局删除安监办
	 * @param departmentCode
	 * @return 
	 */
	@RequestMapping(value = "deleteDepartment", method = RequestMethod.GET)
	@ResponseBody
	public Root deleteDepartment(@RequestParam("departmentCode") Integer departmentCode)
	{
		
		boolean Bool=deptService.deleteDepartment(departmentCode);
		root.setMessage(Bool?0:1, Bool?"SUCCESS":"FAILED");
		return root;
	}
	
	/**
	 * 安监局修改安监局信息
	 * @param  departmentCode,departmentName,departmentAddr,
	 * @param  departmentPerson,departmentPersonTel,departmentTel
	 * @return 
	 */
	@RequestMapping(value = "modifyDepartment", method = RequestMethod.POST)
	@ResponseBody
	public Root modifyDepartment(@ModelAttribute("department") Department department)
	{
		
		boolean Bool=deptService.modifyDepartment(department);
		root.setMessage(Bool?0:1, Bool?"SUCCESS":"FAILED");
		return root;
	}
	
	/**
	 * 安监局添加安监办
	 * @param  departmentCode,departmentName,departmentAddr,
	 * @param  departmentPerson,departmentPersonTel,departmentTel
	 * @return 
	 */
	@RequestMapping(value = "addDepartment", method = RequestMethod.POST)
	@ResponseBody
	public Root addDepartment(@ModelAttribute("department") Department department)
	{
		department.setDeptLevel(2);
		boolean Bool=deptService.addDepartment(department);
		root.setMessage(Bool?0:1, Bool?"SUCCESS":"FAILED");
		return root;
		
	}
	
	/**
	 * 安监局修改安监办信息
	 * @param  departmentCode,departmentName,departmentAddr,
	 * @param  departmentPerson,departmentPersonTel,departmentTel
	 * @return 
	 */
	@RequestMapping(value = "modifyDepartmentInfo", method = RequestMethod.POST)
	@ResponseBody
	public Root modifyDepartmentInfo(@ModelAttribute("department") Department department)
	{
		
		boolean Bool=deptService.modifyDepartmentInfo(department);
		root.setMessage(Bool?0:1, Bool?"SUCCESS":"FAILED");
		return root;
	}
	
	
	
	
}

