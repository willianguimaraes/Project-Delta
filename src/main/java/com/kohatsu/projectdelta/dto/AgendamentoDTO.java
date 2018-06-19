package com.kohatsu.projectdelta.dto;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kohatsu.projectdelta.domain.Agendamento;

public class AgendamentoDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private Integer semana;
	
	@JsonFormat(pattern="dd/MM/yyyy")
	private Date dia;
	@JsonFormat(pattern="hh:mm")
	private Date horario;
	
	public AgendamentoDTO() {}
	
	

	public AgendamentoDTO(Agendamento obj) {
		super();
		this.id = obj.getId();
		this.semana = obj.getSemana().getCod();
		this.dia = obj.getDia();
		this.horario = obj.getHorario();
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
	
	

}
