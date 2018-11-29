package br.com.academiadev.BatataComBaconSpring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableJpaAuditing
@EnableTransactionManagement
@SpringBootApplication
public class BatataComBaconSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(BatataComBaconSpringApplication.class, args);
	}
}
