package com.dambroski.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.dambroski.domain.Categoria;
import com.dambroski.domain.dto.CategoriaDTO;
import com.dambroski.repositories.CategoriaRepository;
import com.dambroski.services.exceptions.DataIntegrityException;
import com.dambroski.services.exceptions.EntitieNotFoundException;



@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository categoriaRepository;

	public List<CategoriaDTO> findAll() {
		List<Categoria> cats = categoriaRepository.findAll();
		List<CategoriaDTO> catsDTO = listToDTO(cats);
		return catsDTO;
	}

	public List<Categoria> findAllByIds(List<Integer> ids) {
		List<Categoria> list = categoriaRepository.findAllById(ids);
		return list;
	}

	public Page<CategoriaDTO> findByPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		Page<Categoria> pageCat = categoriaRepository.findAll(pageRequest);
		Page<CategoriaDTO> pageDTO = pageCat.map(obj -> new CategoriaDTO(obj));
		return pageDTO;
	}

	public Categoria findById(Integer id) {
		Optional<Categoria> cat = categoriaRepository.findById(id);
		return cat.orElseThrow(() -> new EntitieNotFoundException("Categoria não encontrada"));
	}

	public Categoria insert(Categoria categoria) {

		return categoriaRepository.save(categoria);
	}

	public Categoria update(Integer id, Categoria categoria) {
		Categoria cat = findById(id);
		cat.setNome(categoria.getNome());
		cat = categoriaRepository.save(cat);
		return cat;
	}

	public void delete(Integer id) {
		findById(id);
		try {
			categoriaRepository.deleteById(id);

		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Categoria vinculada a produto não pode ser deletada");
		}
	}

	private List<CategoriaDTO> listToDTO(List<Categoria> cats) {
		List<CategoriaDTO> catsDTO = new ArrayList<>();
		for (Categoria x : cats) {
			CategoriaDTO catDTO = new CategoriaDTO(x);
			catsDTO.add(catDTO);
		}
		return catsDTO;
	}

}

