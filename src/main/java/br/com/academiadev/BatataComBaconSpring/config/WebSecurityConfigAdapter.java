package br.com.academiadev.BatataComBaconSpring.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import br.com.academiadev.BatataComBaconSpring.dto.UserLoginDTO;
import br.com.academiadev.BatataComBaconSpring.model.User;
import br.com.academiadev.BatataComBaconSpring.repository.UserRepository;

@Configuration
@EnableWebSecurity
public class WebSecurityConfigAdapter extends WebSecurityConfigurerAdapter {

	@Bean
	public AuthenticationManager customAuthenticationManager() throws Exception {
		return authenticationManagerBean();
	}

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Autowired
	public void authenticationManager(AuthenticationManagerBuilder builder, UserRepository userRepository)
			throws Exception {
		if (userRepository.count() == 0) {
			User user = new User();
			user.setNome("Administrador do Sistema");
			user.setEmail("admin@backendcombacon.com.br");
			user.setSenha(passwordEncoder().encode("AdminComBacon"));
			userRepository.save(user);
		}

		builder.userDetailsService(email -> new UserLoginDTO(userRepository.findByEmail(email)))
				.passwordEncoder(passwordEncoder());
	}
}
