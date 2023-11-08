package com.ubots.invext.entities;

import java.io.Serializable;
import java.util.List;

import jakarta.annotation.Nonnull;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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

@Entity(name="atendentes")
@Table(name="atendentes")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of="id")
public class Atendente implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ID_ATENDENTE", unique = true, nullable= false)
	private Long id;
	
	@Column(name="NO_ATENDENTE")
	@Nonnull
	private String nome;
	
	@OneToMany
	@JoinColumn(name="atendimento_id")
	private List<Atendimento> atendimentos;
	
	public void addAtendimento(Atendimento atendimento) {
		this.atendimentos.add(atendimento);
	}
}