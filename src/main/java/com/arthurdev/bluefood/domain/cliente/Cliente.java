package com.arthurdev.bluefood.domain.cliente;

import javax.persistence.Entity;

import com.arthurdev.bluefood.domain.usuario.Usuario;

@Entity
public class Cliente extends Usuario {
    
	private String cpf;
	
	private String cep;

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}
	
	
	
}
