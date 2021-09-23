package com.dambroski.resources;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
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

import com.dambroski.domain.Categoria;
import com.dambroski.domain.dto.CategoriaDTO;
import com.dambroski.services.CategoriaService;



@RestController
@RequestMapping("/categoria")
public class CategoriaResource {

	@Autowired
	private CategoriaService categoriaService;

	@GetMapping("/todas")
	public Page<CategoriaDTO> findByPage(@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "linesPerPage", defaultValue = "2") Integer linesPerPage,
			@RequestParam(value = "orderBy", defaultValue = "nome") String orderBy,
			@RequestParam(value = "direction", defaultValue = "ASC") String direction) {
		return categoriaService.findByPage(page, linesPerPage, orderBy, direction);

	}

	@GetMapping
	public ResponseEntity<List<CategoriaDTO>> findAll() {
		List<CategoriaDTO> catsDTO = categoriaService.findAll();
		return ResponseEntity.ok().body(catsDTO);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Categoria> findById(@PathVariable Integer id) {
		Categoria cat = categoriaService.findById(id);
		return ResponseEntity.ok().body(cat);
	}

	@PostMapping
	public ResponseEntity<Void> insert(@Valid @RequestBody Categoria categoria) {
		Categoria cat = categoriaService.insert(categoria);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(cat.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

	@PutMapping("/{id}")
	public ResponseEntity<Categoria> update(@PathVariable Integer id, @Valid @RequestBody Categoria categoria) {
		categoriaService.update(id, categoria);
		return ResponseEntity.noContent().build();
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		categoriaService.delete(id);
		return ResponseEntity.noContent().build();
	}

}

