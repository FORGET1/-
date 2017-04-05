package com.pingdu.controler.userControler;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pingdu.baseModel.BaseController;
import com.pingdu.baseModel.Root;
import com.pingdu.entity.department.Department;
import com.pingdu.entity.enterprise.Enterprise;
import com.pingdu.entity.role.Role;
import com.pingdu.entity.user.User;
import com.pingdu.service.deptService.DeptService;
import com.pingdu.service.entService.EntService;
import com.pingdu.service.roleService.RoleService;
import com.pingdu.service.userService.UserService;
import com.pingdu.view.UserView;

@Controller
@RequestMapping(value="user")
public class UserControler extends BaseController{

	@Autowired
	UserService userService;
	@Autowired
	RoleService roleService;
	@Autowired
	EntService  entService;
	@Autowired
	DeptService deptService;
	@Autowired
	Root root;

	
	@RequestMapping(value="getUserByCode")
	@ResponseBody
	public Root getUserByCode(@RequestParam("userCode") String userCode){
		
		root.setObject(new UserView(userService.getUserByCode(userCode)));
		return root;
	}
	
	@RequestMapping(value="getUsersByRole")
	@ResponseBody
	public Root getUsersByRole(@RequestParam("roleCode") Integer roleCode,
			@RequestParam("page") Integer page,
			@RequestParam("manageCode") String manageCode){
		List<UserView> users;
		int sumPage;
		
		if("super".equals(manageCode) || "".equals(manageCode)){
			
			users = userService.getUserListByRole(roleCode, page);
			sumPage = userService.calSum(roleCode);
			
		}
		else{
			users = userService.getEntUserListByDeptCode(Integer.parseInt(manageCode),page);
			sumPage = userService.calSumOfGetEntUserListByDeptCode(Integer.parseInt(manageCode));
		}
		
		
		
		Root root = new Root();
		root.setObject(users);
		root.setSumPage(sumPage);
		root.setCurrentPage(page);
		
		return root;
	}
	
	@RequestMapping(value="getUsersByEnt")
	@ResponseBody
	public Root getUsersByEnt(@RequestParam("entCode") Integer entCode,@RequestParam("page") Integer page){
		
		List<UserView> users = userService.getUserListByEnt(entCode);
		int sumPage = userService.sumOfUserListByEnt(entCode);
		
		Root root = new Root();
		root.setObject(users);
		root.setSumPage(sumPage);
		root.setCurrentPage(page);
		
		return root;
	}
	
	@RequestMapping(value="searchUserByKeyAndRole")
	@ResponseBody
	public Root searchUserByKeyAndRole(@RequestParam("searchType") String searchType, @RequestParam("keyWord") String keyWord,
			@RequestParam("roleCode") int roleCode,@RequestParam("page") int page,@RequestParam("manageCode") String manageCode){
		
		List<UserView> users;
		int sumPage;
		
		if("super".equals(manageCode) || "".equals(manageCode)){
			
			users = userService.searchUserByKeyAndRole(searchType, keyWord, page, roleCode);
			sumPage = userService.SumOfSearchUserByKeyAndRole(searchType, keyWord, page, roleCode);
			
		}
		else{
			users = userService.searchUserByKeyAndRoleAndDept(searchType, keyWord, page,manageCode);
			sumPage = userService.SumOfSearchUserByKeyAndRoleAndDept(searchType, keyWord, page, manageCode);
		}
		
		Root root = new Root();
		root.setObject(users);
		root.setSumPage(sumPage);
		root.setCurrentPage(page);
		
		return root;

	}
	@RequestMapping(value="addUser" ,method = RequestMethod.POST)
	@ResponseBody
	public Root addUser(@ModelAttribute("user") User user,@RequestParam("roleCode") Integer roleCode,
			@RequestParam("deptCode") Integer deptCode){
		boolean isExist = userService.isExist(user.getUserCode());
		if(isExist == true){
			root.setMessage(0,"exist");
			return root;
		}
		Role role ;
		Department department;
		Enterprise enterprise;
		switch(roleCode){
		case 0:
			role = roleService.getRoleByCode(2);
			user.setRole(role);
			department = deptService.getDepartmentInfo(deptCode);
			user.setDept(department);
			break;
		case 1:
			role = roleService.getRoleByCode(3);
			user.setRole(role);
			department = deptService.getDepartmentInfo(deptCode);
			user.setDept(department);
			break;
		case 2:
			enterprise = entService.getEnterpriseByCode(deptCode);
			switch(enterprise.getEntLevel()){
			case 1:
				role = roleService.getRoleByCode(4);
				user.setRole(role);
			case 2:
				role = roleService.getRoleByCode(6);
				user.setRole(role);
			}
			user.setEnterprise(enterprise);
			break;
		}
		
		boolean result = userService.modifyUser(user);
		root.setMessage(result?0:1, result?"SUCCESS":"FAILED");
		return root;
	}
	@RequestMapping(value="modifyUser",method = RequestMethod.POST)
	@ResponseBody
	public Root modifyUser(@ModelAttribute("user") User userToMod,
			@RequestParam("deptCode") Integer deptCode){
		User user = userService.getUserByCode(userToMod.getUserCode());

		user.modifyUser(userToMod);
		
		switch(user.getRole().getRoleCode()){
		case 1: case 2: case 3:
			Department department = deptService.getDepartmentInfo(deptCode);
			user.setDept(department);
			user.setEnterprise(null);
			break;
		case 4:case 5:case 6:case 7:
			Enterprise enterprise = entService.getEnterpriseByCode(deptCode);
			user.setEnterprise(enterprise);
			user.setDept(null);
			break;
		}
		boolean result = userService.modifyUser(user);
		root.setMessage(result?0:1, result?"SUCCESS":"FAILED");
		return root;
	}
	
	/**
	 * 检测用户工号是否存在 存在返回"exist",不存在返回"inexist"
	 * 
	 * @param userCode
	 * @return
	 */
	@RequestMapping(value = "checkUserCode", method = RequestMethod.GET)
	@ResponseBody
	public Root checkUserCode(@RequestParam("userCode") String userCode) {

		boolean isExist = userService.isExist(userCode);
		root.setResult(isExist?"exist":"inexist");

		return root;
	}
	
	/**
	 * 修改密码
	 * 
	 * @param userCode
	 * @param userPwd
	 * @param userSalt
	 * @return
	 */
	@RequestMapping(value = "appModifyPwd", method = RequestMethod.GET)
	@ResponseBody
	public Root appMofifyPwd(
			@RequestParam("userCode") String userCode,
			@RequestParam("userPwd") String userPwd, 
			@RequestParam("userSalt") String userSalt) {

		User user = userService.getUserByCode(userCode);
		user.setPassword(userPwd);
		user.setSalt(userSalt);
		userService.modifyUser(user);
		root.setMessage(0,"success");
		return root;
	}
	
	/**
	 * 删除用户接口
	 * 
	 * @param userCode
	 * @return
	 */
	@RequestMapping(value = "deleteUserByCode", method = RequestMethod.GET)
	public Root del(@RequestParam("userCode") String userCode) {
		
		boolean result = userService.deleteUserByCode(userCode);
		
		root.setMessage(result?0:1,result?"SUCCESS":"FAILED");
		return root;
	}
	
	
	
}
