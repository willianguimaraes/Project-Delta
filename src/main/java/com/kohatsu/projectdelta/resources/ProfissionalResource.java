package com.kohatsu.projectdelta.resources;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.kohatsu.projectdelta.domain.Profissional;
import com.kohatsu.projectdelta.dto.ProfissionalDTO;
import com.kohatsu.projectdelta.services.ProfissionalService;

@RestController
@RequestMapping(value="/profissionais")
public class ProfissionalResource {

	@Autowired
	private ProfissionalService service;
	
	@RequestMapping(value="/{id}",method=RequestMethod.GET)
	public ResponseEntity<?> find(@PathVariable Integer id) {
		
		Profissional obj = service.find(id);
		
		return ResponseEntity.ok().body(obj);
		
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<ProfissionalDTO>> findAll() {
		
		List<Profissional> list = service.findAll();
		
		List<ProfissionalDTO> listDto = list.stream().map(obj -> new ProfissionalDTO(obj)).collect(Collectors.toList());
		
		return ResponseEntity.ok().body(listDto);
		
	}
	
}
