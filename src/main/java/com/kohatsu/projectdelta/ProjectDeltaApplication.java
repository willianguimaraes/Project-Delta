package com.kohatsu.projectdelta;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.kohatsu.projectdelta.domain.Agendamento;
import com.kohatsu.projectdelta.domain.Cliente;
import com.kohatsu.projectdelta.domain.Endereco;
import com.kohatsu.projectdelta.domain.Profissional;
import com.kohatsu.projectdelta.domain.Servico;
import com.kohatsu.projectdelta.domain.Telefone;
import com.kohatsu.projectdelta.domain.enums.Semanas;
import com.kohatsu.projectdelta.repositories.AgendamentoRepository;
import com.kohatsu.projectdelta.repositories.ClienteRepository;
import com.kohatsu.projectdelta.repositories.EnderecoRepository;
import com.kohatsu.projectdelta.repositories.ProfissionalRepository;
import com.kohatsu.projectdelta.repositories.ServicoRepository;
import com.kohatsu.projectdelta.repositories.TelefoneRepository;

@SpringBootApplication
public class ProjectDeltaApplication implements CommandLineRunner{

	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private EnderecoRepository enderecoRepository;
	@Autowired
	private TelefoneRepository telefoneRepository;
	@Autowired
	private ProfissionalRepository profissionalRepository; 
	@Autowired
	private ServicoRepository servicoRepository; 
	@Autowired
	private AgendamentoRepository agendamentoRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(ProjectDeltaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Endereco end2 = new Endereco(null, null, null, null, null, null);
		Endereco end3 = new Endereco(null, "Rua Doutor Mário Campos", "1500", null, "Parigot de Souza 2", "86082360");
		Endereco end1 = new Endereco(null, "Rua Maria Madalena", "124", null, "Jardim Gayon", "86039380");
		Endereco end4 = new Endereco(null, "Rua Doutor Mário Campos", "1500", null, "Parigot de Souza 2", "86082360");
		
		Profissional pro1 = new Profissional(null, "Anderson", "62828648010", "anderson@gmail.com", end2);
		Profissional pro2 = new Profissional(null, "Anderson", "62828648010", "anderson@gmail.com", end3);
		
		end2.getProfissionais().addAll(Arrays.asList(pro1));
		end3.getProfissionais().addAll(Arrays.asList(pro2));
		
		Cliente cli1 = new Cliente(null, "Alisson", 'M',"35447310881", end1);
		Cliente cli2 = new Cliente(null, "Willian", 'M',"56242986094", end4);
		
		end1.getClientes().addAll(Arrays.asList(cli1));
		end4.getClientes().addAll(Arrays.asList(cli2));
		
		enderecoRepository.saveAll(Arrays.asList(end1, end2, end3, end4));
		profissionalRepository.saveAll(Arrays.asList(pro1, pro2));
		clienteRepository.saveAll(Arrays.asList(cli1, cli2));
		
		Servico serv1 = new Servico(null, "Pilates", "Dar aula de pilates", pro1);
		Servico serv2 = new Servico(null, "Instrutor", "Auxiliar as atividades com os alunos", pro2);
		
		pro1.getServicos().addAll(Arrays.asList(serv2));
		pro2.getServicos().addAll(Arrays.asList(serv1));
		
		
		servicoRepository.saveAll(Arrays.asList(serv1, serv2));
		
		
		
		Telefone tel1 = new Telefone(null, "43", "988144287", cli1);
		/*Telefone tel2 = new Telefone(null, "43", "996295333", pro1);
		Telefone tel3 = new Telefone(null, "43", "996295333", pro2);*/
		Telefone tel4 = new Telefone(null, "43", "996295333", cli2);
		
		
		
		
		
		
		/*pro2.getTelefones().addAll(Arrays.asList(tel3));*/
		/*pro1.getEnderecos().addAll(Arrays.asList(end2));
		pro2.getEnderecos().addAll(Arrays.asList(end3));*/
		/*cli1.getTelefones().addAll(Arrays.asList(tel1));
		cli2.getTelefones().addAll(Arrays.asList(tel4));*/
		

		
		telefoneRepository.saveAll(Arrays.asList(tel1,tel4));
			
		
		
		SimpleDateFormat fd = new SimpleDateFormat("dd/MM/yyyy");
		SimpleDateFormat ft = new SimpleDateFormat("HH:mm");
		
		Agendamento agend1 = new Agendamento(null, Semanas.SEGUNDA_FEIRA.getCod(), fd.parse("13/03/2018"), ft.parse("15:50"), pro1, cli1);
		Agendamento agend2 = new Agendamento(null, Semanas.SEGUNDA_FEIRA.getCod(), fd.parse("13/03/2018"), ft.parse("15:50"), pro2, cli2);
		Agendamento agend3 = new Agendamento(null, Semanas.TERCA_FEIRA.getCod(), fd.parse("15/03/2018"), ft.parse("18:50"), pro2, cli2);
		
		pro1.getAgendamento().addAll(Arrays.asList(agend1));
		pro2.getAgendamento().addAll(Arrays.asList(agend2, agend3));
		cli1.getAgendamentos().addAll(Arrays.asList(agend1));
		cli2.getAgendamentos().addAll(Arrays.asList(agend2, agend3));

		agendamentoRepository.saveAll(Arrays.asList(agend1, agend2, agend3));
		profissionalRepository.saveAll(Arrays.asList(pro1, pro2));
		clienteRepository.saveAll(Arrays.asList(cli1, cli2));
		
	}
	
}
