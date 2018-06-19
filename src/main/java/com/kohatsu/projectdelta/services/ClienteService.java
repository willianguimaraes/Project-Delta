package com.kohatsu.projectdelta.services;

import java.util.ArrayList;
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
		telefoneRepository.saveAll(obj.getTelefones());
		
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
			Cliente cliente = new Cliente(null, objDto.getNome(), objDto.getSexo(), objDto.getCpf(), endereco);
			Telefone telefone = new Telefone(null, objDto.getDdd(), objDto.getNumeroTel(), cliente);
			
			endereco.getClientes().addAll(Arrays.asList(cliente));
			cliente.getTelefones().add(telefone);
			
			return cliente;
			
		} else {
			
			Endereco endereco = new Endereco(objDto.getIdEnd(), objDto.getLogradouro(), objDto.getNumeroEnd(), objDto.getComplemento(), objDto.getBairro(), objDto.getCep());
			System.out.println(endereco.getId());
			Cliente cliente = new Cliente(objDto.getId(), objDto.getNome(), objDto.getSexo(), objDto.getCpf(), endereco);
			System.out.println(cliente.getId());
			Telefone telefone = new Telefone(objDto.getIdTel(), objDto.getDdd(), objDto.getNumeroTel(), cliente);
			System.out.println(telefone.getId());
			
			endereco.getClientes().addAll(Arrays.asList(cliente));
			cliente.getTelefones().addAll(Arrays.asList(telefone));
			
			return cliente;
			
		}
		
	}

	public Cliente update(Cliente obj, ClienteNewDTO objDto) {
		
		
		Cliente newObj =  find(obj.getId());
		Optional<Endereco> end = enderecoRepository.findById(obj.getEndereco().getId());
		/*List<Telefone> tels = telefoneRepository.findAll();
		
		for(int i = 0; i < tels.size(); i++) {
			
			System.out.println(tels.get(i).getId());
			
		}
		
		
		
		for(Telefone telefone: tels) {
			
			if(objDto.getIdTel() == telefone.getId()) {
				
				telefone.setId(objDto.getIdTel());
				telefone.setDdd(objDto.getDdd());
				telefone.setNumero(objDto.getNumeroTel());
				
				tels.add(telefone);
				
			}
			
		}*/
		
		
		
		/*tel.get().setId(objDto.getIdTel());
		tel.get().setDdd(objDto.getDdd());
		tel.get().setNumero(objDto.getNumeroTel());*/
		
		end.get().setId(obj.getEndereco().getId());
		end.get().setLogradouro(obj.getEndereco().getLogradouro());
		end.get().setNumero(obj.getEndereco().getNumero());
		end.get().setComplemento(obj.getEndereco().getComplemento());
		end.get().setBairro(obj.getEndereco().getBairro());
		end.get().setCep(obj.getEndereco().getCep());
		
		newObj.setNome(obj.getNome());
		newObj.setSexo(obj.getSexo());
		newObj.setCpf(obj.getCpf());
		newObj.setEndereco(end.get());
		/*newObj.setTelefones(tels);*/
		
		enderecoRepository.save(end.get());
		/*telefoneRepository.saveAll(tels);*/
		return repo.save(newObj);
		
	}
	
}
