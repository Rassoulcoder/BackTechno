package com.example.demo;


import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

import com.example.demo.modele.Annonce;


@SpringBootApplication
public class UserTokenApplication {
	@Autowired
	private RepositoryRestConfiguration repositoryRestConfiguration;
	public static void main(String[] args) {
		SpringApplication.run(UserTokenApplication.class, args);
		
	}
	
	
	public void run(String... args) throws Exception {
		repositoryRestConfiguration.exposeIdsFor(Annonce.class);
		
	}

	@Bean
	public ModelMapper modelMapper()
	{
		return new ModelMapper();
	}
	
}