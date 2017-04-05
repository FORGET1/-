package com.pingdu.dao.deptDao;

import static com.pingdu.manager.ThreadLocalManager.em;


import java.util.List;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.pingdu.entity.user.User;
import com.pingdu.entity.department.Department;
import com.pingdu.utility.PublicVariable;


@Repository
public class DeptDao {
	//待讨论
	public boolean addDepartment(Department dept)
	{	
		//安监局用户添加安监办，这还得跟孙山鑫讨论一下
	    try{
	    	em().persist(dept);			
	    	return true;
	   	}catch(Exception e){
	    	e.printStackTrace();
	    return false;}	
	}
	
	/*检查部门编号是否重复*/
	public boolean checkDeptCode(Integer code)
	{
	
		String jpql=" select u from Department u where u.deptCode = :code ";
		TypedQuery<Department> query = em().createQuery(jpql, Department.class);
		query.setParameter("code", code);
		List<Department> list = query.getResultList();
		int ListSize=list.size();
		if(ListSize==0)	return true;
		else  return false;
			
	}
	
    
	private Department findByCode(Integer deptCode) {
		return em().find(Department.class, deptCode);
	}
	
	
	public boolean deleteDepartment (Integer departmentCode) {
		Department u = findByCode(departmentCode);
			em().remove(u);
			return true;
	}
	
	public Department departmentInfo (int departmentCode)
	{
		return findByCode(departmentCode);
	}
	
	//跳转到安监局的信息界面
	public Department turnToModify (String userCode )
	{
		
		/*String jpql =  new String();
		String temp = "select new %3$s(b.deptCode , b.deptNamr, b.deptAddr, b.contact ,b.contactPhone ,b.deptPhone) "
         			+ " from %1$s a,%2$s b where a.userCode = :userCode and a.department.deptCode = b.deptCode  ";
		jpql= String.format(temp,User.class.getName(),Department.class.getName(),DepartmentView.class.getName());
		TypedQuery<DepartmentView> query = em().createQuery(jpql, DepartmentView.class);
		query.setParameter("userCode", userCode);
		List<DepartmentView> d = query.getResultList();
		if(d.size()==1)
		{
			return d.get(0);
		}
		else 
		{
			return null;
		}*/
		
		
	    String jpql= new String();
		String temp ="select b "
     			+ " from %1$s a,%2$s b where a.userCode = :userCode and a.department.deptCode = b.deptCode  ";
		jpql=String.format(temp, User.class.getName(),Department.class.getName());
		TypedQuery<Department> query2 = em().createQuery(jpql, Department.class);
		query2.setParameter("userCode", userCode);
		List<Department> d2 = query2.getResultList();
		if(d2.size()==1)
		{
			return d2.get(0);
		}
		else 
		{
			return null;
		}
			
		
	}
	
	//修改安监局信息
	public boolean modifyDepartment(Department department)
	{
		Department d = findByCode(department.getDeptCode());
		if (d != null) {
			d.setContact(department.getContact());
			d.setContactPhone(department.getContactPhone());
			d.setDeptAddr(department.getDeptAddr());		
			d.setDeptName(department.getDeptName());
			d.setDeptPhone(department.getDeptPhone());	
		
			try{
			em().merge(d);
			}catch (Exception e) {
				e.printStackTrace();
			}
	
			return true;
		}
		else return false;
			
	}
	
	public  List<Department> getDepartmentList(int page) {
		int rows= PublicVariable.rows;
		int head = (page - 1) * rows;
		String jpql = "select u from Department u where u.deptLevel<>1 ";	
		TypedQuery<Department> query = em().createQuery(jpql, Department.class);
		query.setFirstResult(head);
		query.setMaxResults(rows);
		List<Department> list = query.getResultList();
		return list;
		
	}
	
	public  List<Department> getAllDepartmentList() {
		
		String jpql = "select u from Department u where u.deptLevel<>1  ";	
		TypedQuery<Department> query = em().createQuery(jpql, Department.class);
		List<Department> list = query.getResultList();
		return list;
		
	}
	
	public Department getDepartmentInfo(Integer code)
	{
		return findByCode(code);
	}
	
	public  int getDepartmentPageSize()
	{
		String jpql = "select u from Department u where 1=1 order by u.deptCode ";
		TypedQuery<Department> query = em().createQuery(jpql, Department.class);
		List<Department> list = query.getResultList();
		int ListSize=list.size();
		int rows= PublicVariable.rows;
		if(ListSize==0) return 0;
		int pageSize=ListSize/rows;
		if(ListSize%rows!=0) pageSize=pageSize+1;
		return pageSize;
		
	}
	
	public  List<Department> searchDepartmentList(String searchType, String keyWord,Integer page)
	{
		int rows= PublicVariable.rows;
		int head = (page - 1) * rows;
		String jpql=new String();
		System.out.println("------------------------");
		System.out.println(searchType);
		System.out.println(keyWord);
		System.out.println(page);
		System.out.println("------------------------");
		if (searchType.equals("部门名称")) {
			jpql = "select u from Department u where u.deptName like :keyWord and u.deptLevel=2 order by u.deptCode ";
		}
		if (searchType.equals("部门电话")) {
			jpql = "select u from Department u where u.deptPhone like :keyWord and u.deptLevel=2 order by u.deptCode ";
		}
		TypedQuery<Department> query = em().createQuery(jpql, Department.class);
		query.setParameter("keyWord", "%"+keyWord+"%");
		query.setFirstResult(head);
		query.setMaxResults(rows);
		List<Department> list = query.getResultList();
		return list;
	}
	
	public  int getSearchDepartmentListPageSize(String searchType, String keyWord)
	{
		String jpql=new String();
		if (searchType.equals("部门名称")) {
			jpql = "select u from Department u where u.deptName like :keyWord and u.deptLevel=2 order by u.deptCode";
		}
		if (searchType.equals("部门电话")) {
			jpql = "select u from Department u where u.deptPhone =:keyWord and u.deptLevel=2 order by u.deptCode";
		}
		TypedQuery<Department> query = em().createQuery(jpql, Department.class);
		query.setParameter("keyWord", keyWord);
		List<Department> list = query.getResultList();
		int ListSize=list.size();
		if(ListSize==0) return 0;
		int rows= PublicVariable.rows;
		int pageSize=ListSize/rows;
		if(ListSize%rows!=0) pageSize=pageSize+1;
		return pageSize;
		
	}
	
	//安监局修改安监办信息
	public boolean modifyDepartmentInfo(Department department)
	{
		Department d = findByCode(department.getDeptCode());
		if (d != null) {
			d.setContact(department.getContact());
			d.setContactPhone(department.getContactPhone());
			d.setDeptAddr(department.getDeptAddr());
			if(department.getDeptName().equals(""))
			{
				d.setDeptName("无");
			}
			if(department.getDeptCode()==null)
			{
				return false;
			}
			else d.setDeptName(department.getDeptName());
			d.setDeptPhone(department.getDeptPhone());
		
			try{
			em().merge(d);
			}catch (Exception e) {
				e.printStackTrace();
			}
			return true;
		}
		else return false;
		
	}
	
	
}
