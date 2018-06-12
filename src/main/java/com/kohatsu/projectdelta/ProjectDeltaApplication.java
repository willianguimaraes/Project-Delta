package com.kohatsu.projectdelta;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.kohatsu.projectdelta.domain.Cliente;
import com.kohatsu.projectdelta.domain.Endereco;
import com.kohatsu.projectdelta.domain.Telefone;
import com.kohatsu.projectdelta.repositories.ClienteRepository;
import com.kohatsu.projectdelta.repositories.EnderecoRepository;
import com.kohatsu.projectdelta.repositories.TelefoneRepository;

@SpringBootApplication
public class ProjectDeltaApplication implements CommandLineRunner{

	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private EnderecoRepository enderecoRepository;
	@Autowired
	private TelefoneRepository telefoneRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(ProjectDeltaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Cliente cli = new Cliente(null, "Alisson", 'M',"35447310881");
		Telefone tel = new Telefone(null, "43", "988144287", cli);
		cli.getTelefones().addAll(Arrays.asList(tel));
		
		Endereco end = new Endereco("Rua Maria Madalena", "124", null, "Jardim Gayon", "86039380", cli);
		
		cli.getEnderecos().addAll(Arrays.asList(end));
		
		clienteRepository.saveAll(Arrays.asList(cli));
		telefoneRepository.saveAll(Arrays.asList(tel));
		enderecoRepository.saveAll(Arrays.asList(end));
		
		
	}
	
}
