package br.com.unifacisa.pp.gym.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.unifacisa.pp.gym.models.Endereco;
import br.com.unifacisa.pp.gym.repositories.EnderecoRepository;
import br.com.unifacisa.pp.gym.services.exceptions.NotFoundException;

@Service
public class EnderecoService {

	@Autowired
	private EnderecoRepository repository;

	public Endereco save(Endereco obj) {
		return repository.save(obj);
	}

	public List<Endereco> findAll() {
		return repository.findAll();
	}

	public Endereco findById(Integer id) throws NotFoundException {
		return repository.findById(id).orElseThrow(() -> new NotFoundException("Pessoa não encontrada"));
	}

	public void deleteOne(Integer id) {
		repository.deleteById(id);
	}

	public Endereco findOne(Integer id, Endereco obj) throws NotFoundException {
		if (repository.existsById(id)) {
			obj.setId(id);
			return repository.save(obj);
		} else {
			throw new NotFoundException("Pessoa não encontrada");
		}
	}

}
