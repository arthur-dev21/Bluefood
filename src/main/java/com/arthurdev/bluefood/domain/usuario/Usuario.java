package com.arthurdev.bluefood.domain.usuario;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import antlr.StringUtils;

@SuppressWarnings("serial")
@MappedSuperclass
public class Usuario implements Serializable {
    
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@NotBlank(message = "o NOME nao pode ser vazio")
	@Size(max = 80, message = "o NOME é muito grande")
	private String nome;
	
	@NotBlank(message = "o EMAIL nao pode ser vazio")
	@Size(max = 60, message = "o EMAIL é muito grande")
	@Email(message = "o formato do EMAIL é invalido")
	private String email;
	
	//@NotBlank(message = "a SENHA nao pode ser vazia")
	@Size(max = 80 , message = "a SENHA é muito grande")
	private String senha;
	
	@NotBlank(message = "o telefone nao pode ser vazio")
	@Pattern(regexp = "[0-9]{10,11}" , message = "o telefone tem formato invalido")
	@Column(length = 11 , nullable = false)
	private String telefone;
	
	
	public void encryptPassword() {
		this.senha = com.arthurdev.bluefood.util.StringUtils.encrypt(this.senha);
	}

	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
	
	
	
}
