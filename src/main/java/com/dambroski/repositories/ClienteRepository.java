package com.dambroski.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dambroski.domain.Cliente;


@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

	
	Boolean existsClienteByEmail(String email); 
	
	Cliente findByNomeContainingIgnoreCase(String nome);

}
