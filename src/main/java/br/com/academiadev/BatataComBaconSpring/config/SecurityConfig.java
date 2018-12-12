package br.com.academiadev.BatataComBaconSpring.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;

import br.com.academiadev.BatataComBaconSpring.exception.UserNaoEncontradoException;
import br.com.academiadev.BatataComBaconSpring.model.Usuario;
import br.com.academiadev.BatataComBaconSpring.repository.UserRepository;

@Configuration
@EnableAuthorizationServer
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	@Override
	protected AuthenticationManager authenticationManager() throws Exception {
		return super.authenticationManager();
	}

	@Autowired
	public void configure(AuthenticationManagerBuilder auth, UserRepository repository) throws Exception {
		Usuario user = new Usuario();
		user.setNome("Administrador do Sistema");
		user.setEmail("admin@batatacombacon.com.br");
		user.setSenha(new BCryptPasswordEncoder().encode("AdminComBacon"));
		user.setRole("ROLE_ADMIN");

		if (repository.count() == 0) {
			repository.saveAndFlush(user);
		}

		auth.userDetailsService(email -> {
			return repository.findByEmail(email)
					.orElseThrow(() -> new UserNaoEncontradoException("Usuario nao encontrado"));
		}).passwordEncoder(passwordEncoder());

	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers(HttpMethod.GET, //
				"/", //
				"/webjars/**", //
				"/*.html", //
				"/favicon.ico", //
				"/**/*.html", //
				"/v2/api-docs", //
				"/configuration/ui", //
				"/swagger-resources/**", //
				"/configuration/**", //
				"/swagger-ui.html", //
				"/webjars/**", //
				"/**/*.css", //
				"/**/*.js"//
		);
	}

	/*
	 * Estavamos com problemas ao usar a implementação CORS do Spring
	 * Ele aparentemente autenticava os endpoins da aplicação mas
	 * não os do oauth, portanto implementamos uma classe de FiltroCors
	 */
//	@Bean
//	CorsConfigurationSource corsConfigurationSource() {
//		CorsConfiguration configuration = new CorsConfiguration();
//		configuration.applyPermitDefaultValues();
//		configuration.addAllowedMethod(HttpMethod.PUT);
//		configuration.addAllowedMethod(HttpMethod.DELETE);
//		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//		source.registerCorsConfiguration("/**", configuration);
//		return source;
//	}
}
