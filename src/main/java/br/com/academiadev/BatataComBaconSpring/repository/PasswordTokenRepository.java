package br.com.academiadev.BatataComBaconSpring.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.academiadev.BatataComBaconSpring.model.PasswordResetToken;

public interface PasswordTokenRepository extends JpaRepository<PasswordResetToken, Long>{
	
	PasswordResetToken findByToken(String token);

}
