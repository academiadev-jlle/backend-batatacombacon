package br.com.academiadev.BatataComBaconSpring.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@NoArgsConstructor
public class Localizacao extends AbstractEntity<Long> {

	@OneToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "pet_id", nullable = false)
	private Pet pet;

	private String cep;

	private String rua;

	private String numero;

	private String complemento;

	private String bairro;

	private String cidade;

	private String uf;

	private String referencia;

}
