package com.kohatsu.projectdelta.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kohatsu.projectdelta.domain.Profissional;
import com.kohatsu.projectdelta.domain.Servico;
import com.kohatsu.projectdelta.exceptions.ObjectNotFoundException;
import com.kohatsu.projectdelta.repositories.ServicoRepository;

@Service
public class ServicoService {

	@Autowired
	private ServicoRepository repo;
	/*@Autowired
	private ProfissionalService profissionalService;*/
		
	public Servico find(Integer id) {
			
		Optional<Servico> obj = repo.findById(id);
			
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado: Id: "+id+", Tipo: "+Servico.class.getName()));
		
	}
	
	public List<Servico> findAll(){
		
		return repo.findAll();
		
	}

	public List<Servico> servicoPorProfissional(Profissional profissional) {

		List<Servico> lista = repo.findByProfissional(profissional);
		
		return lista;
		
	}

	/*@Transactional
	public Servico insert(Servico obj) {

		obj.setId(null);
		obj.setProfissional(Arrays.asList(profissionalService.find(obj.getId())));
		
		obj = repo.save(obj);
		
		return obj;
		
	}*/
	
}
