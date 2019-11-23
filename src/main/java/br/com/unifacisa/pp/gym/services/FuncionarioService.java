package br.com.unifacisa.pp.gym.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.unifacisa.pp.gym.dto.FuncionarioDTO;
import br.com.unifacisa.pp.gym.models.Funcionario;
import br.com.unifacisa.pp.gym.repositories.FuncionarioRepository;
import br.com.unifacisa.pp.gym.services.exceptions.NotFoundException;

@Service
public class FuncionarioService {
	
	@Autowired
	private BCryptPasswordEncoder cryp;
	
	@Autowired
	private FuncionarioRepository repository;
	
	public Funcionario save(FuncionarioDTO obj) {
		
		Funcionario func = new Funcionario(
				null,
				obj.getNome(),
				obj.getCpf(),
				cryp.encode(obj.getSenha()),
				obj.getEmail(),
				obj.getTipo(),
				obj.getFuncao(),
				obj.getSalario()
				);
		
		return repository.save(func);
	}

	public List<Funcionario> findAll() {
		return repository.findAll();
	}
	
	public Funcionario findById(Integer id) throws NotFoundException {
		return repository.findById(id).orElseThrow(() -> new NotFoundException("Pessoa não encontrada"));
	}

	public void deleteOne(Integer id) {
		repository.deleteById(id);
	}
	
	public Funcionario update(Funcionario al) throws NotFoundException {
		if (repository.existsById(al.getId())) {
			return repository.save(al);
		} else {
			throw new NotFoundException("Aluno não encontrada");
		}
	}

	public Funcionario findOne(Integer id, Funcionario obj) throws NotFoundException {
		if (repository.existsById(id)) {
			obj.setId(id);
			return repository.save(obj);
		} else {
			throw new NotFoundException("Pessoa não encontrada");
		}
	}
	
	public Funcionario findByEmail(String email) {
		return repository.findByEmail(email);
	}
}