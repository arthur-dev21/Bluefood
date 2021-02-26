package com.arthurdev.bluefood.application.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.arthurdev.bluefood.domain.cliente.Cliente;
import com.arthurdev.bluefood.domain.cliente.ClienteRepository;
import com.arthurdev.bluefood.domain.restaurante.Restaurante;
import com.arthurdev.bluefood.domain.restaurante.RestauranteRepository;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private RestauranteRepository restauranteRepository;
    
	@Transactional                                                                    // caso vc queira fazer mais de uma operaçao no BD é ideal que seja no contexto transacional , pois qulquer erro o BD pode desfazer , ex: atualizar tebela 1,2,3 ,  1 e 2 foi um sucesso mas a 3 deu erro , o BD difaz as ateraçoes na 1 e 2
	public void saveCliente(Cliente cliente) throws ValidatrionException {

		if (!validateEmail(cliente.getEmail(), cliente.getId())) {
			throw new ValidatrionException("o email esta duplicado");
		}
		if (cliente.getId() != null) {
            Cliente clienteDB = clienteRepository.findById(cliente.getId()).orElseThrow();   //orElseThrow erro do sistema , erro de progamaçao
            cliente.setSenha(clienteDB.getSenha());
		} else {
            cliente.encryptPassword();
		}
		clienteRepository.save(cliente);
	}

	private boolean validateEmail(String email, Integer id) {
        
		Restaurante restauranteDB = restauranteRepository.findByEmail(email);
		
		if(restauranteDB != null) {
			return false;
		}
		
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
