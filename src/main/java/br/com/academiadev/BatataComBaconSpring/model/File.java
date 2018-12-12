package br.com.academiadev.BatataComBaconSpring.model;

import javax.persistence.Entity;
import javax.persistence.Lob;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@Entity
public class File extends AbstractEntity<Long> {
	
	private String nome;
	
	private String mimetype;
	
	@Lob
	private byte[] file;

}
