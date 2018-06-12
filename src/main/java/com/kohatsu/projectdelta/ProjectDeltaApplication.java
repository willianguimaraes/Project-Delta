package com.kohatsu.projectdelta;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.kohatsu.projectdelta.domain.Cliente;
import com.kohatsu.projectdelta.domain.Endereco;
import com.kohatsu.projectdelta.domain.Profissional;
import com.kohatsu.projectdelta.domain.Telefone;
import com.kohatsu.projectdelta.repositories.ClienteRepository;
import com.kohatsu.projectdelta.repositories.EnderecoRepository;
import com.kohatsu.projectdelta.repositories.ProfissionalRepository;
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
	
	public static void main(String[] args) {
		SpringApplication.run(ProjectDeltaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Cliente cli = new Cliente(null, "Alisson", 'M',"35447310881");
		Profissional pro = new Profissional(null, "Anderson", "62828648010", "anderson@gmail.com");
		
		Endereco end = new Endereco("Rua Maria Madalena", "124", null, "Jardim Gayon", "86039380", cli);
		
		Telefone tel1 = new Telefone();
		tel1.setId(null);
		tel1.setDdd("43");
		tel1.setNumero("988144287");
		tel1.setCliente(cli);
		
		Telefone tel2 = new Telefone();
		tel2.setId(null);
		tel2.setDdd("43");
		tel2.setNumero("996295333");
		tel2.setProfissional(pro);
		
		cli.getTelefones().addAll(Arrays.asList(tel1));
		pro.getTelefones().addAll(Arrays.asList(tel2));
		
		cli.getEnderecos().addAll(Arrays.asList(end));
		
		clienteRepository.saveAll(Arrays.asList(cli));
		profissionalRepository.saveAll(Arrays.asList(pro));
		telefoneRepository.saveAll(Arrays.asList(tel1, tel2));
		enderecoRepository.saveAll(Arrays.asList(end));
		
		
	}
	
}
