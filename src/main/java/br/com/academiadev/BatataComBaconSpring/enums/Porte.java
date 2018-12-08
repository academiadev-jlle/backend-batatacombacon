package br.com.academiadev.BatataComBaconSpring.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Porte implements EnumInterface {
	PEQUENO("Pequeno"), MEDIO("Médio"), GRANDE("Grande");
	
	private String descricao;

}
