package com.kohatsu.projectdelta.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kohatsu.projectdelta.domain.Profissional;
import com.kohatsu.projectdelta.domain.Servico;
import com.kohatsu.projectdelta.dto.ServicoNewDTO;
import com.kohatsu.projectdelta.exceptions.ObjectNotFoundException;
import com.kohatsu.projectdelta.repositories.ServicoRepository;

@Service
public class ServicoService {

	@Autowired
	private ServicoRepository repo;
	@Autowired
	private ProfissionalService profissionalService;
		
	public Servico find(Integer id) {
			
		Optional<Servico> obj = repo.findById(id);
			
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado: Id: "+id+", Tipo: "+Servico.class.getName()));
		
	}
	
	public List<Servico> findAll(){
		
		return repo.findAll();
		
	}

	@Transactional
	public Servico insert(Servico obj) {

		obj.setId(null);
		obj.setNome(obj.getNome());
		obj.setDescricao(obj.getDescricao());
		obj.setProfissional(profissionalService.find(obj.getProfissional().getId()));
		
		obj = repo.save(obj);
		
		return obj;
		
	}
	
	public List<Servico> servicoPorProfissional(Profissional profissional) {

		List<Servico> lista = repo.findByProfissional(profissional);
		
		return lista;
		
	}

	public Servico fromDTO(@Valid ServicoNewDTO objDto) {
		
		Profissional profissional = profissionalService.find(objDto.getIdProf());

		Servico servico = new Servico(null, objDto.getNome(), objDto.getDescricao(), profissional);
		
		return servico;
		
	}
	
}
