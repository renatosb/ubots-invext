package com.ubots.invext.entities;

import java.io.Serializable;

import com.ubots.invext.enums.AtendimentoAssuntoEnum;

import io.micrometer.common.lang.NonNull;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name="atendimentos")
@Table(name="atendimentos")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of="id")
public class Atendimento implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ID_ATENDIMENTO", unique = true, nullable= false)
	private Long id;
	
	@NonNull
	@Enumerated(EnumType.STRING)
	@Column(name="EN_ATENDIMENTO_ASSUNTO")
	private AtendimentoAssuntoEnum assunto;
	
	@NonNull
	@Column(name = "TX_DESCRICAO", length = 4000)
	private String descricao;
}
