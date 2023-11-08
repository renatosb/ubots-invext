package com.ubots.invext.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ubots.invext.dto.AtendenteDTO;
import com.ubots.invext.dto.AtendenteDemandaDTO;
import com.ubots.invext.entities.Atendente;
import com.ubots.invext.entities.Demanda;
import com.ubots.invext.enums.DemandaStatusEnum;
import com.ubots.invext.repositories.AtendenteRepository;
import com.ubots.invext.repositories.DemandaRepository;

@Service
public class AtendenteService {
	
	@Autowired
	private AtendenteRepository atendenteRepository;
	
	@Autowired
	private DemandaRepository demandaRepository;
	
	public Atendente criaNovoAtendente(AtendenteDTO atendenteDTO) {
		Atendente atendente = new Atendente();
		atendente.setNome(atendenteDTO.nome());
		
		return atendenteRepository.save(atendente);
	}
	
	public Atendente atribuirDemanda(AtendenteDemandaDTO atendenteDemandaDTO) throws Exception {
		Atendente atendente = atendenteRepository.findById(atendenteDemandaDTO.atendenteId()).get();
		
		if(atendente.getAtendimentos().size() > 2) {
			throw new Exception("O Atendente ja esta cheio de demandas");
		}
		
		Demanda demanda = demandaRepository.findById(atendenteDemandaDTO.demandaId()).get();
		
		if(!demanda.getStatus().equals(DemandaStatusEnum.PENDENTE)) {
			throw new Exception("Demanda nao está pendente, ja foi atribuida.");
	
		}
		
		demanda.setStatus(DemandaStatusEnum.EM_EXECUCAO);
		atendente.addAtendimento(demanda.getAtendimento());
		demandaRepository.save(demanda);
		
		return atendenteRepository.save(atendente);
	}
	
	public AtendenteRepository getAtendenteRepository() {
		return atendenteRepository;
	}
}
