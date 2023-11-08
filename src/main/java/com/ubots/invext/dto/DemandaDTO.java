package com.ubots.invext.dto;

import com.ubots.invext.enums.DemandaStatusEnum;

public record DemandaDTO(Long id, Long atendimentoId, DemandaStatusEnum status) {

}
