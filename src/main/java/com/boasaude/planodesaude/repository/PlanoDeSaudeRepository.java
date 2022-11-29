package com.boasaude.planodesaude.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.boasaude.planodesaude.model.PlanoDeSaude;

@Repository
public interface PlanoDeSaudeRepository extends JpaRepository<PlanoDeSaude, Long>{
	public Optional<PlanoDeSaude> findByNome(String nome);
	public Optional<PlanoDeSaude> findByUsuario(String usuario);
}
