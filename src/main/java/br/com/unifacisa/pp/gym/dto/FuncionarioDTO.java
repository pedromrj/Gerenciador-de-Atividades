package br.com.unifacisa.pp.gym.dto;

import java.util.Set;

import javax.validation.constraints.Email;

import org.hibernate.validator.constraints.br.CPF;

import br.com.unifacisa.pp.gym.enumeration.TipoUsuario;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FuncionarioDTO {
		
	private String nome;
	
	@CPF
	private String cpf;
	
	@Email
	private String email;
	
	private String senha;
	
	private Set<Integer> tipo;
	
	private String funcao;
	
	private Double salario;
}
