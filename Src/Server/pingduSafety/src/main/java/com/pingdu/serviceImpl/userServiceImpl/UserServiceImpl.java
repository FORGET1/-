package com.pingdu.serviceImpl.userServiceImpl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pingdu.dao.userDao.UserDao;
import com.pingdu.entity.user.User;
import com.pingdu.service.userService.UserService;
import com.pingdu.view.UserView;

@Service
public class UserServiceImpl implements UserService {

	
	@Autowired
	UserDao userDao;
	
	@Override
	public boolean deleteUserByCode(String userCode) {
		return userDao.deleteUserByCode(userCode);
	}

	@Override
	public boolean modifyUser(User user) {
		return userDao.modifyUser(user);
	}


	@Override
	public User getUserByCode(String userCode) {
		User user = userDao.getUserByCode(userCode);
		return user;
	}


	@Override
	public boolean isExist(String userCode) {
		return userDao.isExist(userCode);
	}


	@Override
	public HashMap<String, Object> getUserPwdAndSalt(String userCode) {
		return userDao.getPwdAndSalt(userCode);
	}



	@Override
	public boolean isCanLogin(String userCode) {
		return userDao.isCanLogin(userCode);
	}

	@Override
	public boolean addUser(User user) {
		
		return userDao.addUser(user);
	}

	@Override
	public List<UserView> getUserListByRole(int roleCode, int page) {
		return userDao.getUserByRoleCode(roleCode, page);
	}
	
	
	@Override
	public int calSum(int roleCode) {
		return userDao.sumOfUserPage(roleCode);
	}


	@Override
	public List<User> getDeptOtherUser(String deptCode, String userCode) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> getDeptUser(String deptCode) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> getAllUser() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> getPatrolPersons() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteDeptUser(String deptCode) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<UserView> getUserListByEnt(int entCode) {
		return userDao.getEntUserByEntCode(entCode);
	}

	@Override
	public int sumOfUserListByEnt(int entCode) {
		
		return 0;
	}


	@Override
	public List<UserView> getEntUserListByDeptCode(int deptCode,int page) {
		return userDao.getEntUserListByDeptCode(deptCode, page);
	}

	@Override
	public int calSumOfGetEntUserListByDeptCode(int deptCode) {
		return userDao.calSumOfGetEntUserListByDeptCode(deptCode);
	}

	@Override
	public List<UserView> searchUserByKeyAndRoleAndDept(String searchType, String keyWord, int page,String manageCode) {
		return userDao.searchUserByKeyAndRoleAndDept(searchType,keyWord,page,manageCode);
	}

	@Override
	public List<UserView> searchUserByKeyAndRole(String searchType, String keyWord, int page, int roleCode) {
		return userDao.searchUserByKeyAndRole(roleCode, searchType, keyWord, page);
	}

	@Override
	public int SumOfSearchUserByKeyAndRoleAndDept(String searchType, String keyWord, int page, String manageCode) {
		return userDao.SumOfSearchUserByKeyAndRoleAndDept(searchType, keyWord, page, manageCode);
	}

	@Override
	public int SumOfSearchUserByKeyAndRole(String searchType, String keyWord, int page, int roleCode) {
		return userDao.SumOfSearchUserByKeyAndRole(roleCode, searchType, keyWord, page);
	}





	

}
