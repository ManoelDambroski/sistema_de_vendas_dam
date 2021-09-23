package com.dambroski.services;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.dambroski.domain.Cidade;
import com.dambroski.domain.Cliente;
import com.dambroski.domain.Endereco;
import com.dambroski.domain.dto.ClienteDTO;
import com.dambroski.domain.dto.ClienteInsertDTO;
import com.dambroski.repositories.ClienteRepository;
import com.dambroski.repositories.EnderecoRepository;
import com.dambroski.services.exceptions.DataIntegrityException;
import com.dambroski.services.exceptions.EntitieNotFoundException;



@Service
public class ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private EnderecoRepository enderecoRepository;

	public Cliente findById(Integer id) {
		Optional<Cliente> cli = clienteRepository.findById(id);
		return cli.orElseThrow(() -> new EntitieNotFoundException("Cliente não encontrado"));
	}

	public Page<ClienteDTO> findAll(Integer page, Integer size, String direction, String orderBy) {

		Pageable pageble = PageRequest.of(page, size, Direction.fromString(direction), orderBy);
		Page<Cliente> clientes = clienteRepository.findAll(pageble);
		Page<ClienteDTO> clientesDTO = clientes.map(obj -> new ClienteDTO(obj));

		return clientesDTO;
	}

	public void update(Integer id, ClienteDTO clienteDTO) {

		Cliente cliente = findById(id);

		if (clienteDTO.getNome() != null) {
			cliente.setNome(clienteDTO.getNome());
		}
		if (clienteDTO.getEmail() != null) {
			cliente.setEmail(clienteDTO.getEmail());
		}

		clienteRepository.save(cliente);
	}

	@Transactional
	public Cliente insert(ClienteInsertDTO clienteInsertDTO) {

		Cliente cliente = new Cliente(clienteInsertDTO);

		if (clienteInsertDTO.getTelefone1() != null) {
			cliente.getTelefones().add(clienteInsertDTO.getTelefone1());
		}
		if (clienteInsertDTO.getTelefone2() != null) {
			cliente.getTelefones().add(clienteInsertDTO.getTelefone2());
		}
		if (clienteInsertDTO.getTelefone3() != null) {
			cliente.getTelefones().add(clienteInsertDTO.getTelefone3());
		}

		cliente = clienteRepository.save(cliente);

		Cidade cidade = new Cidade();
		cidade.setId(clienteInsertDTO.getCidadeId());
		Endereco endereco = new Endereco(clienteInsertDTO.getLogradouro(), clienteInsertDTO.getNumero(),
				clienteInsertDTO.getComplemento(), clienteInsertDTO.getBairro(), clienteInsertDTO.getCep(), cliente,
				cidade);

		endereco = enderecoRepository.save(endereco);
		cliente.getEnderecos().add(endereco);

		return cliente;

	}

	public void delete(Integer id) {

		Cliente cliente = findById(id);

		try {
			clienteRepository.delete(cliente);
		}

		catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Cliente vinculado a pedidos não pode ser deletado");
		}

	}

}