package com.kohatsu.projectdelta.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.kohatsu.projectdelta.domain.Agendamento;
/*import com.kohatsu.projectdelta.dto.AgendamentoDTO;*/
import com.kohatsu.projectdelta.services.AgendamentoService;

@RestController
@RequestMapping(value="/agendamentos")
public class AgendamentoResource {

	@Autowired
	private AgendamentoService service;
	
	@RequestMapping(value="/{id}",method=RequestMethod.GET)
	public ResponseEntity<?> find(@PathVariable Integer id) {
		
		Agendamento obj = service.find(id);
		
		return ResponseEntity.ok().body(obj);
		
	}
	
	/*@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<AgendamentoDTO>> findAll() {
		
		List<Agendamento> list = service.findAll();
		
		List<AgendamentoDTO> listDto = list.stream().map(obj -> new AgendamentoDTO(obj)).collect(Collectors.toList());
		
		return ResponseEntity.ok().body(listDto);
		
	}*/
	
}
