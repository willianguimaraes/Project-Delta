package com.kohatsu.projectdelta.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kohatsu.projectdelta.domain.Agendamento;
import com.kohatsu.projectdelta.domain.Cliente;
import com.kohatsu.projectdelta.domain.Profissional;

public interface AgendamentoRepository extends JpaRepository<Agendamento, Integer>{

	List<Agendamento> findByProfissional(Profissional profissional);

	List<Agendamento> findByCliente(Cliente cliente);

}
