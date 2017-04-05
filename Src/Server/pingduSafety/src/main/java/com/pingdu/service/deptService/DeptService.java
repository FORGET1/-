package com.pingdu.service.deptService;

import java.util.List;

import com.pingdu.entity.department.Department;


public interface  DeptService {
	
   public List<Department> getDepartmentList( int page);
	
   public int getDepartmentPageSize();
    
   public Department turnToModify (String usercode);
    
   public Department getDepartmentInfo(Integer code);
    
   public  List<Department> searchDepartmentList(String searchType, String keyWord,Integer page);

   public  int getSearchDepartmentListPageSize(String searchType, String keyWord);
   
   public boolean deleteDepartment (Integer departmentCode) ;
   
   public boolean modifyDepartment(Department department);
   
   public boolean addDepartment(Department department);
   
   public boolean modifyDepartmentInfo(Department department);
   
   public boolean checkDeptCode(Integer code);
   
   public  List<Department> getAllDepartmentList() ;
}
