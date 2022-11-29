package com.boasaude.planodesaude.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.boasaude.planodesaude.model.PlanoDeSaude;
import com.boasaude.planodesaude.model.PlanoDeSaudeLogin;
import com.boasaude.planodesaude.repository.PlanoDeSaudeRepository;
import com.boasaude.planodesaude.service.PlanoDeSaudeService;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/planodesaude")
public class PlanoDeSaudeController {
	
	@Autowired
	private PlanoDeSaudeRepository repository;
	
	@Autowired
	private PlanoDeSaudeService planoDeSaudeService;
	
	@GetMapping
	public ResponseEntity<List<PlanoDeSaude>> getAllPlanoDeSaude()
	{
		return ResponseEntity.ok(repository.findAll());
	}
	
	@GetMapping("/id/{id}")
	public ResponseEntity<PlanoDeSaude> findByIdPlanoDeSaude(@PathVariable long id)
	{
		return repository.findById(id).map(resp -> ResponseEntity.ok(resp)).orElse(ResponseEntity.notFound().build());
	}
	
	@GetMapping("/nome/{nome}")
	public ResponseEntity<Optional<PlanoDeSaude>> getByNome(@PathVariable String nome)
	{
		return ResponseEntity.ok(repository.findByNome(nome));
	}
	
	@PostMapping
	public ResponseEntity<PlanoDeSaude> post(@RequestBody PlanoDeSaude planoDeSaude)
	{
		return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(planoDeSaude));
	}
	
	@PutMapping("/id/{id}")
	public ResponseEntity<PlanoDeSaude> put(@RequestBody PlanoDeSaude planoDeSaude)
	{
		return ResponseEntity.status(HttpStatus.OK).body(repository.save(planoDeSaude));
	}
	
	@DeleteMapping("/id/{id}")
	public void delete(@PathVariable long id)
	{
		repository.deleteById(id);
	}
	
	@PostMapping("/logar")
	public ResponseEntity<PlanoDeSaudeLogin> Autentication(@RequestBody Optional<PlanoDeSaudeLogin> user) {
		return planoDeSaudeService.logar(user).map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());

	}

	@PostMapping("/cadastrar")
	public ResponseEntity<PlanoDeSaude> Post(@RequestBody PlanoDeSaude usuario) {
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(planoDeSaudeService.cadastrarUsuario(usuario));
	}
}
