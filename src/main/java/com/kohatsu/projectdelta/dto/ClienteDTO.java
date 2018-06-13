package com.kohatsu.projectdelta.dto;

import java.io.Serializable;

import com.kohatsu.projectdelta.domain.Cliente;

public class ClienteDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String nome;
	private char sexo;
	private String cpf;
	
	
	public ClienteDTO() {
		super();
	}
	
	
	public ClienteDTO(Cliente obj) {
		
		this.id = obj.getId();
		this.nome=obj.getNome();
		this.sexo=obj.getSexo();
		this.cpf=obj.getCpf();
		
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


	public char getSexo() {
		return sexo;
	}


	public void setSexo(char sexo) {
		this.sexo = sexo;
	}


	public String getCpf() {
		return cpf;
	}


	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	
	
	

}
