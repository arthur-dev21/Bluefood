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

import com.arthurdev.bluefood.application.ClienteService;
import com.arthurdev.bluefood.domain.cliente.Cliente;

@Controller
@RequestMapping("/public")
public class PublicController {

	@Autowired
	private ClienteService clienteService;

	@GetMapping("/cliente/new")
	public String newCliente(Model model) {
		model.addAttribute("cliente", new Cliente());
		ControllerHelper.setEditMode(model, false);
		return "cliente-cadastro";
	}

	@PostMapping("/cliente/save")
	public String saveCliente(@ModelAttribute("cliente") @Valid Cliente cliente,  Errors errors, Model model) {

		if (!errors.hasErrors()) {
			clienteService.saveCliente(cliente);
            model.addAttribute("msg", "cliente gravado com sucesso");
		}
		model.addAttribute("cliente", new Cliente());
		ControllerHelper.setEditMode(model, false);
		return "cliente-cadastro";
	}
}
