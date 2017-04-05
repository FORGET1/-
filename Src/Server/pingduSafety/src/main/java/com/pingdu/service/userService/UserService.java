package com.pingdu.service.userService;

import java.util.HashMap;

import java.util.List;

import com.pingdu.entity.user.User;
import com.pingdu.view.UserView;;

public interface UserService {

	public List<UserView> getUserListByRole(int roleCode,int page);

	public boolean deleteUserByCode(String userCode);

	public boolean modifyUser(User user);

	public boolean addUser(User user);

	public User getUserByCode(String userCode);

	public int  calSum(int roleCode);

	public boolean isExist(String userCode);

	public boolean isCanLogin(String userCode);

	public HashMap<String, Object> getUserPwdAndSalt(String userCode);

	public List<User> getDeptOtherUser(String deptCode, String userCode);

	public List<User> getDeptUser(String deptCode);

	// 针对无网情况的数据库写入移动端
	public List<User> getAllUser();

	public List<User> getPatrolPersons();

	public void deleteDeptUser(String deptCode);

	public List<UserView> getUserListByEnt(int entCode);

	public int sumOfUserListByEnt(int entCode);

	public List<UserView> getEntUserListByDeptCode(int deptCode,int page);

	public int calSumOfGetEntUserListByDeptCode(int deptCode);

	public List<UserView> searchUserByKeyAndRoleAndDept(String searchType, String keyWord, int page,String manageCode);

	public List<UserView> searchUserByKeyAndRole(String searchType, String keyWord, int page, int roleCode);
	
	public int SumOfSearchUserByKeyAndRoleAndDept(String searchType, String keyWord, int page,String manageCode);

	public int SumOfSearchUserByKeyAndRole(String searchType, String keyWord, int page, int roleCode);
}
