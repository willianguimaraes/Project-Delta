package com.kohatsu.projectdelta.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kohatsu.projectdelta.domain.Agendamento;
import com.kohatsu.projectdelta.exceptions.ObjectNotFoundException;
import com.kohatsu.projectdelta.repositories.AgendamentoRepository;

@Service
public class AgendamentoService {

	@Autowired
	private AgendamentoRepository repo;
		
	public Agendamento find(Integer id) {
			
		Optional<Agendamento> obj = repo.findById(id);
			
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado: Id: "+id+", Tipo: "+Agendamento.class.getName()));
		
	}
	
	public List<Agendamento> findAll(){
		
		return repo.findAll();
		
	}
	
}
