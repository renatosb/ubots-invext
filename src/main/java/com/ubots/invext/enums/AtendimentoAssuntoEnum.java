package com.ubots.invext.enums;

public enum AtendimentoAssuntoEnum implements ItemValueLabel{
	
	CARTAO("Problemas com cartão"),
	EMPRESTIMO("Contratação de empréstimo"),
	OUTROS("Outros Assuntos");
	
	private String label;
	
	private AtendimentoAssuntoEnum(String assunto) {
		this.label = assunto;
	}

	@Override
	public Object getValue() {
		return name();
	}

	@Override
	public String getLabel() {
		return label;
	}

}
