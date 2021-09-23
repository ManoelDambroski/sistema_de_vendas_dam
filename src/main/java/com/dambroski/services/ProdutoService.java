package com.dambroski.services;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.dambroski.domain.Produto;
import com.dambroski.repositories.ProdutoRepository;
import com.dambroski.services.exceptions.EntitieNotFoundException;



@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository produtoRepository;

	public Produto findById(Integer id) {
		Optional<Produto> prod = produtoRepository.findById(id);
		return prod.orElseThrow(() -> new EntitieNotFoundException("Produto não encontrado"));
	}

	public Page<Produto> findByNomeAndIds(String nome, String categoriaIds, Pageable pageable) {
		nome = nomeDeconde(nome);
		List<Integer> ids = listToInteger(categoriaIds);
		Page<Produto> produtos = produtoRepository.findDistinctByNomeContainingIgnoreCaseAndCategoriasIdIn(nome, ids,
				pageable);
		return produtos;

	}

	private static List<Integer> listToInteger(String param) {
		return Arrays.asList(param.split(",")).stream().map(x -> Integer.parseInt(x)).collect(Collectors.toList());
	}

	private static String nomeDeconde(String nome) {
		try {
			nome = URLDecoder.decode(nome, "UTF-8");
			return nome;
		} catch (UnsupportedEncodingException e) {
			return "";
		}
	}

}
