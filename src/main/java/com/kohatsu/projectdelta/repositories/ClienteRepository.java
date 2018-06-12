package com.kohatsu.projectdelta.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kohatsu.projectdelta.domain.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Integer>{

}
