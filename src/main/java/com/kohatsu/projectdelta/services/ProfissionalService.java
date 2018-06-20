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
import com.kohatsu.projectdelta.repositories.TelefoneRepository;

@Service
public class ProfissionalService {

	@Autowired
	private ProfissionalRepository repo;
	@Autowired
	private EnderecoRepository enderecoRepository;
	@Autowired
	private TelefoneRepository telefoneRepository;
		
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
		telefoneRepository.save(obj.getTelefone());
		
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

		if(objDto.getId() == null) {
			
			Endereco endereco = new Endereco(null, objDto.getLogradouro(), objDto.getNumeroEnd(), objDto.getComplemento(), objDto.getBairro(), objDto.getCep());
			Telefone telefone = new Telefone(null, objDto.getDdd(), objDto.getNumeroTel());
			Profissional profissional = new Profissional(null, objDto.getNome(), objDto.getCpf(), objDto.getEmail(), endereco, telefone);
			
			endereco.getProfissionais().addAll(Arrays.asList(profissional));
			telefone.getProfissionais().addAll(Arrays.asList(profissional));
			
			return profissional;
			
		} else {
			
			Endereco endereco = new Endereco(objDto.getIdEnd(), objDto.getLogradouro(), objDto.getNumeroEnd(), objDto.getComplemento(), objDto.getBairro(), objDto.getCep());
			Telefone telefone = new Telefone(objDto.getIdTel(), objDto.getDdd(), objDto.getNumeroTel());
			Profissional profissional = new Profissional(objDto.getId(), objDto.getNome(), objDto.getCpf(), objDto.getEmail(), endereco, telefone);
			
			endereco.getProfissionais().addAll(Arrays.asList(profissional));
			telefone.getProfissionais().addAll(Arrays.asList(profissional));
			
			return profissional;
			
		}
		
		
		
	}
	
	public Profissional update(Profissional obj) {
		
		Profissional newObj =  find(obj.getId());
		Optional<Endereco> end = enderecoRepository.findById(obj.getEndereco().getId());
		Optional<Telefone> tel = telefoneRepository.findById(obj.getTelefone().getId());
		
		end.get().setId(obj.getEndereco().getId());
		end.get().setLogradouro(obj.getEndereco().getLogradouro());
		end.get().setNumero(obj.getEndereco().getNumero());
		end.get().setComplemento(obj.getEndereco().getComplemento());
		end.get().setBairro(obj.getEndereco().getBairro());
		end.get().setCep(obj.getEndereco().getCep());
		
		tel.get().setId(obj.getTelefone().getId());
		tel.get().setDdd(obj.getTelefone().getDdd());
		tel.get().setNumero(obj.getTelefone().getNumero());
		
		newObj.setNome(obj.getNome());
		newObj.setCpf(obj.getCpf());
		newObj.setEmail(obj.getEmail());
		newObj.setEndereco(end.get());
		newObj.setTelefone(tel.get());
		
		enderecoRepository.save(end.get());
		telefoneRepository.save(tel.get());
		return repo.save(newObj);
		
	}
	
	
}
