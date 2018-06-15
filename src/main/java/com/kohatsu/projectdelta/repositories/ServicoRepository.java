package com.kohatsu.projectdelta.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kohatsu.projectdelta.domain.Profissional;
import com.kohatsu.projectdelta.domain.Servico;

public interface ServicoRepository extends JpaRepository<Servico, Integer>{

	List<Servico> findByProfissional(Profissional profissional);
	
}
