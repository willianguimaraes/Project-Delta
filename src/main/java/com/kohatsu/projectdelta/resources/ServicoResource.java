package com.kohatsu.projectdelta.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.kohatsu.projectdelta.domain.Servico;
/*import com.kohatsu.projectdelta.dto.ServicoDTO;*/
import com.kohatsu.projectdelta.services.ServicoService;

@RestController
@RequestMapping(value="/servicos")
public class ServicoResource {

	@Autowired
	private ServicoService service;
	
	@RequestMapping(value="/{id}",method=RequestMethod.GET)
	public ResponseEntity<?> find(@PathVariable Integer id) {
		
		Servico obj = service.find(id);
		
		return ResponseEntity.ok().body(obj);
		
	}
	
	/*@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<ServicoDTO>> findAll() {
		
		List<Servico> list = service.findAll();
		
		List<ServicoDTO> listDto = list.stream().map(obj -> new ServicoDTO(obj)).collect(Collectors.toList());
		
		return ResponseEntity.ok().body(listDto);
		
	}*/
	
}
