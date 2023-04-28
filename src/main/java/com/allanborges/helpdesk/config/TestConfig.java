package com.allanborges.helpdesk.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.allanborges.helpdesk.services.DBService;

@Configuration
//@Profile("test")
public class TestConfig {

	@Autowired
	private DBService dbService;
	
	@Bean
	void instanciaDB() {
		this.dbService.instanciaDB();
	}
}