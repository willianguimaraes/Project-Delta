package com.kohatsu.projectdelta.services;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kohatsu.projectdelta.domain.Cliente;
import com.kohatsu.projectdelta.domain.Endereco;
import com.kohatsu.projectdelta.domain.Telefone;
import com.kohatsu.projectdelta.dto.ClienteNewDTO;
import com.kohatsu.projectdelta.exceptions.ObjectNotFoundException;
import com.kohatsu.projectdelta.repositories.ClienteRepository;
import com.kohatsu.projectdelta.repositories.EnderecoRepository;
import com.kohatsu.projectdelta.repositories.TelefoneRepository;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository repo;
	@Autowired
	private EnderecoRepository enderecoRepository;
	@Autowired
	private TelefoneRepository telefoneRepository;

		
	public Cliente find(Integer id) {
			
		Optional<Cliente> obj = repo.findById(id);
			
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado: Id: "+id+", Tipo: "+Cliente.class.getName()));
		
	}
	
	public List<Cliente> findAll(){
		
		return repo.findAll();
		
	}
	
	@Transactional
	public Cliente insert(Cliente obj) {
		
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
	
	public Cliente fromDTO(ClienteNewDTO objDto) {
		
		if(objDto.getId() == null) {
			
			Endereco endereco = new Endereco(null, objDto.getLogradouro(), objDto.getNumeroEnd(), objDto.getComplemento(), 
					objDto.getBairro(),	objDto.getCep());
			Telefone telefone = new Telefone(null, objDto.getDdd(), objDto.getNumeroTel());
			Cliente cliente = new Cliente(null, objDto.getNome(), objDto.getSexo(), objDto.getCpf(),objDto.getEmail(), endereco, telefone);
			
			endereco.getClientes().addAll(Arrays.asList(cliente));
			telefone.getClientes().addAll(Arrays.asList(cliente));
			
			return cliente;
			
		} else {
			
			Endereco endereco = new Endereco(objDto.getIdEnd(), objDto.getLogradouro(), objDto.getNumeroEnd(), objDto.getComplemento(), objDto.getBairro(), objDto.getCep());
			Telefone telefone = new Telefone(objDto.getIdTel(), objDto.getDdd(), objDto.getNumeroTel());
			Cliente cliente = new Cliente(objDto.getId(), objDto.getNome(), objDto.getSexo(), objDto.getCpf(),objDto.getEmail(), endereco, telefone);
			
			endereco.getClientes().addAll(Arrays.asList(cliente));
			telefone.getClientes().addAll(Arrays.asList(cliente));
			
			return cliente;
			
		}
		
	}

	public Cliente update(Cliente obj) {
		
		
		Cliente newObj =  find(obj.getId());
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
		newObj.setSexo(obj.getSexo());
		newObj.setCpf(obj.getCpf());
		newObj.setEmail(obj.getEmail());
		newObj.setEndereco(end.get());
		newObj.setTelefone(tel.get());
		
		enderecoRepository.save(end.get());
		telefoneRepository.save(tel.get());
		return repo.save(newObj);
		
	}
	
}
