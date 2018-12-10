package br.com.academiadev.BatataComBaconSpring.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

@Configuration
@EnableResourceServer
public class ResourceConfig extends ResourceServerConfigurerAdapter {

	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.cors().and().authorizeRequests() //
		.antMatchers(HttpMethod.POST, "/user", "/user/resetPassword", "/user/changePassword").permitAll()
        .antMatchers("/user/updatePassword*", "/user/savePassword*", "/updatePassword*").hasAuthority("CHANGE_PASSWORD_PRIVILEGE")
		.antMatchers(HttpMethod.OPTIONS, "/oauth/token").permitAll()
		.antMatchers(HttpMethod.GET, "/pet/**", "/especies", "/objetivos", "/porte", "/sexo", "/images/*", "/images").permitAll()
		.antMatchers(HttpMethod.GET, "/user/", "/user").hasRole("ADMIN")
		.anyRequest().authenticated();
	}
}
