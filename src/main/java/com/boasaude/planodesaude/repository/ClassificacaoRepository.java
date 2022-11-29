package com.boasaude.planodesaude.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.boasaude.planodesaude.model.Classificacao;

@Repository
public interface ClassificacaoRepository extends JpaRepository<Classificacao, Long>{
	public List<Classificacao> findAllByTipoContainingIgnoreCase(String tipo);

}
