package br.com.academiadev.BatataComBaconSpring.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
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
	
	@OneToOne
	private Usuario usuario;
	
	private LocalDateTime dataExpira = LocalDateTime.now().plusMinutes(Expiration);

	public PasswordResetToken(String token, Usuario usuario) {
		this.token = token;
		this.usuario = usuario;
	}
}
