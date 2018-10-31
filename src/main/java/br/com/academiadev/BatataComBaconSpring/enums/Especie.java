package br.com.academiadev.BatataComBaconSpring.enums;

public enum Especie {
	
	AVE("Ave"), EQUINO("Equino"), CANINO("Canino"), FELINO("Felino"), OUTRO("Outro");
	
	private String descricao;
	
	private Especie(String descricao) {
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return descricao;
	}
}
