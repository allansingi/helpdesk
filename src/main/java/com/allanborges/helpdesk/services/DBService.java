package com.allanborges.helpdesk.services;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.allanborges.helpdesk.domain.Chamado;
import com.allanborges.helpdesk.domain.Cliente;
import com.allanborges.helpdesk.domain.Tecnico;
import com.allanborges.helpdesk.domain.enums.Perfil;
import com.allanborges.helpdesk.domain.enums.Prioridade;
import com.allanborges.helpdesk.domain.enums.Status;
import com.allanborges.helpdesk.repositories.ChamadoRepository;
import com.allanborges.helpdesk.repositories.ClienteRepository;
import com.allanborges.helpdesk.repositories.TecnicoRepository;

@Service
public class DBService {

	@Autowired
	private TecnicoRepository tecnicoRepository;
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private ChamadoRepository chamadoRepository;

	public void instanciaDB() {
		/*
		Tecnico tec1 = new Tecnico(null, "Flamengo", "123456789", "flamengo@flamengo.com", "123");
		tec1.addPerfil(Perfil.ADMIN);

		Cliente cli1 = new Cliente(null, "Vasco", "000000000", "vasco@vasco.com", "123");

		Chamado c1 = new Chamado(null, Prioridade.MEDIA, Status.ANDAMENTO, "Chamado 01", "Primeiro Chamado", tec1,
				cli1);
		*/
		
		Tecnico tec2 = new Tecnico(null, "Fluminense", "242424242", "fluminense@fluminense.com", "123");
		tec2.addPerfil(Perfil.ADMIN);

		Cliente cli2 = new Cliente(null, "Botafogo", "987654321", "botafogo@botafogo.com", "123");

		Chamado c2 = new Chamado(null, Prioridade.ALTA, Status.ABERTO, "Chamado 02", "Segundo Chamado", tec2,
				cli2);

		tecnicoRepository.saveAll(Arrays.asList(tec2));
		clienteRepository.saveAll(Arrays.asList(cli2));
		chamadoRepository.saveAll(Arrays.asList(c2));
	}
}