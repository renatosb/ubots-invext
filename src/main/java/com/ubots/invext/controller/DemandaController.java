package com.ubots.invext.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ubots.invext.dto.DemandaDTO;
import com.ubots.invext.entities.Demanda;
import com.ubots.invext.service.DemandaService;

import lombok.extern.slf4j.Slf4j;

@Slf4j(topic = "CONTROLLER_DEMANDAS")
@RestController
@RequestMapping("/demandas")
@CrossOrigin
public class DemandaController {
	
	@Autowired
	private DemandaService demandaService;

	@PostMapping("/create")
	public ResponseEntity<Demanda> createNewDemanda(@RequestBody DemandaDTO demanda){
		log.info("Criando novo: " + demanda);
		return new ResponseEntity<>(demandaService.createNovaDemanda(demanda), HttpStatus.CREATED);
	}
	
	@PatchMapping(path = {"/",""})
	public ResponseEntity<Demanda> encontrarAtendenteDisponivel(@RequestBody DemandaDTO demandaDTO) throws Exception{
		log.info("Encontrando atendente disponivel para a demanda: " + demandaDTO);
		return new ResponseEntity<>(demandaService.encontraAtendenteDisponivel(demandaDTO), HttpStatus.OK);
	}
	
	@GetMapping(path= {"/",""})
	public ResponseEntity<List<Demanda>> findAll(){
		log.info("Buscando todos");
		return new ResponseEntity<>(demandaService.getDemandaRepository().findAll(), HttpStatus.OK);
	}
	
}
