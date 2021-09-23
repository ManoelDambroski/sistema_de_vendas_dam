package com.dambroski.services.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.dambroski.repositories.ClienteRepository;


public class ClienteEmailValidation implements ConstraintValidator<ClienteEmail, String> {

	@Autowired
	private ClienteRepository clienteRepository;

	@Override
	public void initialize(ClienteEmail constraintAnnotation) {

	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {

		Boolean valid = !clienteRepository.existsClienteByEmail(value);

		context.disableDefaultConstraintViolation();
		context.buildConstraintViolationWithTemplate("Email já cadastrado").addConstraintViolation();

		return valid;
	}

}
