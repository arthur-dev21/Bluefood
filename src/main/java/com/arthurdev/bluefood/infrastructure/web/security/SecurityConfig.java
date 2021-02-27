package com.arthurdev.bluefood.infrastructure.web.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SecurityConfig  extends WebSecurityConfigurerAdapter{
   
	@Override
	protected void configure(HttpSecurity http) throws Exception {    //esse metodo define a  authenticaçao e autorizaçao , quem pode logar no sistema e quai paginas pode ser acessadas respectivamente
		
		http.csrf().disable() 										                                                                     //desabilitar um recurso ???
		    .authorizeRequests()                                                                                                         // permitir o que sera acessado
		    .antMatchers("/images/**" , "/css/**" , "/js/**" , "/public/" , "/sbpay/**").permitAll()								     // padroes de acesso , permite que o usuario acesse livremente paginas ou arquiivos com essas terminaçoes
		    .antMatchers("/cliente/**").hasRole(Role.CLIENTE.toString())                                                                               // QULQUER PAGINA QUE SENHA ESSE PADRAO PODERA SER ACESSADA
		    .antMatchers("/restaurante/**").hasRole(Role.RESTAURANTE.toString())
		    .anyRequest().authenticated()                                                                                                // QULQUER PAGINA QUE TENHA ESSA PADROO PRECISA DE AUTHENTICAÇAO
		    .and()                            
		    .formLogin()                                                                                                                 //define que os pdroes precisa de um formulario
		         .loginPage("/login")                                                                                                    // pagina de login
		         .failureUrl("/login-error") 																							 // pagina de erro
		        // .successHandler(null)																									 // objeto para ser chamdo quando o login for um sucesso
		         .permitAll()                                                                                                            // permite o acesso
		    .and()     
		         .logout().logoutUrl("/logout")                                                                                          //define o comando de terminar um sessao
		         .permitAll();                               
		
	}
}
