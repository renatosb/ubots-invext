package com.ubots.invext.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ubots.invext.dto.AtendimentoDTO;
import com.ubots.invext.entities.Atendimento;
import com.ubots.invext.entities.Demanda;
import com.ubots.invext.entities.Team;
import com.ubots.invext.enums.DemandaStatusEnum;
import com.ubots.invext.repositories.AtendimentoRepository;
import com.ubots.invext.repositories.DemandaRepository;
import com.ubots.invext.response.AtendimentoCriadoResponse;

@Service
public class AtendimentoService {
	
	@Autowired
	private AtendimentoRepository atendimentoRepository;
	
	@Autowired
	private DemandaRepository demandaRepository;
	
	public AtendimentoCriadoResponse createNewAtendimento(AtendimentoDTO atendimentoDTO) {
		
		Atendimento atendimento = new Atendimento();
		atendimento.setAssunto(atendimentoDTO.assunto());
		atendimento.setDescricao(atendimentoDTO.descricao());
		
		atendimentoRepository.save(atendimento);
		
		Demanda demanda = new Demanda();
		demanda.setAtendimento(atendimento);
		demanda.setStatus(DemandaStatusEnum.PENDENTE);
		demandaRepository.save(demanda);
		
		return new AtendimentoCriadoResponse(atendimento, demanda);
	}
	
	public AtendimentoRepository getAtendimentoRepository() {
		return atendimentoRepository;
	}
}
