package com.dambroski.services.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.dambroski.domain.dto.ClienteInsertDTO;
import com.dambroski.domain.enuns.TipoCliente;
import com.dambroski.resources.exceptions.FieldMessage;
import com.dambroski.services.validation.uttils.BR;



public class ClienteInsertValidation implements ConstraintValidator<ClienteInsert, ClienteInsertDTO> {

	

	@Override
	public void initialize(ClienteInsert ann) {
	}

	@Override
	public boolean isValid(ClienteInsertDTO clienteInsertDTO, ConstraintValidatorContext context) {

		List<FieldMessage> erros = new ArrayList<>();

		if (clienteInsertDTO.getTipo() == TipoCliente.PESSOAFISICA.getCod()
				&& !BR.isValidCPF(clienteInsertDTO.getCpfOuCnpj())) {
			erros.add(new FieldMessage("CpfOuCnpj", "CPF inválido."));
		}

		if (clienteInsertDTO.getTipo() == TipoCliente.PESSOAJURIDICA.getCod()
				&& !BR.isValidCNPJ(clienteInsertDTO.getCpfOuCnpj())) {
			erros.add(new FieldMessage("CpfOuCnpj", "CNPJ inválido"));

		}

		for (FieldMessage e : erros) {

			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}

		return erros.isEmpty();
	}

}
