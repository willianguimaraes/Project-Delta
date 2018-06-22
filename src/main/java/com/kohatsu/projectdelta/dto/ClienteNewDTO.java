package com.kohatsu.projectdelta.dto;

import java.io.Serializable;

import com.kohatsu.projectdelta.domain.Endereco;

public class ClienteNewDTO implements Serializable{

	private static final long serialVersionUID = 1L;

	private Integer id;
	private String nome;
	private String sexo;
	private String cpf;
	private String email;
	
	private Integer idTel;
	private String ddd;
	private String numeroTel;
	
	private Integer idEnd;
	private String logradouro;
	private String numeroEnd;
	private String complemento;
	private String bairro;
	private String cep;
	
	private Endereco endereco;
	
	
	public ClienteNewDTO() {}
	
	
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
	public String getSexo() {
		return sexo;
	}
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
	public String getEmail() {
		return sexo;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public Integer getIdTel() {
		return idTel;
	}
	public void setIdTel(Integer idTel) {
		this.idTel = idTel;
	}
	public String getDdd() {
		return ddd;
	}
	public void setDdd(String ddd) {
		this.ddd = ddd;
	}
	public String getNumeroTel() {
		return numeroTel;
	}
	public void setNumeroTel(String numeroTel) {
		this.numeroTel = numeroTel;
	}
	public Integer getIdEnd() {
		return idEnd;
	}
	public void setIdEnd(Integer idEnd) {
		this.idEnd = idEnd;
	}
	public String getLogradouro() {
		return logradouro;
	}
	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}
	public String getNumeroEnd() {
		return numeroEnd;
	}
	public void setNumeroEnd(String numeroEnd) {
		this.numeroEnd = numeroEnd;
	}
	public String getComplemento() {
		return complemento;
	}
	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}
	public String getBairro() {
		return bairro;
	}
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	public String getCep() {
		return cep;
	}
	public void setCep(String cep) {
		this.cep = cep;
	}
	
	public Endereco getEndereco() {
		return endereco;
	}
	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
	
	
}
