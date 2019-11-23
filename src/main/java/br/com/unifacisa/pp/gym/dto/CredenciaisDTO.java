package br.com.unifacisa.pp.gym.dto;


import java.io.Serializable;

public class CredenciaisDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String email;
	
	private String password;
	
	public CredenciaisDTO() {
		
	}
	
	public CredenciaisDTO(String email, String senha) {
		super();
		this.email = email;
		this.password = senha;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
