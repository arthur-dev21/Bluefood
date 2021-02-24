package com.arthurdev.bluefood.infrastructure.web.controller;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.ui.Model;

import com.arthurdev.bluefood.domain.restaurante.CategoriaRestaurante;
import com.arthurdev.bluefood.domain.restaurante.CategoriaRestauranteRepository;

public class ControllerHelper {
   
	public static void setEditMode(Model model , boolean isEdit) {
		model.addAttribute("editMode", isEdit);
	}
	
	public static void addCategoriaRequest(CategoriaRestauranteRepository  repository ,Model model) {
		List<CategoriaRestaurante>categorias= repository.findAll(Sort.by("nome"));
		model.addAttribute("categorias", categorias);
	}
}
