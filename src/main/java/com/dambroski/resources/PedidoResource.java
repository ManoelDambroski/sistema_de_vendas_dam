package com.dambroski.resources;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.dambroski.domain.Pedido;
import com.dambroski.services.PedidoService;



@RestController
@RequestMapping("/pedido")
public class PedidoResource {

	@Autowired
	private PedidoService pedidoService;

	@GetMapping("/{id}")
	public ResponseEntity<Pedido> findById(@PathVariable Integer id) {
		Pedido ped = pedidoService.findById(id);
		return ResponseEntity.ok().body(ped);
	}

	@GetMapping
	public Page<Pedido> findByCliente(@RequestParam(name = "nome", defaultValue = "") String nome,
			@RequestParam(name = "page", defaultValue = "0") Integer page,
			@RequestParam(name = "size", defaultValue = "4") Integer size,
			@RequestParam(name = "direction", defaultValue = "ASC") String direction,
			@RequestParam(name = "orderBy", defaultValue = "id") String orderBy) {

		Pageable pageble = PageRequest.of(page, size, Direction.fromString(direction), orderBy);
		Page<Pedido> pedidos = pedidoService.findByCliente(nome, pageble);
		return pedidos;

	}

	@PostMapping
	public ResponseEntity<Void> insert(@RequestBody Pedido pedido) {
		pedidoService.insert(pedido);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(pedido.getId())
				.toUri();
		return ResponseEntity.created(uri).build();

	}

}