package com.allanborges.helpdesk.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.allanborges.helpdesk.domain.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Integer>{

}
