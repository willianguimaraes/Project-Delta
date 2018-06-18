package com.kohatsu.projectdelta.services;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kohatsu.projectdelta.domain.Endereco;
import com.kohatsu.projectdelta.domain.Profissional;
import com.kohatsu.projectdelta.domain.Telefone;
import com.kohatsu.projectdelta.dto.ProfissionalNewDTO;
import com.kohatsu.projectdelta.exceptions.ObjectNotFoundException;
import com.kohatsu.projectdelta.repositories.EnderecoRepository;
import com.kohatsu.projectdelta.repositories.ProfissionalRepository;

@Service
public class ProfissionalService {

	@Autowired
	private ProfissionalRepository repo;
	@Autowired
	private EnderecoRepository enderecoRepository;
		
	public Profissional find(Integer id) {
			
		Optional<Profissional> obj = repo.findById(id);
			
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado: Id: "+id+", Tipo: "+Profissional.class.getName()));
		
	}
	
	public List<Profissional> findAll(){
		
		return repo.findAll();
		
	}

	@Transactional
	public Profissional insert(Profissional obj) {

		obj.setId(null);
		obj = repo.save(obj);
		enderecoRepository.save(obj.getEndereco());
		
		return obj;
		
	}
	
	public void delete(Integer id) {
		
		find(id);
		
		
		try {
			
			repo.deleteById(id);
			
		}catch(DataIntegrityViolationException e) {
			
			throw new DataIntegrityViolationException("Não é possível excluir pois há entidades relacionadas.");
			
		}
		
	}

	public Profissional fromDTO(@Valid ProfissionalNewDTO objDto) {

		Endereco endereco = new Endereco(null, objDto.getLogradouro(), objDto.getNumeroEnd(), objDto.getComplemento(), objDto.getBairro(), objDto.getCep());
		Profissional profissional = new Profissional(null, objDto.getNome(), objDto.getCpf(), objDto.getEmail(), endereco);
		Telefone telefone = new Telefone(null, objDto.getDdd(), objDto.getNumeroTel(), profissional);
		
		endereco.getProfissionais().addAll(Arrays.asList(profissional));
		profissional.getTelefones().add(telefone);
		
		return profissional;
		
	}
	
	
}
