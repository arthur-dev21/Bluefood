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

		if (!validateEmail(cliente.getEmail(), cliente.getId())) {
			throw new ValidatrionException("o email esta duplicado");
		}

		clienteRepository.save(cliente);
	}

	private boolean validateEmail(String email, Integer id) {

		Cliente clienteDB = clienteRepository.findByEmail(email);					 // busca cliente pelo email

		if (clienteDB != null) { 													// se nao encontrar o cliente no bd , entao vc esta criando um novo , pode retornar true
																					
			if (id == null) { 														// se o id que eu estou passando como parametro é null , siguinifica que estou inserindo , mas tem algem como o mesmo email , entao o email esta duplicado
																					
				return false;
			}

			if (!clienteDB.getId().equals(id)) { 									// o id nao esta nulo , vc esta alterando um regirtro pra um email que ja existe entao retorna false
				return false;
			}

		}
		return true;
	}
}
