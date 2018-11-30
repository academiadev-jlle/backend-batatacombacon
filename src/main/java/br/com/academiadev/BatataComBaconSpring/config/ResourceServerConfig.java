package br.com.academiadev.BatataComBaconSpring.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {
	
	@Override
	public void configure(HttpSecurity http) throws Exception{
		http.authorizeRequests().antMatchers(HttpMethod.GET, "/pet/**", "/especies", "/objetivos", "/porte", "/sexo", "/images/*").permitAll()
		.and().authorizeRequests().antMatchers(HttpMethod.POST, "/user").permitAll()
		.and().authorizeRequests()
        .antMatchers("/v2/api-docs", "/swagger-resources/configuration/ui", "/swagger-resources", "/swagger-resources/configuration/security", "/swagger-ui.html", "/webjars/**").permitAll()
        .and().authorizeRequests().anyRequest().authenticated();
	}
}
