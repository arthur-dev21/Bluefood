package com.arthurdev.bluefood.application.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.arthurdev.bluefood.domain.cliente.Cliente;
import com.arthurdev.bluefood.domain.cliente.ClienteRepository;
import com.arthurdev.bluefood.domain.restaurante.Restaurante;
import com.arthurdev.bluefood.domain.restaurante.RestauranteRepository;

@Service
public class RestauranteService {

	@Autowired
	private RestauranteRepository restauranteRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private ImageService imageService;
    
	@Transactional                                                                       // caso vc queira fazer mais de uma operaçao no BD é ideal que seja no contexto transacional , pois qulquer erro o BD pode desfazer , ex: atualizar tebela 1,2,3 ,  1 e 2 foi um sucesso mas a 3 deu erro , o BD difaz as ateraçoes na 1 e 2
	public void saveRestaurante(Restaurante restaurante) throws ValidatrionException {

		if (!validateEmail(restaurante.getEmail(), restaurante.getId())) {
			throw new ValidatrionException("o email esta duplicado");
		}
		if (restaurante.getId() != null) {
			Restaurante restauranteDB = restauranteRepository.findById(restaurante.getId()).orElseThrow();   //orElseThrow erro do sistema , erro de progamaçao
			restaurante.setSenha(restauranteDB.getSenha());
		} else {
			restaurante.encryptPassword();
			restaurante=restauranteRepository.save(restaurante);             //de acordo com o gerenciamento de entidades da jpa , o metodo save(), retorna uma instancia de restaurante , entao qualquer mudandança sera refletido no banco de dados
			restaurante.setLogotipoFileName();
			imageService.uploadLogotipo(restaurante.getLogotipoFile(), restaurante.getLogotipo());
			
			
		}
	}

	private boolean validateEmail(String email, Integer id) {
        Cliente clienteDB = clienteRepository.findByEmail(email);
        
        if(clienteDB != null) {
        	return false;
        }
		
		Restaurante restauranteDB  = restauranteRepository.findByEmail(email);					 // busca cliente pelo email

		if (restauranteDB != null) { 													// se nao encontrar o cliente no bd , entao vc esta criando um novo , pode retornar true
																					
			if (id == null) { 														// se o id que eu estou passando como parametro é null , siguinifica que estou inserindo , mas tem algem como o mesmo email , entao o email esta duplicado
																					
				return false;
			}

			if (!restauranteDB.getId().equals(id)) { 									// o id nao esta nulo , vc esta alterando um regirtro pra um email que ja existe entao retorna false
				return false;
			}

		}
		return true;
	}
}
