package com.ubots.invext.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ubots.invext.dto.AddDemandaTimeDTO;
import com.ubots.invext.dto.AtendenteTeamDTO;
import com.ubots.invext.dto.DemandaDTO;
import com.ubots.invext.entities.Atendimento;
import com.ubots.invext.entities.Demanda;
import com.ubots.invext.entities.Team;
import com.ubots.invext.enums.DemandaStatusEnum;
import com.ubots.invext.repositories.AtendenteRepository;
import com.ubots.invext.repositories.DemandaRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j(topic = "SERVICE_DEMANDAS")
@Service
public class DemandaService {

	@Autowired
	private DemandaRepository demandaRepository;

	@Autowired
	private AtendimentoService atendimentoService;

	@Autowired
	private TeamService teamService;

	@Autowired
	private AtendenteRepository atendenteRepository;

	public Demanda createNovaDemanda(DemandaDTO demandaDTO) {
		Demanda demanda = new Demanda();

		Atendimento atendimento = atendimentoService.getAtendimentoRepository().findById(demandaDTO.atendimentoId())
				.get();

		demanda.setAtendimento(atendimento);
		demanda.setStatus(demandaDTO.status());

		return demandaRepository.save(demanda);
	}

	public Demanda addDemandaTime(AddDemandaTimeDTO addDemandaTimeDTO) throws Exception {

		Demanda demanda = demandaRepository.findById(addDemandaTimeDTO.demandaId()).get();
		Team time = teamService.getTeamRepository().findById(addDemandaTimeDTO.timeId()).get();

		if (demanda.getAtendimento().getAssunto().getValue() != time.getSetor().getValue()) {
			throw new Exception("O assunto " + demanda.getAtendimento().getAssunto().getLabel()
					+ " não pode ser designado ao time " + time.getSetor().getLabel());
		}

		demanda.setTime(time);

		return demandaRepository.save(demanda);
	}

	public Demanda encontraAtendenteDisponivel(DemandaDTO demandaDTO) throws Exception {

		Demanda demanda = demandaRepository.findById(demandaDTO.id()).get();

		if (!demanda.getStatus().equals(DemandaStatusEnum.PENDENTE)) {
			throw new Exception("Demanda nao está pendente, ja foi atribuida.");

		}

		List<Team> listaTimes = teamService.findTimesByDemandaSetor(demanda);

		if (listaTimes.isEmpty()) {
			throw new Exception(
					"Nenhum time de " + demanda.getAtendimento().getAssunto().getLabel() + " foi encontrado!");
		}

		Stream<Team> timesFiltrados = listaTimes.stream().filter(t -> !t.getAtendentes().isEmpty());

		List<AtendenteTeamDTO> atendenteDemandaDTOs = new ArrayList<>();

		// percorre as equipes procurando um atendente disponivel
		// adiciona a lista
		timesFiltrados.forEach(t -> {
			t.getAtendentes().stream().forEach(at -> {
				if (at.getAtendimentos().size() < 3) {
					AtendenteTeamDTO atDTO = new AtendenteTeamDTO(at, t);
					atendenteDemandaDTOs.add(atDTO);
					return;
				}
			});
		});

		if (atendenteDemandaDTOs.isEmpty()) {
			throw new Exception("Nenum atendente disponível foi encontrado, a demanda continua pendente!");
		}

		// retorna o primeiro atendente do time disponivel
		AtendenteTeamDTO atDTO = atendenteDemandaDTOs.stream().findFirst().get();

		if(demanda.getTime().equals(null)) {
			demanda.setTime(atDTO.time());

		}
		demanda.setStatus(DemandaStatusEnum.EM_EXECUCAO);
		demandaRepository.save(demanda);

		// atribui demanda e salva atendente
		atDTO.atendente().addAtendimento(demanda.getAtendimento());
		atendenteRepository.save(atDTO.atendente());

		return demanda;
	}

	public DemandaRepository getDemandaRepository() {
		return demandaRepository;
	}
}
