package br.com.academiadev.BatataComBaconSpring.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Objetivo {

	PROCURADO("Procurado"), ENCONTRADO("Encontrado"), DOACAO("Doação"), NENHUM("Nenhum");

	private String descricao;
}