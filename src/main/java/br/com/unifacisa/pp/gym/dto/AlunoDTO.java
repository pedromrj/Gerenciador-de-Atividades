package br.com.unifacisa.pp.gym.dto;

import java.util.Set;

import javax.validation.constraints.Email;

import org.hibernate.validator.constraints.br.CPF;

import br.com.unifacisa.pp.gym.enumeration.TipoUsuario;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AlunoDTO {
	
	private String nome;
	
	@Email
	private String email;
	
	@CPF
	private String cpf;
	
	private String senha;
	
	private String rua;
	
	private String bairro;
	
	private String cidade;
	
	private Integer numero;
	
	private Integer cep;
	
}
