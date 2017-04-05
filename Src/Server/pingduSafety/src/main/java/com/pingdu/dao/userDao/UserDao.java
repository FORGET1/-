package com.pingdu.dao.userDao;

import static com.pingdu.manager.ThreadLocalManager.em;


import java.util.HashMap;
import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.pingdu.entity.department.Department;
import com.pingdu.entity.enterprise.Enterprise;
import com.pingdu.entity.role.Role;
import com.pingdu.entity.user.User;
import com.pingdu.utility.PublicVariable;
import com.pingdu.view.UserView;

@Repository
public class UserDao {

	private static String JPQLD ="select new %4$s"
								+ "(a.userCode, a.name,b.deptCode,b.deptName,c.roleName,a.gender,a.birthDate,a.id,a.phone,a.mac,a.note) "
								+ "from %1$s a,%2$s b,%3$s c";
	private static String JPQLE ="select new %4$s"
								+ "(a.userCode, a.name,b.entCode,b.entName,c.roleName,a.gender,a.birthDate,a.id,a.phone,a.mac,a.note) "
		        				+ "from %1$s a,%2$s b,%3$s c";
	
	public boolean addUser(User user) {
		try {
			em().persist(user);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public List<UserView> getDeptUserByDeptCode(int deptCode) {
		String jpql= String.format
		        (JPQLD +" where "
		        		+ "a.department.deptCode = b.deptCode "
		        		+ "AND a.role.roleCode = c.roleCode "
		        		+ "AND a.role.roleCode between 2 and 3 "
		        		+ "And b.entCode=:entCode ",
		                User.class.getName(),
		                Department.class.getName(),
		                Role.class.getName(),
		                UserView.class.getName());                                                                                                                    
		TypedQuery<UserView> query = em().createQuery(jpql, UserView.class);
		
		query.setParameter("deptCode", deptCode);
		List<UserView> list = query.getResultList();
		return list;
	}

	public List<UserView> getEntUserByEntCode(int entCode) {
		String jpql= String.format
		        (JPQLE+" where "
		        		+ "a.enterprise.entCode = b.entCode "
		        		+ "AND a.role.roleCode = c.roleCode "
		        		+ "AND a.role.roleCode between 4 and 8 "
		        		+ "And b.entCode=:entCode",
		                User.class.getName(),
		                Enterprise.class.getName(),
		                Role.class.getName(),
		                UserView.class.getName());
		TypedQuery<UserView> query = em().createQuery(jpql, UserView.class);
		
		query.setParameter("entCode", entCode);
		List<UserView> list = query.getResultList();
		return list;
	}

	
	public User getUserByCode(String userCode) {
		String jpql = "select u from User u where u.userCode=:userCode";
		TypedQuery<User> query = em().createQuery(jpql, User.class);
		query.setParameter("userCode", userCode);
		List<User> users = query.getResultList();
		if (users.size() == 1) {
			return users.get(0);
		}
		User user = new User();
		user.setName("无");
		return user;
	}
	
	
	
	private TypedQuery<UserView> getUserByRoleJpql(int roleCode){
		String temp = "select new %1$s(u) from User u where u.role.roleCode>:roleCodeMin AND u.role.roleCode<:roleCodeMax order by u.userCode";
		
		String jpql=String.format(temp,UserView.class.getName());
		TypedQuery<UserView> query = em().createQuery(jpql, UserView.class);
		switch(roleCode){
		
		case 0:
			query.setParameter("roleCodeMin", 1);
			query.setParameter("roleCodeMax", 3);
			break;
		
		case 1:
			query.setParameter("roleCodeMin", 2);
			query.setParameter("roleCodeMax", 4);
			break;
		case 2:
			query.setParameter("roleCodeMin", 3);
			query.setParameter("roleCodeMax", 8);
		}
		
		return query;
	}
	public List<UserView> getUserByRoleCode(int roleCode,int page) {
		
		TypedQuery<UserView> query = getUserByRoleJpql(roleCode);
		int head = (page - 1) * 15;
		query.setFirstResult(head);
		query.setMaxResults(15);
		List<UserView> users = query.getResultList();

		return users;
	}
	public int sumOfUserPage(int roleCode) {
		
		TypedQuery<UserView> query = getUserByRoleJpql(roleCode);

		return (query.getResultList().size() - 1) / PublicVariable.rows + 1;
	}
	
	

	public boolean deleteUserByCode(String userCode) {
		User u = findById(userCode);
		if (u != null) {
			em().remove(u);
			return true;
		}
		return false;
	}

	private User findById(String id) {
		try {
			User user = em().find(User.class, id);
			return user;
		} catch (Exception e) {
			return null;
		}
		
	}

	public boolean modifyUser(User user) {
		try {
			em().merge(user);
			return true;
		} catch (Exception e) {
			return false;
		}

	}

	public  Integer sumOfAllUser() {
		String jpql = "select u from User u where 1=1";
		TypedQuery<UserView> query = em().createQuery(jpql, UserView.class);
		int SUM = ((query.getResultList().size()) - 1) / PublicVariable.rows + 1;
		return SUM;
	}


	public boolean isExist(String userCode) {
		User u = findById(userCode);
		if (u != null) {
			return true;
		}
		return false;
	}
	
	public boolean isCanLogin(String userCode) {
		User u = findById(userCode);
		if (u != null) {
			Integer roleCode = u.getRole().getRoleCode();
			if(roleCode <= 3){
				return true;
			}
			else{
				return false;
			}
		}
		return false;
	}

	public HashMap<String, Object> getPwdAndSalt(String userCode) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		User u = findById(userCode);
		map.put("salt", u.getSalt());
		map.put("pwd", u.getPassword());
		map.put("role", u.getRole().getRoleCode());
		map.put("manageCode", u.getRole().getRoleCode()<4?u.getDept().getDeptCode():u.getEnterprise().getEntCode());
		return map;
	}

	public List<UserView> getEntUserListByDeptCode(int deptCode,int page) {
		
		String jpql = "select new %1$s(u) from User u where u.enterprise.entCode in (select e.entCode from Enterprise e where e.department.deptCode=:deptCode)";
		jpql = String.format(jpql, UserView.class.getName());
		TypedQuery<UserView> query = em().createQuery(jpql,UserView.class);
		int head = (page-1)*PublicVariable.rows;
		query.setParameter("deptCode", deptCode);
		query.setFirstResult(head);
		query.setMaxResults(PublicVariable.rows);
		List<UserView> users = query.getResultList();
		
		return users;
	}
	public List<UserView> getEntUserListByDeptCode(int deptCode) {
		
		String jpql = "select new %1$s(u) from User u where u.enterprise.entCode in (select e.entCode from Enterprise e where e.department.deptCode=:deptCode)";
		jpql = String.format(jpql, UserView.class.getName());
		TypedQuery<UserView> query = em().createQuery(jpql,UserView.class);
		query.setParameter("deptCode", deptCode);
		List<UserView> users = query.getResultList();
		return users;
	}

	public int calSumOfGetEntUserListByDeptCode(int deptCode) {
		String jpql = "select new %1$s(u) from User u where u.enterprise.entCode in (select e.entCode from Enterprise e where e.department.deptCode=:deptCode)";
		jpql = String.format(jpql, UserView.class.getName());
		TypedQuery<UserView> query = em().createQuery(jpql,UserView.class);
		query.setParameter("deptCode", deptCode);
		
		
		
		return  ((query.getResultList().size()) - 1) / PublicVariable.rows + 1;
	}
	
	
	public List<UserView> searchUserByKeyAndRoleAndDept(String searchType, String keyWord, int page,
			String manageCode) {
		String temp = "select new %1$s(u) from User u where u.role.roleCode>:roleCodeMin AND u.role.roleCode<:roleCodeMax AND u.enterprise.department.deptCode=:deptCode and";
		
		switch(searchType){
		case "用户姓名":
			temp+=" u.name like :keyword ";
			break;
		case "用户编号":
			temp+=" u.userCode like :keyword ";
			break;
		case "用户所属":
			temp+=" u.enterprise.entName like :keyword ";
		    break;
		}
		temp += "order by u.userCode ";
		String jpql=String.format(temp,UserView.class.getName());
		
		int head = (page-1)*PublicVariable.rows;
		TypedQuery<UserView> query = em().createQuery(jpql, UserView.class);
		query.setParameter("roleCodeMin", 3);
		query.setParameter("roleCodeMax", 8);
		query.setFirstResult(head);
		query.setMaxResults(PublicVariable.rows);
		query.setParameter("keyword", "%"+keyWord+"%");
		query.setParameter("deptCode",Integer.parseInt(manageCode));


		return query.getResultList();
	}
	
	
	public int SumOfSearchUserByKeyAndRoleAndDept(String searchType, String keyWord, int page,
			String manageCode){
		String temp = "select new %1$s(u) from User u where u.role.roleCode>:roleCodeMin AND u.role.roleCode<:roleCodeMax AND u.enterprise.department.deptCode=:deptCode and";
		
		switch(searchType){
		case "用户姓名":
			temp+=" u.name like :keyword ";
			break;
		case "用户编号":
			temp+=" u.userCode like :keyword ";
			break;
		case "用户所属":
			temp+=" u.enterprise.entName like :keyword ";
		    break;
		}
		temp += "order by u.userCode ";
		String jpql=String.format(temp,UserView.class.getName());
		
		TypedQuery<UserView> query = em().createQuery(jpql, UserView.class);
		query.setParameter("roleCodeMin", 3);
		query.setParameter("roleCodeMax", 8);
		query.setParameter("keyword", "%"+keyWord+"%");
		query.setParameter("deptCode",Integer.parseInt(manageCode));
		
		return  ((query.getResultList().size()) - 1) / PublicVariable.rows + 1;
	}
	
	

	public List<UserView> searchUserByKeyAndRole(Integer roleCode,String searchType, String keyWord, int page) {
		String temp = "select new %1$s(u) from User u where u.role.roleCode>:roleCodeMin AND u.role.roleCode<:roleCodeMax and";
		
		switch(searchType){
		case "用户姓名":
			temp+=" u.name like :keyword ";
			break;
		case "用户编号":
			temp+=" u.userCode like :keyword ";
			break;
		case "用户所属":
			if(roleCode == 0 || roleCode == 1){
				temp+=" u.department.deptName like :keyword ";
			}else {
				temp+=" u.enterprise.entName like :keyword ";
			}
		    break;
		}

		temp += "order by u.userCode ";
		String jpql=String.format(temp,UserView.class.getName());
		
		TypedQuery<UserView> query = em().createQuery(jpql, UserView.class);
		switch(roleCode){
		
		case 0:
			query.setParameter("roleCodeMin", 1);
			query.setParameter("roleCodeMax", 3);
			break;
		
		case 1:
			query.setParameter("roleCodeMin", 2);
			query.setParameter("roleCodeMax", 4);
			break;
		case 2:
			query.setParameter("roleCodeMin", 3);
			query.setParameter("roleCodeMax", 8);
		}
		int head = (page-1)*PublicVariable.rows;
		query.setParameter("keyword", "%"+keyWord+"%");
		query.setFirstResult(head);
		query.setMaxResults(PublicVariable.rows);

		return query.getResultList();
	}

	public int SumOfSearchUserByKeyAndRole(Integer roleCode,String searchType, String keyWord, int page){
		String temp = "select new %1$s(u) from User u where u.role.roleCode>:roleCodeMin AND u.role.roleCode<:roleCodeMax and";
		
		switch(searchType){
		case "用户姓名":
			temp+=" u.name like :keyword ";
			break;
		case "用户编号":
			temp+=" u.userCode like :keyword ";
			break;
		case "用户所属":
			if(roleCode == 0 || roleCode == 1){
				temp+=" u.department.deptName like :keyword ";
			}else {
				temp+=" u.enterprise.entName like :keyword ";
			}
		    break;
		}

		temp += "order by u.userCode ";
		String jpql=String.format(temp,UserView.class.getName());
		TypedQuery<UserView> query = em().createQuery(jpql, UserView.class);
		switch(roleCode){
		
		case 0:
			query.setParameter("roleCodeMin", 1);
			query.setParameter("roleCodeMax", 3);
			break;
		
		case 1:
			query.setParameter("roleCodeMin", 2);
			query.setParameter("roleCodeMax", 4);
			break;
		case 2:
			query.setParameter("roleCodeMin", 3);
			query.setParameter("roleCodeMax", 8);
		}
		query.setParameter("keyword", "%"+keyWord+"%");
		
		return  ((query.getResultList().size()) - 1) / PublicVariable.rows + 1;
	}


}
