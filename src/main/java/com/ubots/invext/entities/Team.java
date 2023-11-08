package com.ubots.invext.entities;

import java.io.Serializable;
import java.util.List;

import com.ubots.invext.enums.TimeSetorEnum;

import jakarta.annotation.Nonnull;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name="times")
@Table(name="times")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of="id")
public class Team implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ID_TIME", unique = true, nullable= false)
	private Long id;
	
	@Nonnull
	@Enumerated(EnumType.STRING)
	@Column(name="EN_TIME_SETOR")
	private TimeSetorEnum setor;
	
	@OneToMany
	@JoinColumn(name="atendente_id")
	private List<Atendente> atendentes;
	
	public void addAtendente(Atendente atendente) {
		this.atendentes.add(atendente);
	}
}