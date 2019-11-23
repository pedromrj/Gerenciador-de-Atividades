package br.com.unifacisa.pp.gym.services;

public class UsernameNotFoundException extends Exception {
	
	private static final long serialVersionUID = 1L;

	public UsernameNotFoundException(String msg) {
		super(msg);
	}
}
