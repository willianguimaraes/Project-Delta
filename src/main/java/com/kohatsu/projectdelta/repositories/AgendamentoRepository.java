package com.kohatsu.projectdelta.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kohatsu.projectdelta.domain.Agendamento;

public interface AgendamentoRepository extends JpaRepository<Agendamento, Integer>{

}
