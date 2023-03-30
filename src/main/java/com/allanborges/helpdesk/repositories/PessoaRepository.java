package com.allanborges.helpdesk.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.allanborges.helpdesk.domain.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Integer>{

}
