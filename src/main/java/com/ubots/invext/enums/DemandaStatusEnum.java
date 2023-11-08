package com.ubots.invext.enums;

public enum DemandaStatusEnum implements ItemValueLabel {
	
	CONCLUIDO("Concluído"),
	PENDENTE("Pendente"),
	EM_EXECUCAO("Em Execução");
	
	private String label;
	
	private DemandaStatusEnum(String status) {
		this.label = status;
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
