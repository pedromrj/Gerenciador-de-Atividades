package br.com.unifacisa.pp.gym.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.unifacisa.pp.gym.models.Funcionario;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Integer>{

	public Funcionario findByEmail(String email);
}
