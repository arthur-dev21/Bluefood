package com.arthurdev.bluefood.infrastructure.web.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.arthurdev.bluefood.util.SecurityUtils;

public class AuthenticationSuccessHandlerImpl implements AuthenticationSuccessHandler {                         //classe sera chamada pelo spring data quando a authenticaçao for correta e redireciona-lo para sua respectiva pagina 

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,               //requeisaçao do usuario, authntication recebe o role para saber qul o usuario e redireciona-lo
			Authentication authentication) throws IOException, ServletException {
		
		Role role = SecurityUtils.loggedUser().getRole();
		
		if (role == Role.CLIENTE) {
			response.sendRedirect("cliente/home");
		
		} else if (role == Role.RESTAURANTE) {
			response.sendRedirect("restaurante/home");
		
		} else {
			throw new IllegalStateException("Erro na autenticação");
		}
	}
}