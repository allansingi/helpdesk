package com.allanborges.helpdesk.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.allanborges.helpdesk.domain.Tecnico;

public interface TecnicoRepository extends JpaRepository<Tecnico, Integer>{

}
