package com.kohatsu.projectdelta.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.persistence.Index;

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

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository repo;
	@Autowired
	private EnderecoRepository enderecoRepository;
		
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
		enderecoRepository.save(obj.getEnderecos());
		
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
			
			Endereco endereco = new Endereco(null, objDto.getLogradouro(), objDto.getNumeroEnd(), objDto.getComplemento(), objDto.getBairro(), objDto.getCep());
			Cliente cliente = new Cliente(null, objDto.getNome(), objDto.getSexo(), objDto.getCpf(), endereco);
			Telefone telefone = new Telefone(null, objDto.getDdd(), objDto.getNumeroTel(), cliente);
			
			endereco.getClientes().addAll(Arrays.asList(cliente));
			cliente.getTelefones().add(telefone);
			
			return cliente;
			
		} else {
			
			Endereco endereco = new Endereco(objDto.getIdEnd(), objDto.getLogradouro(), objDto.getNumeroEnd(), objDto.getComplemento(), objDto.getBairro(), objDto.getCep());
			Cliente cliente = new Cliente(objDto.getId(), objDto.getNome(), objDto.getSexo(), objDto.getCpf(), endereco);
			/*Telefone telefone = new Telefone(objDto.getIdTel(), objDto.getDdd(), objDto.getNumeroTel(), cliente);*/
			
			endereco.getClientes().addAll(Arrays.asList(cliente));
			/*cliente.getEnderecos().addAll(Arrays.asList(endereco));*/
			/*cliente.getTelefones().add(telefone);*/
			
			return cliente;
			
		}
		
	}

	public Cliente update(Cliente obj) {
		
		Cliente newObj =  find(obj.getId());

		/*Endereco endereco = enderecoRepository.findById(newObj.getEnderecos().getId());*/
		
		/*end.add(endereco);*/
		
		/*newObj.setEnderecos(Arrays.asList(endereco));*/
		/*newObj.setNome(obj.getNome());
		newObj.setSexo(obj.getSexo());
		newObj.setCpf(obj.getCpf());*/
		
	/*	newObj.getEnderecos().addAll(Arrays.asList(obj.ge))*/
		/*updateData(newObj, obj);*/
		
		return repo.save(newObj);
		
	}
	/*private void updateData(Cliente newObj, Cliente obj) {
		
		newObj.setNome(obj.getNome());
		newObj.setSexo(obj.getSexo());
		newObj.setCpf(obj.getCpf());
		
	}*/
	
}
