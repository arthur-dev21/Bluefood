package com.arthurdev.bluefood.infrastructure.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
		return "cliente-cadastro";
	}
	
	@PostMapping("/cliente/save")
	public String saveCliente(@ModelAttribute("cliente") Cliente cliente , Model model) {
		clienteService.saveCliente(cliente);
		model.addAttribute("cliente", new Cliente());
		return "cliente-cadastro";
	}
}
