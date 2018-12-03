package br.com.academiadev.BatataComBaconSpring.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class PasswordResetToken extends AbstractEntity<Long> {
	
	private static final int Expiration = 60 * 24;
	
	private String token;
	
	@OneToOne(targetEntity = User.class, fetch = FetchType.EAGER)
	private User usuario;
	
	private LocalDateTime dataExpira = LocalDateTime.now().plusMinutes(Expiration);

	public PasswordResetToken(String token, User usuario) {
		this.token = token;
		this.usuario = usuario;
	}
}
