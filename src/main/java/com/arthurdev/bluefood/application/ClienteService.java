package com.arthurdev.bluefood.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.arthurdev.bluefood.domain.cliente.Cliente;
import com.arthurdev.bluefood.domain.cliente.ClienteRepository;

@Service
public class ClienteService {
    
	@Autowired
	private ClienteRepository clienteRepository;
	
	
	
	public void saveCliente(Cliente cliente) throws ValidatrionException {
		
		if(! validateEmail(cliente.getEmail(), cliente.getId())) {
			throw new ValidatrionException("o email esta duplicado");
		}
		
		clienteRepository.save(cliente);
	}
	
	private boolean validateEmail(String email , Integer id) {
		return false;
	}
}
