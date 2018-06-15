package com.kohatsu.projectdelta.dto;

import java.io.Serializable;

public class ServicoNewDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String nome, descricao;
	
	private Integer idProf;
	
	public ServicoNewDTO() {
		
	}

	
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Integer getIdProf() {
		return idProf;
	}

	public void setIdProf(Integer idProf) {
		this.idProf = idProf;
	}

	
	
	
}
