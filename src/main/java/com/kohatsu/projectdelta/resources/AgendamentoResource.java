package com.kohatsu.projectdelta.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.kohatsu.projectdelta.domain.Agendamento;
import com.kohatsu.projectdelta.domain.Cliente;
import com.kohatsu.projectdelta.domain.Profissional;
/*import com.kohatsu.projectdelta.dto.AgendamentoDTO;*/
import com.kohatsu.projectdelta.services.AgendamentoService;
import com.kohatsu.projectdelta.services.ClienteService;
import com.kohatsu.projectdelta.services.ProfissionalService;

@RestController
@RequestMapping(value="/agendamentos")
public class AgendamentoResource {

	@Autowired
	private AgendamentoService service;
	@Autowired
	private ProfissionalService profissionalService;
	@Autowired
	private ClienteService clienteService;
	
	
	
	@RequestMapping(value="/profissional/{id}",method=RequestMethod.GET)
	public List<Agendamento> findByProfissional(@PathVariable("id") Integer id ) {
		
		Profissional profissional = profissionalService.find(id);
		
		List<Agendamento> lista = service.agendamentoPorProfissional(profissional);
		
		return lista;
	}
	
	@RequestMapping(value="/cliente/{id}",method=RequestMethod.GET)
	public List<Agendamento> findByCliente(@PathVariable("id") Integer id ) {
		
		Cliente cliente = clienteService.find(id);
		
		List<Agendamento> lista = service.agendamentoPorCliente(cliente);
		
		return lista;
	}
	
	
	
	/*@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<AgendamentoDTO>> findAll() {
		
		List<Agendamento> list = service.findAll();
		
		List<AgendamentoDTO> listDto = list.stream().map(obj -> new AgendamentoDTO(obj)).collect(Collectors.toList());
		
		return ResponseEntity.ok().body(listDto);
		
	}*/
	
}
