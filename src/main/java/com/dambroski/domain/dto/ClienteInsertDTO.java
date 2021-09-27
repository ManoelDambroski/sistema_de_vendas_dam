package com.dambroski.domain.dto;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.dambroski.services.validation.ClienteEmail;
import com.dambroski.services.validation.ClienteInsert;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;



@ClienteInsert
public class ClienteInsertDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	
	
	@NotEmpty(message = "O nome não pode ser vazio")
	@Length(min = 5, max = 120, message = "O tamanho deve ser entre 5 e 120 caracteres")
	private String nome;

	@ClienteEmail
	private String email;

	private String cpfOuCnpj;

	private Integer tipo;
	
	@NotEmpty(message = "Campo Obrigatório")
	@JsonProperty(access = Access.WRITE_ONLY)
	private String senha;

	@NotEmpty(message = "Campo Obrigatório")
	private String telefone1;

	private String telefone2;

	private String telefone3;

	@NotEmpty(message = "Campo Obrigatório")
	private String logradouro;

	@NotEmpty(message = "Campo Obrigatório")
	private String numero;

	private String complemento;

	private String bairro;

	@NotEmpty
	private String cep;

	private Integer cidadeId;

	public ClienteInsertDTO() {

	}

	public ClienteInsertDTO(String nome, String senha,  String email, String cpfOuCnpj, Integer tipo, 
			String logradouro, String numero, String bairro, String cep, Integer cidadeId) {
		this.senha = senha;
		this.nome = nome;
		this.email = email;
		this.cpfOuCnpj = cpfOuCnpj;
		this.tipo = tipo;
		this.logradouro = logradouro;
		this.numero = numero;
		this.bairro = bairro;
		this.cep = cep;
		this.cidadeId = cidadeId;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCpfOuCnpj() {
		return cpfOuCnpj;
	}

	public void setCpfOuCnpj(String cpfOuCnpj) {
		this.cpfOuCnpj = cpfOuCnpj;
	}

	public Integer getTipo() {
		return tipo;
	}

	public void setTipo(Integer tipo) {
		this.tipo = tipo;
	}

	public String getTelefone1() {
		return telefone1;
	}

	public void setTelefone1(String telefone1) {
		this.telefone1 = telefone1;
	}

	public String getTelefone2() {
		return telefone2;
	}

	public void setTelefone2(String telefone2) {
		this.telefone2 = telefone2;
	}

	public String getTelefone3() {
		return telefone3;
	}

	public void setTelefone3(String telefone3) {
		this.telefone3 = telefone3;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numeroEndereço) {
		this.numero = numeroEndereço;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public Integer getCidadeId() {
		return cidadeId;
	}

	public void setCidadeId(Integer cidadeId) {
		this.cidadeId = cidadeId;
	}

}
	