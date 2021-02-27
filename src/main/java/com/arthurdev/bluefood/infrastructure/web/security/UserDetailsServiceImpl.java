package com.arthurdev.bluefood.infrastructure.web.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.arthurdev.bluefood.domain.cliente.ClienteRepository;
import com.arthurdev.bluefood.domain.restaurante.RestauranteRepository;
import com.arthurdev.bluefood.domain.usuario.Usuario;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{                            //para carrgar o usuario no banco de dados
    
	@Autowired
	private ClienteRepository clienteRespository;
	
	@Autowired
	private RestauranteRepository restauranteRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {   //(String username)o que o usuario digitou
		
		Usuario usuario = clienteRespository.findByEmail(username);
		
		if (usuario == null) {
			usuario = restauranteRepository.findByEmail(username);
			
			if (usuario == null) {
				throw new UsernameNotFoundException(username);
			}
		}
		
		return new LoggedUser(usuario);
	}
}
