package br.com.unifacisa.pp.gym.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.unifacisa.pp.gym.controllers.exception.AuthorizationException;
import br.com.unifacisa.pp.gym.dto.AlunoDTO;
import br.com.unifacisa.pp.gym.models.Aluno;
import br.com.unifacisa.pp.gym.services.AlunoService;
import br.com.unifacisa.pp.gym.services.exceptions.NotFoundException;

@Controller
@RequestMapping("api/v1/aluno")
public class AlunoController {
	
	@Autowired
	private AlunoService service;
	
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PostMapping
	public ResponseEntity<Aluno> save(@RequestBody AlunoDTO obj) {
		return new ResponseEntity<Aluno>(service.save(obj), HttpStatus.CREATED);
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN') || hasRole('ROLE_PROFESSOR')")
	@GetMapping
	public ResponseEntity<List<Aluno>> findAll() {
		return new ResponseEntity<List<Aluno>>(service.findAll(), HttpStatus.OK);
	}
	
	
	@GetMapping("/{id}")
	public ResponseEntity<Aluno> findById(@PathVariable Integer id) throws NotFoundException, AuthorizationException {
		return new ResponseEntity<Aluno>(service.findById(id),HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PutMapping("/{id}")
	public ResponseEntity<Aluno> updateAluno(@PathVariable Integer id, @RequestBody Aluno obj) throws NotFoundException {
		return new ResponseEntity<Aluno>(service.findOne(id,obj), HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteOne(@PathVariable Integer id) {
		service.deleteOne(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
}
