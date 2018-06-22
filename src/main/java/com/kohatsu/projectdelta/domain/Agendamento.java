package com.kohatsu.projectdelta.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kohatsu.projectdelta.domain.enums.Semanas;

@Entity
public class Agendamento implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private Integer semana;
	
	@JsonFormat(pattern="dd/MM/yyyy")
	private Date dia;
	@JsonFormat(pattern="HH:mm")
	private Date horario;
	
	@JsonIgnore
	@ManyToOne()
	@JoinColumn(name="profissional_id")
	private Profissional profissional;
	
	@JsonIgnore
	@ManyToOne()
	@JoinColumn(name="cliente_id")
	private Cliente cliente;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="servico_id")
	private Servico servico;
	
	public Agendamento() {
		
	}


	public Agendamento(Integer id, Integer semana, Date dia, Date horario, Profissional pro, Cliente cliente,Servico servico) {
		super();
		this.id = id;
		this.semana = semana;
		this.dia = dia;
		this.horario = horario;
		this.profissional=pro;
		this.cliente = cliente;
		this.servico = servico;
	}
	



	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}

	public  Servico getServico() {
		return servico;
	}
	public void setServico(Servico servico) {
		this.servico = servico;
	}
	
	public Semanas getSemana() {
		return Semanas.toEnum(semana);
	}


	public void setSemana(Semanas semana) {
		this.semana = semana.getCod();
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
	
	public Profissional getProfissional() {
		return profissional;
	}
	
	public void setProfissional(Profissional profissional) {
		this.profissional = profissional;
	}
	
	public Cliente getCliente() {
		return cliente;
	}
	
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Agendamento other = (Agendamento) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
	
	
	

}
