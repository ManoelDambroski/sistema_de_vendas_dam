package com.dambroski.domain.dto;

import java.io.Serializable;
import java.util.Objects;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

import com.dambroski.domain.Cliente;
import com.dambroski.services.validation.ClienteEmail;



public class ClienteDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	@Length(min = 5, max = 120, message = "O tamanho deve ser entre 5 e 120 caracteres")
	@Pattern(regexp = "^\\p{L}+[\\p{L}\\p{Z}\\p{P}]{0,}", message = "O campo deve conter um nome válido")
	private String nome;

	@ClienteEmail
	private String email;

	public ClienteDTO() {

	}

	public ClienteDTO(Cliente cliente) {

		nome = cliente.getNome();
		email = cliente.getEmail();
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public int hashCode() {
		return Objects.hash(nome);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ClienteDTO other = (ClienteDTO) obj;
		return Objects.equals(nome, other.nome);
	}

}

