package com.pingdu.serviceImpl.deptServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pingdu.dao.deptDao.DeptDao;
import com.pingdu.entity.department.Department;
import com.pingdu.service.deptService.DeptService;

@Service
public class DeptServiceImpl implements DeptService {
	
	@Autowired
	private DeptDao deptDao;

	@Override
	public List<Department> getDepartmentList(int page) {
		
		return deptDao.getDepartmentList(page);
	}

	@Override
	public int getDepartmentPageSize() {
		return deptDao.getDepartmentPageSize();
	}

	@Override
	public Department turnToModify(String usercode) {
		return deptDao.turnToModify(usercode);
	}

	@Override
	public Department getDepartmentInfo(Integer code) {
		return deptDao.getDepartmentInfo(code);
	}

	@Override
	public List<Department> searchDepartmentList(String searchType,
			String keyWord, Integer page) {
		return deptDao.searchDepartmentList(searchType, keyWord, page);
	}

	@Override
	public int getSearchDepartmentListPageSize(String searchType, String keyWord) {
		return deptDao.getSearchDepartmentListPageSize(searchType, keyWord);
	}

	@Override
	public boolean deleteDepartment(Integer departmentCode) {
		return deptDao.deleteDepartment(departmentCode);
	}

	@Override
	public boolean modifyDepartment(Department department) {
		
		return deptDao.modifyDepartment(department);
	}

	@Override
	public boolean addDepartment(Department department) {
		
		return deptDao.addDepartment(department);
	}

	@Override
	public boolean modifyDepartmentInfo(Department department) {
		
		return deptDao.modifyDepartmentInfo(department);
	}

	@Override
	public boolean checkDeptCode(Integer code) {
		
		return deptDao.checkDeptCode(code) ;
	}

	@Override
	public List<Department> getAllDepartmentList() {
	
		return deptDao.getAllDepartmentList();
	}

	
	

}
