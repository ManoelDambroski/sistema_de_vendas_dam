package com.dambroski.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dambroski.domain.Produto;
import com.dambroski.services.ProdutoService;



@RestController
@RequestMapping("/produto")
public class ProdutoResource {

	@Autowired
	private ProdutoService produtoService;

	@GetMapping("/{id}")
	public ResponseEntity<Produto> findById(@PathVariable Integer id) {
		Produto prod = produtoService.findById(id);
		return ResponseEntity.ok().body(prod);
	}

	@GetMapping("/find")
	public Page<Produto> findByNomeAndIds(@RequestParam(name = "nome", defaultValue = "") String nome,
			@RequestParam(name = "ids", defaultValue = "") String ids,
			@RequestParam(name = "page", defaultValue = "0") Integer page,
			@RequestParam(name = "size", defaultValue = "10") Integer size,
			@RequestParam(name = "direction", defaultValue = "ASC") String direction,
			@RequestParam(name = "orderBy", defaultValue = "id") String orderBy) {

		Pageable pageable = PageRequest.of(page, size, Direction.valueOf(direction), orderBy);
		Page<Produto> pageCat = produtoService.findByNomeAndIds(nome, ids, pageable);
		return pageCat;
	}

}

