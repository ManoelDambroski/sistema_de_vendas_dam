package com.dambroski;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.dambroski.services.S3Service;

@SpringBootApplication
public class SistemaDeVendasDamApplication implements CommandLineRunner {

	@Autowired
	private S3Service s3;
	
	public static void main(String[] args) {
		SpringApplication.run(SistemaDeVendasDamApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {		
		s3.uploadFile("//Users//manoeldambroski//Desktop//pequenoPrincipe.jpg");
	}	

	
}