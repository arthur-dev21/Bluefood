package com.arthurdev.bluefood.infrastructure.web.security;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.CollectionUtils;

import com.arthurdev.bluefood.domain.cliente.Cliente;
import com.arthurdev.bluefood.domain.restaurante.Restaurante;
import com.arthurdev.bluefood.domain.usuario.Usuario;

@SuppressWarnings("serial")
public class LoggedUser implements UserDetails {                                     //classe para recuperar o usuario logado
    
	private Usuario usuario;
	private Role role;
	private Collection<? extends GrantedAuthority> roles;                            //esse roles que seja GrandetAutority ou que extendam de GrandetAutority
	
	public LoggedUser(Usuario usuario) {                                             //construtoru para obrigar passar o usuario
		this.usuario = usuario;
		
		Role role;
		
		if (usuario instanceof Cliente) {
			role = Role.CLIENTE;
		
		} else if (usuario instanceof Restaurante) {
			role = Role.RESTAURANTE;
		
		} else {
			throw new IllegalStateException("O tipo de usuário não é válido");
		}
		
		this.role = role;
		this.roles =List.of(new SimpleGrantedAuthority("Role_" + role ));
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {                 //meche com os perfis de acesso , os roles 
		
		return roles;
	}

	@Override
	public String getPassword() {                                                     // retorna a senha
		
		return usuario.getSenha();
	}

	@Override 
	public String getUsername() {														//o username do usuario
	
		return usuario.getEmail();
	}

	@Override
	public boolean isAccountNonExpired() {  											//a conta do usuario expirou
		 
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {                                              // a conta nao etsa bloqueada
		
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {                                         // se as credenciasi estao expirdas
		
		return true;
	}

	@Override
	public boolean isEnabled() {                                                       //se a conta esta habilitada
		
		return true;
	}
	public Role getRole() {
		return role;
	}
	
	public Usuario getUsuario() {
		return usuario;
	}

}
