package com.dambroski.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dambroski.domain.Produto;



@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Integer> {

	
	Page<Produto> findDistinctByNomeContainingIgnoreCaseAndCategoriasIdIn(String nome, List<Integer> categoriasId, Pageable pageable);
}