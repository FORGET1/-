package com.pingdu.serviceImpl.roleServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pingdu.dao.roleDao.RoleDao;
import com.pingdu.entity.role.Role;
import com.pingdu.service.roleService.RoleService;

@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
	RoleDao roleDao;
	@Override
	public Role getRoleByCode(int roleCode) {
		
		return roleDao.getRoleByCode(roleCode);
	}

	
	
}
