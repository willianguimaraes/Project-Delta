package com.kohatsu.projectdelta.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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
		enderecoRepository.saveAll(obj.getEnderecos());
		
		return obj;
		
	}
	
public Cliente fromDTO(ClienteNewDTO objDto) {
		
		Cliente cliente = new Cliente(null, objDto.getNome(), objDto.getSexo(), objDto.getCpf());
		Endereco endereco = new Endereco(null, objDto.getLogradouro(), objDto.getNumeroEnd(), objDto.getComplemento(), objDto.getBairro(), objDto.getCep(), cliente);
		Telefone telefone = new Telefone(null, objDto.getDdd(), objDto.getNumeroTel(), cliente);
		
		cliente.getEnderecos().add(endereco);
		cliente.getTelefones().add(telefone);
		
		return cliente;
		
	}
	
}
