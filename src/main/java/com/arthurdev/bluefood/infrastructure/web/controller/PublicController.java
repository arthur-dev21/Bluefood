package com.arthurdev.bluefood.infrastructure.web.controller;



import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.arthurdev.bluefood.application.service.ClienteService;
import com.arthurdev.bluefood.application.service.RestauranteService;
import com.arthurdev.bluefood.application.service.ValidatrionException;
import com.arthurdev.bluefood.domain.cliente.Cliente;

import com.arthurdev.bluefood.domain.restaurante.CategoriaRestauranteRepository;
import com.arthurdev.bluefood.domain.restaurante.Restaurante;


@Controller
@RequestMapping("/public")
public class PublicController {

	@Autowired
	private ClienteService clienteService;
	
	@Autowired
    private RestauranteService restauranteService;
	
	@Autowired
	private CategoriaRestauranteRepository categoriaRestauranteRepository;
	
	
  
	@GetMapping("/cliente/new")
	public String newCliente(Model model) {
		model.addAttribute("cliente", new Cliente());
		ControllerHelper.setEditMode(model, false);
		return "cliente-cadastro";
	}

	@PostMapping("/cliente/save")
	public String saveCliente(@ModelAttribute("cliente") @Valid Cliente cliente,  Errors errors, Model model) {

		if (!errors.hasErrors()) {
			try {
				clienteService.saveCliente(cliente);
				model.addAttribute("msg", "cliente gravado com sucesso");
			} catch (ValidatrionException e) {
				errors.rejectValue("email", null, e.getMessage());
			}
		}
		
		ControllerHelper.setEditMode(model, false);
		return "cliente-cadastro";
	}
	
	//--------------------------------restaurante----------------------------------------------
	
	@GetMapping("/restaurante/new")
	public String newRestaurante(Model model) {
		model.addAttribute("restaurante", new Restaurante());
		ControllerHelper.setEditMode(model, false);
		ControllerHelper.addCategoriaRequest(categoriaRestauranteRepository, model);
		return "restaurante-cadastro";
	}
	
	@PostMapping("/restaurante/save")
	public String saveRestaurante(@ModelAttribute("restaurante") @Valid Restaurante restaurante,  Errors errors, Model model) {

		if (!errors.hasErrors()) {
			try {
				restauranteService.saveRestaurante(restaurante);
				model.addAttribute("msg", "restaurante gravado com sucesso");
			} catch (ValidatrionException e) {
				errors.rejectValue("email", null, e.getMessage());
			}
		}
		
		ControllerHelper.setEditMode(model, false);
		ControllerHelper.addCategoriaRequest(categoriaRestauranteRepository, model);
		return "restaurante-cadastro";
	}
}
