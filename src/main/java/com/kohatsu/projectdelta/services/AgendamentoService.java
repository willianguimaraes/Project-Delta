package com.kohatsu.projectdelta.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kohatsu.projectdelta.domain.Agendamento;
import com.kohatsu.projectdelta.domain.Cliente;
import com.kohatsu.projectdelta.domain.Profissional;
import com.kohatsu.projectdelta.dto.AgendamentoNewDTO;
import com.kohatsu.projectdelta.exceptions.ObjectNotFoundException;
import com.kohatsu.projectdelta.repositories.AgendamentoRepository;

@Service
public class AgendamentoService {

	@Autowired
	private AgendamentoRepository repo;
	@Autowired
	private ProfissionalService profissionalService;
	@Autowired
	private ClienteService clienteService;
	
	public Agendamento find(Integer id) {
			
		Optional<Agendamento> obj = repo.findById(id);
			
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado: Id: "+id+", Tipo: "+Agendamento.class.getName()));
		
	}
	
	public List<Agendamento> findAll(){
		
		return repo.findAll();
		
	}
	
	public List<Agendamento> agendamentoPorProfissional(Profissional profissional) {
		
		List<Agendamento> lista = repo.findByProfissional(profissional);
	
		return lista;
	}

	public List<Agendamento> agendamentoPorCliente(Cliente cliente) {

		List<Agendamento> lista = repo.findByCliente(cliente);
		
		return lista;
		
	}

	@Transactional
	public Agendamento insert(Agendamento obj) {

		obj.setId(null);
		obj.setSemana(obj.getSemana());
		obj.setDia(obj.getDia());
		obj.setHorario(obj.getHorario());
		obj.setProfissional(profissionalService.find(obj.getProfissional().getId()));
		obj.setCliente(clienteService.find(obj.getCliente().getId()));
		
		obj = repo.save(obj);
		
		return obj;
		
	}
	
	public Agendamento fromDTO(@Valid AgendamentoNewDTO objDto) {
		
		Profissional profissional = profissionalService.find(objDto.getIdProf());
		Cliente cliente = clienteService.find(objDto.getIdClient());

		Agendamento agend = new Agendamento(null, objDto.getSemana(), objDto.getDia(), objDto.getHorario(), profissional, cliente);
		
		return agend;
		
	}

	
}
