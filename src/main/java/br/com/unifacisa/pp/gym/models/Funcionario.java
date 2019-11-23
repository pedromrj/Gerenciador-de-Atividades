package br.com.unifacisa.pp.gym.models;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.unifacisa.pp.gym.enumeration.TipoUsuario;
import lombok.AllArgsConstructor;
import lombok.Data;

@Entity
@Data
@AllArgsConstructor
public class Funcionario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String nome;
	
	private String cpf;
	
	@JsonIgnore
	private String senha;
	
	private String email;
	
	@ElementCollection(fetch=FetchType.EAGER)
	@CollectionTable(name="Permissao_Func")
	private Set<Integer> tipo = new HashSet<Integer>();
	
	private String funcao;
	
	private Double salario;
	
	@OneToMany(cascade = CascadeType.REMOVE)
	private List<Atividade> atividades;
	
	public Set<TipoUsuario> getTipo() {
		return tipo.stream().map(x -> TipoUsuario.toEnum(x)).collect(Collectors.toSet());
	}
	
	public void addTipo(TipoUsuario tipo) {
		this.tipo.add(tipo.getCod());
	}

	public Funcionario(Integer id, String nome, String cpf, String senha, String email, Set<Integer> tipo,
			String funcao, Double salario) {
		this.id = id;
		this.nome = nome;
		this.cpf = cpf;
		this.senha = senha;
		this.email = email;
		this.tipo = tipo;
		this.funcao = funcao;
		this.salario = salario;
	}
	
	public Funcionario() {
	}

	
	
	
	
}
