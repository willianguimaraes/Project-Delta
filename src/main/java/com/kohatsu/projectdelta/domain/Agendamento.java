package com.kohatsu.projectdelta.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
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
	
	@JsonBackReference
	/*@JsonManagedReference*/
	@ManyToMany(mappedBy="agendamentos")
	private List<Profissional> profissionais = new ArrayList<>();
		
	
	public Agendamento() {
		
	}


	public Agendamento(Integer id, Semanas semana, Date dia, Date horario) {
		super();
		this.id = id;
		this.semana = semana.getCod();
		this.dia = dia;
		this.horario = horario;
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
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
	
	public List<Profissional> getProfissionais() {
		return profissionais;
	}
	
	public void setProfissionais(List<Profissional> profissionais) {
		this.profissionais = profissionais;
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
