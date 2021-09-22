package com.dambroski.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dambroski.domain.Endereco;


@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Integer> {

}