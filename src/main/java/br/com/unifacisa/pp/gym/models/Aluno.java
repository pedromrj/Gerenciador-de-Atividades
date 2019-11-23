package br.com.unifacisa.pp.gym.models;

import java.util.Date;
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
import javax.persistence.OneToOne;

import org.springframework.hateoas.ResourceSupport;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.unifacisa.pp.gym.enumeration.TipoUsuario;
import lombok.AllArgsConstructor;
import lombok.Data;

@Entity
@Data
@AllArgsConstructor	
public class Aluno {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String nome;
	
	@JsonIgnore
	private String senha;
	
	private String cpf;
	
	private String email;
	
	@ElementCollection(fetch=FetchType.EAGER)
	@CollectionTable(name="Permissao_Aluno")
	private Set<Integer> tipo = new HashSet<Integer>();
	
	@OneToOne(cascade = CascadeType.ALL)
	private Endereco endereco;
	
	@OneToMany
	private List<Atividade> atividades;
	
	public Aluno() {}
	
	public Integer getQtdPresença() {
		return verificandoAtividade(true);
	}
	
	public Integer getQtdFalta() {
		return verificandoAtividade(false);
	}
	
	private Integer verificandoAtividade(Boolean compareceu) {
		int soma = 0;
		for (Atividade atividade : atividades) {
			if(atividade.getInicio().compareTo(new Date()) < 0 && atividade.getCompareceu() == compareceu) {
				soma += 1;
			}
		}
		return soma;
	}
	
	public Set<TipoUsuario> getTipo() {
		return tipo.stream().map(x -> TipoUsuario.toEnum(x)).collect(Collectors.toSet());
	}
	
	public void addTipo(TipoUsuario tipo) {
		this.tipo.add(tipo.getCod());
	}



	public Aluno(Integer id, String nome, String senha, String cpf, String email, Endereco endereco,
			List<Atividade> atividades) {
		this.id = id;
		this.nome = nome;
		this.senha = senha;
		this.cpf = cpf;
		this.email = email;
		this.endereco = endereco;
		this.atividades = atividades;
	}
}	
