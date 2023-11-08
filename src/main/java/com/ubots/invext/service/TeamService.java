package com.ubots.invext.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ubots.invext.dto.AddAtendenteTimeDTO;
import com.ubots.invext.dto.TeamDTO;
import com.ubots.invext.entities.Atendente;
import com.ubots.invext.entities.Demanda;
import com.ubots.invext.entities.Team;
import com.ubots.invext.enums.TimeSetorEnum;
import com.ubots.invext.repositories.TeamRepository;

@Service
public class TeamService {

	@Autowired
	private TeamRepository teamRepository;

	@Autowired
	private AtendenteService atendenteService;

	public Team criaNovoTime(TeamDTO teamDTO) {
		Team time = new Team();
		time.setSetor(teamDTO.setor());

		return teamRepository.save(time);
	}

	public Team addAtendente(AddAtendenteTimeDTO addAtendenteDTO) {
		Team time = teamRepository.findById(addAtendenteDTO.teamId()).get();
		Atendente atendente = atendenteService.getAtendenteRepository().findById(addAtendenteDTO.atendenteID()).get();

		time.addAtendente(atendente);

		return teamRepository.save(time);
	}

	public List<Team> findTimesByDemandaSetor(Demanda demanda) {

		switch (demanda.getAtendimento().getAssunto()) {
		case CARTAO:
			return teamRepository.findBySetor(TimeSetorEnum.CARTAO);
		case EMPRESTIMO:
			return teamRepository.findBySetor(TimeSetorEnum.EMPRESTIMO);
		case OUTROS:
			return teamRepository.findBySetor(TimeSetorEnum.OUTROS);
		default:
			return null;
		}
	}

	public TeamRepository getTeamRepository() {
		return teamRepository;
	}
}
