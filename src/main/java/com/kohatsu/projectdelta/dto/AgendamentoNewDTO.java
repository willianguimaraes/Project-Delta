package com.kohatsu.projectdelta.dto;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class AgendamentoNewDTO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private Integer semana;
	
	@JsonFormat(pattern="dd/MM/yyyy")
	private Date dia;
	@JsonFormat(pattern="HH:mm")
	private Date horario;
	
	private Integer idProf;
	private Integer idClient;
	
	
	public AgendamentoNewDTO() {
		
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getSemana() {
		return semana;
	}

	public void setSemana(Integer semana) {
		this.semana = semana;
	}

	public Date getDia() {
		return dia;
	}

	public void setDia(Date dia) {
		this.dia = dia;
	}

	public Date getHorario() {
		return horario;
	}

	public void setHorario(Date horario) {
		this.horario = horario;
	}

	public Integer getIdProf() {
		return idProf;
	}

	public void setIdProf(Integer idProf) {
		this.idProf = idProf;
	}

	public Integer getIdClient() {
		return idClient;
	}

	public void setIdClient(Integer idClient) {
		this.idClient = idClient;
	}
	
	

}
