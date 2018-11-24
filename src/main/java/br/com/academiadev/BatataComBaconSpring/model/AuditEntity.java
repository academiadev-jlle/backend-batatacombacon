package br.com.academiadev.BatataComBaconSpring.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@MappedSuperclass
public class AuditEntity<ID> extends AbstractEntity<ID>{
	
	@CreatedDate
	@Column(name = "created_at", updatable = false)
	protected LocalDateTime createdAt;
	
	@LastModifiedDate
	@Column(name = "updated_at", updatable = true)
	protected LocalDateTime updatedAt;

}
