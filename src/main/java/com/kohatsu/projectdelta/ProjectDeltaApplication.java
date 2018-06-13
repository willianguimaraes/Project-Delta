package com.kohatsu.projectdelta;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.kohatsu.projectdelta.domain.Cliente;
import com.kohatsu.projectdelta.domain.Endereco;
import com.kohatsu.projectdelta.domain.Profissional;
import com.kohatsu.projectdelta.domain.Servico;
import com.kohatsu.projectdelta.domain.Telefone;
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
	
	public static void main(String[] args) {
		SpringApplication.run(ProjectDeltaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Servico serv = new Servico(null, "Pilates", "Dar aula de pilates");
		
		Cliente cli1 = new Cliente(null, "Alisson", 'M',"35447310881");
		Cliente cli2 = new Cliente(null, "Willian", 'M',"56242986094");
		
		Profissional pro = new Profissional(null, "Anderson", "62828648010", "anderson@gmail.com");
		
		Endereco end1 = new Endereco(null, "Rua Maria Madalena", "124", null, "Jardim Gayon", "86039380", cli2);
		Endereco end2 = new Endereco(null, null, null, null, null, null, pro);
		

		/*end2.setProfissional(pro);*/
		
		Telefone tel1 = new Telefone(null, "43", "988144287", cli1);
		/*Telefone tel2 = new Telefone(null, "43", "996295333", pro);*/
		
		
		cli1.getTelefones().addAll(Arrays.asList(tel1));
		/*cli2.getTelefones().addAll(Arrays.asList(tel1));*/
		/*pro.getTelefones().addAll(Arrays.asList(tel2));*/
		
		/*cli1.getEnderecos().addAll(Arrays.asList(end2));*/
		cli2.getEnderecos().addAll(Arrays.asList(end1));
		pro.getEnderecos().addAll(Arrays.asList(end2));
		
		clienteRepository.saveAll(Arrays.asList(cli1, cli2));
		profissionalRepository.saveAll(Arrays.asList(pro));
		telefoneRepository.saveAll(Arrays.asList(tel1));
		enderecoRepository.saveAll(Arrays.asList(end1, end2));
		servicoRepository.saveAll(Arrays.asList(serv));
		
		
	}
	
}
