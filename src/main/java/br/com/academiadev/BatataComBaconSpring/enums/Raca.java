package br.com.academiadev.BatataComBaconSpring.enums;

public enum Raca {
	
	AVE("Ave"), EQUINO("Equino"), CANINO("Canino"), FELINO("Felino"), OUTRO("Outro");
	
	private String descricao;
	
	private Raca(String descricao) {
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return descricao;
	}
}
