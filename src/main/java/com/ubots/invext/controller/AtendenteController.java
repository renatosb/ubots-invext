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

import com.ubots.invext.dto.AtendenteDTO;
import com.ubots.invext.dto.AtendenteDemandaDTO;
import com.ubots.invext.dto.DemandaDTO;
import com.ubots.invext.entities.Atendente;
import com.ubots.invext.entities.Demanda;
import com.ubots.invext.service.AtendenteService;

import lombok.extern.slf4j.Slf4j;

@Slf4j(topic = "CONTROLLER_ATENDENTES")
@RestController
@RequestMapping("/atendentes")
@CrossOrigin
public class AtendenteController {
	
	@Autowired
	private AtendenteService atendenteService;
	
	@PostMapping("/create")
	public ResponseEntity<Atendente> createNewAtendente(@RequestBody AtendenteDTO atendente){
		log.info("Criando novo: " + atendente);
		return new ResponseEntity<>(atendenteService.criaNovoAtendente(atendente), HttpStatus.CREATED);
	}
	
	@PatchMapping(path = {"/",""})
	public ResponseEntity<Atendente> atribuirDemandaAtendente(@RequestBody AtendenteDemandaDTO atendenteDemandaDTO) throws Exception{
		log.info("Atribuindo demanda ao atendente: " + atendenteDemandaDTO);
		return new ResponseEntity<>(atendenteService.atribuirDemanda(atendenteDemandaDTO), HttpStatus.OK);
	}
	
	@GetMapping(path = {"/",""})
	public ResponseEntity<List<Atendente>> findAll(){
		log.info("Buscando todos");
		return new ResponseEntity<>(atendenteService.getAtendenteRepository().findAll(), HttpStatus.OK);
	}
}
