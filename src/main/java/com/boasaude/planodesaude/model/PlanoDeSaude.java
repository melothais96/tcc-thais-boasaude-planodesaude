package com.boasaude.planodesaude.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "tb_plano_de_saude")
public class PlanoDeSaude {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Size(min = 2, max = 255)
	private String nome;

	@Size(min = 2, max = 32)
	private String faixa_etaria;
	
	@Size(min = 4)
	@Column(unique = true)
	private String usuario;

	@Size(min = 5)
	private String email;

	@Size(min = 8)
	private String senha;

	private boolean admin;
	
	@OneToMany(mappedBy = "planoDeSaude",cascade = CascadeType.ALL)
	@JsonIgnoreProperties("planoDeSaude")
	private List<Classificacao> classificacao;
	
	@OneToMany(mappedBy = "planoDeSaude",cascade = CascadeType.ALL)
	@JsonIgnoreProperties("planoDeSaude")
	private List<Categoria> categoria;

	
	public PlanoDeSaude(String nome) {
		this.nome = nome;
	}

	@Deprecated
	public PlanoDeSaude() {
	}

	public boolean isAdmin() {
		return admin;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getFaixa_etaria() {
		return faixa_etaria;
	}

	public void setFaixa_etaria(String faixa_etaria) {
		this.faixa_etaria = faixa_etaria;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public void setAdmin(boolean admin) {
		this.admin = admin;
	}
	
	public List<Classificacao> getClassificacao() {
		return classificacao;
	}

	public void setClassificacao(List<Classificacao> classificacao) {
		this.classificacao = classificacao;
	}

	public List<Categoria> getCategoria() {
		return categoria;
	}

	public void setCategoria(List<Categoria> categoria) {
		this.categoria = categoria;
	}
	
}
