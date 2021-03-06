package com.dambroski.domain;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import com.dambroski.domain.dto.ClienteInsertDTO;
import com.dambroski.domain.enuns.Perfil;
import com.dambroski.domain.enuns.TipoCliente;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;


@Entity
public class Cliente implements Serializable {
	private static final long serialVersionUID = 1L;

	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String nome;

	
	private String email;

	private String cpfOuCnpj;

	private Integer tipo;
	
	@JsonProperty(access = Access.WRITE_ONLY)
	private String senha;

	@ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable(name = "PERFIS")
	private Set<Integer> perfis = new HashSet<>();
	
	
	@ElementCollection
	@CollectionTable(name = "TELEFONES")
	private Set<String> telefones = new HashSet<>();

	@OneToMany(mappedBy = "cliente")
	@Cascade(CascadeType.ALL)
	private List<Endereco> enderecos = new ArrayList<>();

	@JsonProperty(access = Access.WRITE_ONLY)
	@OneToMany(mappedBy = "cliente")
	private List<Pedido> pedidos = new ArrayList<>();

	
	public Cliente() {
		addPerfil(Perfil.CLIENTE);
	}

	public Cliente(String nome, String senha, String email, String cpfOuCnpj, TipoCliente tipo) {
		addPerfil(Perfil.CLIENTE);
		this.nome = nome;
		this.email = email;
		this.cpfOuCnpj = cpfOuCnpj;
		this.tipo = tipo.getCod();
		this.senha = senha;
	}

	public Cliente(ClienteInsertDTO clienteInsertDTO) {
		addPerfil(Perfil.CLIENTE);
		this.senha = clienteInsertDTO.getSenha();
		this.nome = clienteInsertDTO.getNome();
		this.email = clienteInsertDTO.getEmail();
		this.cpfOuCnpj = clienteInsertDTO.getCpfOuCnpj();
		this.tipo = clienteInsertDTO.getTipo();
		
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public String getCpfOuCnpj() {
		return cpfOuCnpj;
	}

	public void setCpfOuCnpj(String cpfOuCnpj) {
		this.cpfOuCnpj = cpfOuCnpj;
	}

	public TipoCliente getTipo() {
		return TipoCliente.toEnum(tipo);
	}

	public void setTipo(TipoCliente tipo) {
		this.tipo = tipo.getCod();
	}

	public List<Endereco> getEnderecos() {
		return enderecos;
	}

	public Set<String> getTelefones() {
		return telefones;
	}

	public List<Pedido> getPedidos() {
		return pedidos;
	}

	public Endereco getEnderecoDeEntrega(Integer id) {
		Endereco endereco = enderecoEntrega(enderecos, id);
		return endereco;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Set<Perfil> getPerfis() {
		Set<Perfil> perf = new HashSet<>();	
		for(Integer x : perfis) {
			perf.add(Perfil.getPerfil(x));
		}return perf;
	}
	
	public void addPerfil(Perfil perfil) {
		perfis.add(perfil.getCod());
	}

	public Endereco enderecoEntrega(List<Endereco> enderecos, Integer id) {
		Endereco enderecoEnt = new Endereco();
		for (Endereco x : enderecos) {
			if (x.getId() == id) {
				enderecoEnt = x;
			}
		}
		return enderecoEnt;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cliente other = (Cliente) obj;
		return Objects.equals(id, other.id);
	}

}

