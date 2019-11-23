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

import br.com.unifacisa.pp.gym.dto.FuncionarioDTO;
import br.com.unifacisa.pp.gym.models.Funcionario;
import br.com.unifacisa.pp.gym.services.FuncionarioService;
import br.com.unifacisa.pp.gym.services.exceptions.NotFoundException;

@Controller
@RequestMapping("api/v1/funcionario")
public class FuncionarioController {
	
	@Autowired
	private FuncionarioService service;
	
	
	@PostMapping
	public ResponseEntity<Funcionario> save(@RequestBody FuncionarioDTO obj) {
		return new ResponseEntity<Funcionario>(service.save(obj), HttpStatus.CREATED);
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping
	public ResponseEntity<List<Funcionario>> findAll() {
		return new ResponseEntity<List<Funcionario>>(service.findAll(), HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Funcionario> findById(@PathVariable Integer id) throws NotFoundException {
		return new ResponseEntity<Funcionario>(service.findById(id),HttpStatus.OK);
	}
	
	
	@PutMapping("/{id}")
	public ResponseEntity<Funcionario> updateFuncionario(@PathVariable Integer id, @RequestBody Funcionario obj) throws NotFoundException {
		return new ResponseEntity<Funcionario>(service.findOne(id,obj), HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteOne(@PathVariable Integer id) {
		service.deleteOne(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
}
