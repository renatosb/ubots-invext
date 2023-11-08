package com.ubots.invext.entities;

import java.io.Serializable;

import com.ubots.invext.enums.DemandaStatusEnum;

import io.micrometer.common.lang.NonNull;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name="demandas")
@Table(name="demandas")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of="id")
public class Demanda implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ID_DEMANDA", unique = true, nullable= false)
	private Long id;
	
	@OneToOne
	@JoinColumn(name="team_id")
	private Team time;
	
	@OneToOne
	@JoinColumn(name="atendimento_id")
	private Atendimento atendimento;
	
	@NonNull
	@Enumerated(EnumType.STRING)
	@Column(name="EN_ATENDIMENTO_STATUS")
	private DemandaStatusEnum status;
}
