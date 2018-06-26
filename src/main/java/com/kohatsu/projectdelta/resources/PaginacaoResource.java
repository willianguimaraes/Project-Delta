package com.kohatsu.projectdelta.resources;

import java.util.Arrays;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.kohatsu.projectdelta.domain.Agendamento;
import com.kohatsu.projectdelta.domain.Cliente;
import com.kohatsu.projectdelta.domain.Profissional;
import com.kohatsu.projectdelta.domain.Servico;
import com.kohatsu.projectdelta.domain.enums.Semanas;
import com.kohatsu.projectdelta.dto.AgendamentoNewDTO;
import com.kohatsu.projectdelta.dto.ClienteNewDTO;
import com.kohatsu.projectdelta.dto.ProfissionalNewDTO;
import com.kohatsu.projectdelta.dto.ServicoNewDTO;
import com.kohatsu.projectdelta.services.AgendamentoService;
import com.kohatsu.projectdelta.services.ClienteService;
import com.kohatsu.projectdelta.services.ProfissionalService;
import com.kohatsu.projectdelta.services.ServicoService;

@RestController
/*@RequestMapping(value="/aluno")*/
public class PaginacaoResource {

	@Autowired
	private ClienteService service;
	@Autowired
	private ProfissionalService profissionalService;
	@Autowired
	private ServicoService servicoService;
	@Autowired
	private AgendamentoService agendamentoService;
	
/*	private ClienteNewDTO client;
	private Cliente c;*/
	
	@RequestMapping(value="/home")
	public ModelAndView home() {
		
		ModelAndView mv = new ModelAndView("home/index");
		
		return mv;
		
	}
	
	//Inserir objetos em cada formulários
	@RequestMapping(value="/aluno/cadastrarAluno")
	public ModelAndView formAluno(ModelAndView mv) {
		
		mv.addObject("aluno", new ClienteNewDTO());
		
		return mv;
		
	}
	
	@RequestMapping(value="/profissional/cadastrarProfissional")
	public ModelAndView formProfissional(ModelAndView mv) {
		
		mv.addObject("profissional", new ProfissionalNewDTO());
		
		return mv;
		
	}
	
	@RequestMapping(value="/servico/cadastrarServico")
	public ModelAndView formServico(ModelAndView mv) {
		
		List<Profissional> list = profissionalService.findAll();
		
		mv.addObject("servico", new ServicoNewDTO());
		mv.addObject("listaProf", list);
		
		return mv;
		
	}
	
	@RequestMapping(value="/agendamento/cadastrarAgendamento")
	public ModelAndView formAgendamento(ModelAndView mv) {
		
		List<Semanas> list = Arrays.asList(Semanas.values());
		List<Profissional> listProf = profissionalService.findAll();
		List<Cliente> listClient = service.findAll();
/*		String dia = "";
		String horario = "";*/
		
		mv.addObject("semanas", list);
		mv.addObject("listaProf", listProf);
		mv.addObject("listClient", listClient);
		mv.addObject("agendamento", new AgendamentoNewDTO());
/*		mv.addObject("dia", dia);
		mv.addObject("horario", horario);*/
		
		return mv;
		
	}

	
	//Listar registro no banco de dados
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
	
	@RequestMapping(value="/servico/listarServico", method=RequestMethod.GET)
	public ModelAndView listarServico() {
		
		List<Servico> list = servicoService.findAll();
		
		ModelAndView mv = new ModelAndView("servico/listarServico");
		
		mv.addObject("lista", list);
		
		return mv;
		
	}
	
	@RequestMapping(value="/agendamento/listarAgendamento", method=RequestMethod.GET)
	public ModelAndView listarAgendamento() {
		
		List<Agendamento> list = agendamentoService.findAll();
		
		ModelAndView mv = new ModelAndView("agendamento/listarAgendamento");
		
		mv.addObject("lista", list);
		
		return mv;
		
	}
	
	//Inserir, salvar no banco de dados
	@RequestMapping(value="/aluno/cadastrarAluno", method=RequestMethod.POST)
	public ModelAndView insert(@ModelAttribute ClienteNewDTO objDto) {
		
		Cliente obj = service.fromDTO(objDto);
		service.insert(obj);
		
		return listarAluno();
		
	}
	
	@RequestMapping(value="/profissional/cadastrarProfissional", method=RequestMethod.POST)
	public ModelAndView insert(@ModelAttribute ProfissionalNewDTO objDto) {
		
		Profissional obj = profissionalService.fromDTO(objDto);
		profissionalService.insert(obj);
		
		return listarProfissional();
		
	}
	
	@RequestMapping(value="/servico/cadastrarServico", method=RequestMethod.POST)
	public ModelAndView insert(@ModelAttribute ServicoNewDTO objDto) {
		
		Servico obj = servicoService.fromDTO(objDto);
		servicoService.insert(obj);
		
		return listarServico();
		
	}
	
	@RequestMapping(value="/agendamento/cadastrarAgendamento", method=RequestMethod.POST)
	public ModelAndView insert(@ModelAttribute AgendamentoNewDTO objDto) {
		
		/*System.out.println(dia);
		System.out.println(horario);
		
		SimpleDateFormat fd = new SimpleDateFormat("dd/MM/yyyy");
		SimpleDateFormat fh = new SimpleDateFormat("HH:mm");
		Date objDate = fd.parse(dia);
		Date objHor = fh.parse(horario);
		objDto.setDia(objDate);
		objDto.setHorario(objHor);*/
		
		Agendamento obj = agendamentoService.fromDTO(objDto);
		agendamentoService.insert(obj);
		
		return listarAgendamento();
		
	}
	
	
	//deletando os registro
		@RequestMapping(value="/aluno/listarAluno/{id}")
		public ModelAndView deleteCliente(@PathVariable(value="id") Integer id) {
			
			service.delete(id);
			
			return listarAluno();
			
		}
		
		@RequestMapping(value="/profissional/listarProfissional/{id}")
			public ModelAndView deleteProfissional(@PathVariable(value="id") Integer id) {
				
				profissionalService.delete(id);
				
				return listarProfissional();
				
			}
		
		@RequestMapping(value="/agendamento/listarAgendamento/{id}")
			public ModelAndView deleteAgendamento(@PathVariable(value="id") Integer id) {
				
				agendamentoService.delete(id);
				
				return listarAgendamento();
				
			}
		
		@RequestMapping(value="/servico/listarServico/{id}")
			public ModelAndView deleteServico(@PathVariable(value="id") Integer id) {
				
				servicoService.delete(id);
				
				return listarServico();
				
			}
	
}
