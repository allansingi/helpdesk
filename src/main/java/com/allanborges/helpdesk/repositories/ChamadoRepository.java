package com.allanborges.helpdesk.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.allanborges.helpdesk.domain.Chamado;

public interface ChamadoRepository extends JpaRepository<Chamado, Integer>{

}
