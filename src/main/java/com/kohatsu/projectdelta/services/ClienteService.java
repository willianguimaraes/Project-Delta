package com.kohatsu.projectdelta.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kohatsu.projectdelta.domain.Cliente;
import com.kohatsu.projectdelta.exceptions.ObjectNotFoundException;
import com.kohatsu.projectdelta.repositories.ClienteRepository;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository repo;
		
	public Cliente find(Integer id) {
			
		Optional<Cliente> obj = repo.findById(id);
			
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado: Id: "+id+", Tipo: "+Cliente.class.getName()));
		
	}
	
	public List<Cliente> findAll(){
		
		return repo.findAll();
		
	}
	
}
