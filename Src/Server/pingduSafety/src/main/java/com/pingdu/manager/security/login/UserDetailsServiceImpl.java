package com.pingdu.manager.security.login;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public class UserDetailsServiceImpl implements UserDetailsService {
	public UserDetails loadUserByUsername(String loginName) {
		if (loginName == null || loginName.trim().length() == 0) {
			throw new UsernameNotFoundException("Please input your account！");
		}

		return null;
	}

}
