package br.com.academiadev.BatataComBaconSpring.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Especie implements EnumInterface{
	
	AVE("Ave"), EQUINO("Equino"), CANINO("Canino"), FELINO("Felino"), OUTRO("Outro");
	
	private String descricao;
}
