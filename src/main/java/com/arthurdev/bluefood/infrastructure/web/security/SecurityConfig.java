package com.arthurdev.bluefood.infrastructure.web.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;




@Configuration
public class SecurityConfig  extends WebSecurityConfigurerAdapter{
	
	@Bean                                                                                      //tem a resposbilidade de criar uma instancia de  AuthenticationSuccessHandlerImpl 
	public AuthenticationSuccessHandler authenticationSuccessHandler() {
		return  new AuthenticationSuccessHandlerImpl();
	}
   
	@Override
	protected void configure(HttpSecurity http) throws Exception {    																	//esse metodo define a  authenticaçao e autorizaçao , quem pode logar no sistema e quai paginas pode ser acessadas respectivamente
		
		http.csrf().disable()
		.authorizeRequests()
		.antMatchers("/images/**", "/css/**", "/js/**", "/public/**", "/sbpay/**").permitAll()
		.antMatchers("/cliente/**").hasRole(Role.CLIENTE.toString())
		.antMatchers("/restaurante/**").hasRole(Role.RESTAURANTE.toString())
		.anyRequest().authenticated()
		.and()
		.formLogin()
			.loginPage("/login")
			.failureUrl("/login-error")
			.successHandler(authenticationSuccessHandler())
			.permitAll()
		.and()
			.logout()
			.logoutUrl("/logout")
			.permitAll();
}
}
