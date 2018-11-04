package br.com.academiadev.BatataComBaconSpring.enums;

public enum Objetivo {

	PERDIDO("Perdido"), ENCONTRADO("Encontrado"), DOACAO("Doação");

	private String descricao;

	Objetivo(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}
}