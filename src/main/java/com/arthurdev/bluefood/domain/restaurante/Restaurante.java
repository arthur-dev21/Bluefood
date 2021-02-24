package com.arthurdev.bluefood.domain.restaurante;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.arthurdev.bluefood.domain.usuario.Usuario;


@SuppressWarnings("serial")
@Entity
@Table(name = "restaurante")
public class Restaurante extends Usuario {
    
	
	@NotBlank(message = "O CNPJ não pode ser vazio")
	@Pattern(regexp = "[0-9]{14}", message = "O CNPJ possui formato inv�lido")
	@Column(length = 14, nullable = false)
	private String cnpj;
	
	@Size(max = 80)
	private String logotipo;
	
	
	
	@NotNull(message = "A taxa de entrega não pode ser vazia")
	@Min(0)
	@Max(99)
	private BigDecimal taxaEntrega;
	
	@NotNull(message = "O tempo de entrega não pode ser vazio")
	@Min(0)
	@Max(120)
	private Integer tempoEntregaBase;

	
	
	
	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getLogotipo() {
		return logotipo;
	}

	public void setLogotipo(String logotipo) {
		this.logotipo = logotipo;
	}

	public BigDecimal getTaxaEntrega() {
		return taxaEntrega;
	}

	public void setTaxaEntrega(BigDecimal taxaEntrega) {
		this.taxaEntrega = taxaEntrega;
	}

	public Integer getTempoEntregaBase() {
		return tempoEntregaBase;
	}

	public void setTempoEntregaBase(Integer tempoEntregaBase) {
		this.tempoEntregaBase = tempoEntregaBase;
	}
	
	
	
}
