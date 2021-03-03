package com.arthurdev.bluefood.domain.restaurante;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RestauranteRepository extends JpaRepository<Restaurante, Integer> {
     
	public Restaurante findByEmail(String email);
	
	public List<Restaurante> findByNomeIgnoreCaseContaining(String nome);
	
	public List<Restaurante> findByCategorias_Id(Integer categoriaId);
}  
