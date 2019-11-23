package br.com.unifacisa.pp.gym.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Service;

import br.com.unifacisa.pp.gym.models.Aluno;
import br.com.unifacisa.pp.gym.models.Funcionario;
import br.com.unifacisa.pp.gym.security.UserSecutiry;

@Service
public class UserDetailsSecurityService implements UserDetailsService{

	@Autowired
	private AlunoService alService;
	
	@Autowired
	private FuncionarioService funcService;
	
	@Override
	public UserDetails loadUserByUsername(String email) {
		Aluno aluno = alService.findByEmail(email);
		
		Funcionario func = funcService.findByEmail(email);
		
		if(aluno == null && func == null) {
			throw new UsernameNotFoundException(email);
		}
		
		if(aluno != null) {
			return new UserSecutiry(aluno.getId(),aluno.getEmail(),aluno.getSenha(), aluno.getTipo()); 
		}
		return new UserSecutiry(func.getId(),func.getEmail(),func.getSenha(),func.getTipo());
	}
	
	
}
