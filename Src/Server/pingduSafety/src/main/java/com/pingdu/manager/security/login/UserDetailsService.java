package com.pingdu.manager.security.login;

public interface UserDetailsService {
	public LoginUser loadUserByUsername(String username) throws Exception;

}
