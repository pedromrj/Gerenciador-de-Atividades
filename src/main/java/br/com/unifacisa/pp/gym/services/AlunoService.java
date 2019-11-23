package br.com.unifacisa.pp.gym.services;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.unifacisa.pp.gym.controllers.exception.AuthorizationException;
import br.com.unifacisa.pp.gym.dto.AlunoDTO;
import br.com.unifacisa.pp.gym.enumeration.TipoUsuario;
import br.com.unifacisa.pp.gym.models.Aluno;
import br.com.unifacisa.pp.gym.models.Atividade;
import br.com.unifacisa.pp.gym.models.Endereco;
import br.com.unifacisa.pp.gym.repositories.AlunoRepository;
import br.com.unifacisa.pp.gym.services.exceptions.NotFoundException;

@Service
public class AlunoService {
	
	@Autowired
	private BCryptPasswordEncoder cryp;
	
	@Autowired
	private EnderecoService endService;
	
	@Autowired
	private AlunoRepository repository;
	
	public Aluno save(AlunoDTO obj) {

		Endereco end = endService.save(new Endereco(
				null,
				obj.getBairro(),
				obj.getRua(),
				obj.getCidade(),
				obj.getNumero(),
				obj.getCep()
				));
		
		Aluno al = new Aluno(null,obj.getNome(),cryp.encode(obj.getSenha()),obj.getCpf(),obj.getEmail(),end,new ArrayList<Atividade>());
		al.addTipo(TipoUsuario.COMUM);
		return repository.save(al);
	}

	public List<Aluno> findAll() {
		return repository.findAll();
	}
	
	public Aluno findById(Integer id) throws NotFoundException, AuthorizationException {
		if (UserService.authenticated().getId() != id && !UserService.authenticated().getAuthorities().contains(new SimpleGrantedAuthority(TipoUsuario.COMUM.getDescricao()))) {
			throw new AuthorizationException("Usuario não autorizado");
		}
		
		return repository.findById(id).orElseThrow(() -> new NotFoundException("Aluno não encontrada"));
	}
	
	public Aluno update(Aluno al) throws NotFoundException {
		if (repository.existsById(al.getId())) {
			return repository.save(al);
		} else {
			throw new NotFoundException("Aluno não encontrada");
		}
	}

	public void deleteOne(Integer id) {
		repository.deleteById(id);
	}

	public Aluno findOne(Integer id, Aluno obj) throws NotFoundException {
		if (repository.existsById(id)) {
			obj.setId(id);
			return repository.save(obj);
		} else {
			throw new NotFoundException("Aluno não encontrada");
		}
	}
	
	public Aluno findByEmail(String email) {
		return repository.findByEmail(email);
	}
}
	