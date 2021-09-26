package com.dambroski.config;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.dambroski.services.DBService;
import com.dambroski.services.EmailService;
import com.dambroski.services.SmtpMailService;


@Configuration
@Profile("dev")
public class DevConfig {
	@Autowired
	private DBService dbService;
	@Bean
	public boolean initiateDB() throws ParseException {
		dbService.instantiateTestDatabase();
		return true;
	}

	@Bean
	public EmailService emailService() {
		return new SmtpMailService();
	}

}
