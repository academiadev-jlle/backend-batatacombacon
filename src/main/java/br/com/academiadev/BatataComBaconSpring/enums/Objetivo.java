package br.com.academiadev.BatataComBaconSpring.enums;

public enum Objetivo {

	PROCURADO("Procurado"), ENCONTRADO("Encontrado"), DOACAO("Doação"), NENHUM("Nenhum");

	private String descricao;

	Objetivo(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}
}