package com.ubots.invext.dto;

import com.ubots.invext.enums.AtendimentoAssuntoEnum;

public record AtendimentoDTO(Long id, AtendimentoAssuntoEnum assunto, String descricao) {

}
