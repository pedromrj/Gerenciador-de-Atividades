package br.com.unifacisa.pp.gym.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.unifacisa.pp.gym.models.Aluno;

public interface AlunoRepository extends JpaRepository<Aluno, Integer>{
	
	public Aluno findByEmail(String email);
	
}
