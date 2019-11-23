	package br.com.unifacisa.pp.gym.services;


import org.springframework.security.core.context.SecurityContextHolder;

import br.com.unifacisa.pp.gym.security.UserSecutiry;

public class UserService {
	
	public static UserSecutiry authenticated() {
		try {
			return (UserSecutiry) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		} catch (Exception e) {
			return null;
		}
	}

}
