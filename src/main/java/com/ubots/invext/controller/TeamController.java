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

import com.ubots.invext.dto.AddAtendenteTimeDTO;
import com.ubots.invext.dto.TeamDTO;
import com.ubots.invext.entities.Team;
import com.ubots.invext.service.TeamService;

import lombok.extern.slf4j.Slf4j;

@Slf4j(topic = "CONTROLLER_TEAMS")
@RestController
@RequestMapping("/times")
@CrossOrigin
public class TeamController {
	
	@Autowired
	private TeamService teamService;
	
	@PostMapping("/create")
	public ResponseEntity<Team> createNewTime(@RequestBody TeamDTO time){
		log.info("Criando novo: " + time);
		return new ResponseEntity<>(teamService.criaNovoTime(time), HttpStatus.CREATED);
	}
	
	@PatchMapping(path = {"/",""})
	public ResponseEntity<Team> addAtendenteAoTeam(@RequestBody AddAtendenteTimeDTO addAtendenteDTO){
		log.info("Adicionando atendente ao time: " + addAtendenteDTO);
		return new ResponseEntity<>(teamService.addAtendente(addAtendenteDTO), HttpStatus.OK);
	}
	
	@GetMapping(path = {"/",""})
	public ResponseEntity<List<Team>> findAll(){
		log.info("Buscando todos");
		return new ResponseEntity<>(teamService.getTeamRepository().findAll(), HttpStatus.OK);
	}
}
