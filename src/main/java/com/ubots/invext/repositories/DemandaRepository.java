package com.ubots.invext.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ubots.invext.entities.Demanda;


public interface DemandaRepository extends JpaRepository<Demanda, Long>{
	
}
