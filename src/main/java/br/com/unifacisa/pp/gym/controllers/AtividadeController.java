package br.com.unifacisa.pp.gym.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.unifacisa.pp.gym.controllers.exception.AuthorizationException;
import br.com.unifacisa.pp.gym.dto.AtividadeDTO;
import br.com.unifacisa.pp.gym.models.Atividade;
import br.com.unifacisa.pp.gym.services.AtividadeService;
import br.com.unifacisa.pp.gym.services.exceptions.NotFoundException;
import io.swagger.annotations.ApiOperation;

@Controller
@RequestMapping("api/v1/atividade")
public class AtividadeController {
	
	@Autowired
	private AtividadeService service;
	
	@ApiOperation( value = "Adicionar uma atividade ao aluno")
	@PreAuthorize("hasRole('ROLE_ADMIN') || hasRole('ROLE_PROFESSOR')")
	@PostMapping
	public ResponseEntity<Atividade> save(@RequestBody AtividadeDTO obj) throws NotFoundException, AuthorizationException {
		return new ResponseEntity<Atividade>(service.save(obj), HttpStatus.CREATED);
	}
	
	@ApiOperation( value = "Buscar todas as atividades criadas")
	@PreAuthorize("hasRole(ROLE_ADMIN)")
	@GetMapping
	public ResponseEntity<List<Atividade>> findAll() {
		return new ResponseEntity<List<Atividade>>(service.findAll(), HttpStatus.OK);
	}
	
	@ApiOperation(value = "Buscar a atividade pelo o id, mas o aluno so pode buscar suas atividades")
	@GetMapping("/{id}")
	public ResponseEntity<Atividade> findById(@PathVariable Integer id) throws NotFoundException {
		return new ResponseEntity<Atividade>(service.findById(id),HttpStatus.OK);
	}
	
	@ApiOperation(value = "Atualizando a atividade")
	@PreAuthorize("hasRole('ROLE_ADMIN') || hasRole('ROLE_ADMIN')")
	@PutMapping("/{id}")
	public ResponseEntity<Atividade> updateAtividade(@PathVariable Integer id, @RequestBody Atividade obj) throws NotFoundException {
		return new ResponseEntity<Atividade>(service.findOne(id,obj), HttpStatus.OK);
	}
	
	@ApiOperation(value = "Adicionar a presenca a atividade passado o id pela URI")
	@PatchMapping("/{id}/presenca")
	public ResponseEntity<Atividade> adicionarPresenca(@PathVariable Integer id) throws NotFoundException, AuthorizationException {
		return new ResponseEntity<Atividade>(service.adicionarPresenca(id), HttpStatus.OK);
	}
	
	@ApiOperation(value = "Deletar uma atividade")
	@PreAuthorize("hasRole('ROLE_ADMIN') || hasRole('ROLE_PROFESSOR')")
	@DeleteMapping("/{id}")
	public ResponseEntity<Atividade> deleteOne(@PathVariable Integer id) {
		service.deleteOne(id);
		return new ResponseEntity<Atividade>(HttpStatus.OK);
	}
	
}
