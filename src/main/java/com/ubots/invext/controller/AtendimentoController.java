package com.ubots.invext.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ubots.invext.dto.AtendimentoDTO;
import com.ubots.invext.entities.Atendimento;
import com.ubots.invext.response.AtendimentoCriadoResponse;
import com.ubots.invext.service.AtendimentoService;

import lombok.extern.slf4j.Slf4j;

@Slf4j(topic = "CONTROLLER_ATENDIMENTOS")
@RestController
@RequestMapping("/atendimentos")
@CrossOrigin
public class AtendimentoController {
	
	@Autowired
	private AtendimentoService atendimentoService;
	
	@PostMapping("/create")
	public ResponseEntity<AtendimentoCriadoResponse> createNewTime(@RequestBody AtendimentoDTO atendimento){
		log.info("Criando novo: " + atendimento);
		return new ResponseEntity<>(atendimentoService.createNewAtendimento(atendimento), HttpStatus.CREATED);
	}
	
	@GetMapping(path = {"/",""})
	public ResponseEntity<List<Atendimento>> findAll(){
		log.info("Buscando todos");
		return new ResponseEntity<>(atendimentoService.getAtendimentoRepository().findAll(), HttpStatus.OK);
	}
}
