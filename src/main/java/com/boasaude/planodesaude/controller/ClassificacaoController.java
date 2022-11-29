package com.boasaude.planodesaude.controller;

import java.util.List;

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

import com.boasaude.planodesaude.model.Classificacao;
import com.boasaude.planodesaude.repository.ClassificacaoRepository;


@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/classificacao")
public class ClassificacaoController {
	@Autowired
	private ClassificacaoRepository repository;
	
	@GetMapping
	public ResponseEntity<List<Classificacao>> getAllClassificacao()
	{
		return ResponseEntity.ok(repository.findAll());
	}
	
	@GetMapping("id/{id}")
	public ResponseEntity<Classificacao> findByIdClassificacao(@PathVariable long id)
	{
		return repository.findById(id).map(resp -> ResponseEntity.ok(resp)).orElse(ResponseEntity.notFound().build());
	}
	
	@GetMapping("/tipo/{tipo}")
	public ResponseEntity<List<Classificacao>> getByTipo(@PathVariable String tipo)
	{
		return ResponseEntity.ok(repository.findAllByTipoContainingIgnoreCase(tipo));
	}
	
	@PostMapping
	public ResponseEntity<Classificacao> post(@RequestBody Classificacao classificacao)
	{
		return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(classificacao));
	}
	
	@PutMapping
	public ResponseEntity<Classificacao> put(@RequestBody Classificacao classificacao)
	{
		return ResponseEntity.status(HttpStatus.OK).body(repository.save(classificacao));
	}
	
	@DeleteMapping("id/{id}")
	public void delete(@PathVariable long id)
	{
		repository.deleteById(id);
	}
}
