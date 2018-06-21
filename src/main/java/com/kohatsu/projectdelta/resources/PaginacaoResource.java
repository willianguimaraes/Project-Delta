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
import com.kohatsu.projectdelta.domain.Profissional;
import com.kohatsu.projectdelta.dto.ClienteNewDTO;
import com.kohatsu.projectdelta.services.ClienteService;
import com.kohatsu.projectdelta.services.ProfissionalService;

@RestController
/*@RequestMapping(value="/aluno")*/
public class PaginacaoResource {

	@Autowired
	private ClienteService service;
	@Autowired
	private ProfissionalService profissionalService;
	
/*	private ClienteNewDTO client;
	private Cliente c;*/
	
	@RequestMapping(value="/aluno/listarAluno", method=RequestMethod.GET)
	public ModelAndView listarAluno() {
		
		List<Cliente> list = service.findAll();
		
		ModelAndView mv = new ModelAndView("aluno/listarAluno");
		
		mv.addObject("lista", list);
		
		return mv;
		
	}
	
	@RequestMapping(value="/profissional/listarProfissional", method=RequestMethod.GET)
	public ModelAndView listarProfissional() {
		
		List<Profissional> list = profissionalService.findAll();
		
		ModelAndView mv = new ModelAndView("profissional/listarProfissional");
		
		mv.addObject("lista", list);
		
		return mv;
		
	}
	
	/*@GetMapping(value="/cadastrarAluno")
	public ModelAndView insert(@ModelAttribute ClienteNewDTO objDto) {
		
		Cliente obj = service.fromDTO(objDto);
		obj = service.insert(obj);
		
		return new ModelAndView("redirect:/");
		
	}*/
	
}
