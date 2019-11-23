package br.com.unifacisa.pp.gym.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.unifacisa.pp.gym.controllers.exception.AuthorizationException;
import br.com.unifacisa.pp.gym.dto.AtividadeDTO;
import br.com.unifacisa.pp.gym.models.Aluno;
import br.com.unifacisa.pp.gym.models.Atividade;
import br.com.unifacisa.pp.gym.models.Funcionario;
import br.com.unifacisa.pp.gym.repositories.AtividadeRepository;
import br.com.unifacisa.pp.gym.services.exceptions.NotFoundException;

@Service
public class AtividadeService {
	
	@Autowired
	private FuncionarioService funcService;
	
	@Autowired
	private AlunoService alService;
	
	@Autowired
	private AtividadeRepository repository;
	
	public Atividade save(AtividadeDTO obj) throws NotFoundException, AuthorizationException {
		
		Aluno al = alService.findById(obj.getPessoaId());
		
		
		
		Funcionario func = funcService.findById(obj.getFuncionarioId());
		
		
		Atividade at = new Atividade(null,obj.getInicio(),obj.getTitulo(),obj.getDescricao(),false,func);
		
		func.getAtividades().add(at);
		
		funcService.update(func);
		
		at = repository.save(at);
		
		al.getAtividades().add(at);
		alService.update(al);
		
		return at;
	}

	public List<Atividade> findAll() {
		return repository.findAll();
	}
	
	public Atividade findById(Integer id) throws NotFoundException {
		return repository.findById(id).orElseThrow(() -> new NotFoundException("Atividade não encontrada"));
	}

	// NÃO IMPLEMENTEI SO POR CAUSA DO REQUISITO
	public void deleteOne(Integer id) {
		repository.deleteById(id);
	}

	public Atividade findOne(Integer id, Atividade obj) throws NotFoundException {
		if (repository.existsById(id)) {
			obj.setId(id);
			return repository.save(obj);
		} else {
			throw new NotFoundException("Pessoa não encontrada");
		}
	}

	public Atividade adicionarPresenca(Integer id) throws NotFoundException, AuthorizationException {
		
		Integer idFunc = UserService.authenticated().getId();
		Atividade at = findById(id);
		
		if (funcService.findById(idFunc).getAtividades().contains(at)) {
			at.setCompareceu(true);
			return repository.save(at);
		}
		
		throw new AuthorizationException("Funcionario não autorizado a marca presenca");
		
	}

	
}
