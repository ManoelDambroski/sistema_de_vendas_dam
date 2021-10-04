package com.dambroski.resources;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.dambroski.domain.Cliente;
import com.dambroski.domain.dto.ClienteDTO;
import com.dambroski.domain.dto.ClienteInsertDTO;
import com.dambroski.services.ClienteService;



@RestController
@RequestMapping("/cliente")
public class ClienteResource {

	@Autowired
	private ClienteService clienteService;

	@GetMapping("/{id}")
	public ResponseEntity<Cliente> findById(@PathVariable Integer id) {

		Cliente cli = clienteService.findById(id);
		return ResponseEntity.ok().body(cli);
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping("/todos")
	public ResponseEntity<Page<ClienteDTO>> findByPage(@RequestParam(name = "page", defaultValue = "0") Integer page,
			@RequestParam(name = "size", defaultValue = "4") Integer size,
			@RequestParam(name = "direction", defaultValue = "ASC") String direction,
			@RequestParam(name = "orderBy", defaultValue = "nome") String orderBy) {
		Page<ClienteDTO> pageC = clienteService.findAll(page, size, direction, orderBy);
		return ResponseEntity.ok().body(pageC);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Void> update(@PathVariable Integer id, @Valid @RequestBody ClienteDTO clienteDTO) {
		clienteService.update(id, clienteDTO);
		return ResponseEntity.ok().build();
	}

	@PostMapping
	public ResponseEntity<Void> insert(@Valid @RequestBody ClienteInsertDTO clienteInsertDTO) {
		Cliente cliente = clienteService.insert(clienteInsertDTO);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(cliente.getId())
				.toUri();
		return ResponseEntity.created(uri).build();
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		clienteService.delete(id);
		return ResponseEntity.ok().build();
	}

}
