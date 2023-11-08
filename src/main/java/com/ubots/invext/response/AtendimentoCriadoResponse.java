package com.ubots.invext.response;

import com.ubots.invext.entities.Atendimento;
import com.ubots.invext.entities.Demanda;

public record AtendimentoCriadoResponse(Atendimento atendimento, Demanda demanda) {

}
