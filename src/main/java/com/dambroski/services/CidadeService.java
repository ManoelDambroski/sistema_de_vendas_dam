package com.dambroski.services;

import java.io.Serializable;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dambroski.domain.Cidade;
import com.dambroski.repositories.CidadeRepository;
import com.dambroski.services.exceptions.EntitieNotFoundException;



@Service
public class CidadeService implements Serializable {
	private static final long serialVersionUID = 1L;

	@Autowired
	private CidadeRepository cidadeRepository;

	public Cidade findById(Integer id) {
		Optional<Cidade> cidade = cidadeRepository.findById(id);
		return cidade.orElseThrow(() -> new EntitieNotFoundException("Cidade não encontrada id: " + id));
	}

}
