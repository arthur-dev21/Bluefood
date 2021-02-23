package com.arthurdev.bluefood.domain.cliente;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import com.arthurdev.bluefood.domain.usuario.Usuario;

@SuppressWarnings("serial")
@Entity
public class Cliente extends Usuario {
    
	@NotBlank(message = "o cpf nao pode ser vazio")
	@Pattern(regexp = "[0-9]{10,11}" , message = "o CPF possui formato invalido")
	@Column(length = 11)
	private String cpf;
	
	@NotBlank(message = "o cpf nao pode ser vazio")
	@Pattern(regexp = "[0-9]{8}" , message = "o CPF possui formato invalido")
	@Column(length = 8)
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
