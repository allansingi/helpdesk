package com.allanborges.helpdesk;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.allanborges.helpdesk.domain.Chamado;
import com.allanborges.helpdesk.domain.Cliente;
import com.allanborges.helpdesk.domain.Tecnico;
import com.allanborges.helpdesk.domain.enums.Perfil;
import com.allanborges.helpdesk.domain.enums.Prioridade;
import com.allanborges.helpdesk.domain.enums.Status;
import com.allanborges.helpdesk.repositories.ChamadoRepository;
import com.allanborges.helpdesk.repositories.ClienteRepository;
import com.allanborges.helpdesk.repositories.TecnicoRepository;

@SpringBootApplication
public class HelpdeskApplication implements CommandLineRunner {

	@Autowired
	private TecnicoRepository tecnicoRepository;
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private ChamadoRepository chamadoRepository;
	
	
	public static void main(String[] args) {
		SpringApplication.run(HelpdeskApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Tecnico tec1 = new Tecnico(null, "Flamengo", "123456789", "flamengo@flamengo.com", "123");
		tec1.addPerfil(Perfil.ADMIN);
		
		Cliente cli1 = new Cliente(null, "Vasco", "000000000", "vasco@vasco.com", "123");
		
		Chamado c1 = new Chamado(null, Prioridade.MEDIA, Status.ANDAMENTO, "Chamado 01", "Primeiro Chamado", tec1, cli1);
		
		tecnicoRepository.saveAll(Arrays.asList(tec1));
		clienteRepository.saveAll(Arrays.asList(cli1));
		chamadoRepository.saveAll(Arrays.asList(c1));
	}

}