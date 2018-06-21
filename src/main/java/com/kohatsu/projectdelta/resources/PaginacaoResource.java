package com.kohatsu.projectdelta.resources;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.kohatsu.projectdelta.domain.Cliente;
import com.kohatsu.projectdelta.dto.ClienteNewDTO;
import com.kohatsu.projectdelta.services.ClienteService;

@RestController
@RequestMapping(value="/aluno")
public class PaginacaoResource {

	@Autowired
	private ClienteService service;
	
	private ClienteNewDTO client;
	private Cliente c;
	
	@GetMapping(value="/listarAluno/{idAluno}")
	public ModelAndView cadastrarAluno(@PathVariable("idAluno") Integer id) {
		
		c = service.find(id);
		
		ModelAndView mv = new ModelAndView("aluno/listarAluno");
		
		mv.addObject("cliente", c);
		
		return mv;
		
	}
	
	/*@GetMapping(value="/cadastrarAluno")
	public ModelAndView insert(@ModelAttribute ClienteNewDTO objDto) {
		
		Cliente obj = service.fromDTO(objDto);
		obj = service.insert(obj);
		
		return new ModelAndView("redirect:/");
		
	}*/
	
}
