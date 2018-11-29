package br.com.academiadev.BatataComBaconSpring.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import lombok.Data;

@Data
@MappedSuperclass
public class AbstractEntity<ID> {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected ID id;

}
