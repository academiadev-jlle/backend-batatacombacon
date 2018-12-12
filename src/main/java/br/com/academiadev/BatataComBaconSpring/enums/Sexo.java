package br.com.academiadev.BatataComBaconSpring.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Sexo implements EnumInterface {
	MACHO("Macho"), FEMEA("Fêmea");

	private String descricao;

}
