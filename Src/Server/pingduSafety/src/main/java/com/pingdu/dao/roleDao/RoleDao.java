package com.pingdu.dao.roleDao;

import static com.pingdu.manager.ThreadLocalManager.em;
import org.springframework.stereotype.Repository;

import com.pingdu.entity.role.Role;

@Repository
public class RoleDao {

	public Role getRoleByCode(int roleCode){
		
		Role role = em().find(Role.class, roleCode);
		if(role != null){
			return role;
		}
		return new Role();
		
	}
}
