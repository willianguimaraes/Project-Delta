package com.kohatsu.projectdelta.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kohatsu.projectdelta.domain.Servico;
import com.kohatsu.projectdelta.exceptions.ObjectNotFoundException;
import com.kohatsu.projectdelta.repositories.ServicoRepository;

@Service
public class ServicoService {

	@Autowired
	private ServicoRepository repo;
		
	public Servico find(Integer id) {
			
		Optional<Servico> obj = repo.findById(id);
			
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado: Id: "+id+", Tipo: "+Servico.class.getName()));
		
	}
	
	public List<Servico> findAll(){
		
		return repo.findAll();
		
	}
	
}
