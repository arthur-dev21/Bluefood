package com.arthurdev.bluefood.domain.restaurante;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.web.multipart.MultipartFile;

import com.arthurdev.bluefood.domain.usuario.Usuario;
import com.arthurdev.bluefood.infrastructure.web.validator.UploadConstraint;
import com.arthurdev.bluefood.util.FileType;


@SuppressWarnings("serial")
@Entity
@Table(name = "restaurante")
public class Restaurante extends Usuario {
    
	
	@NotBlank(message = "O CNPJ não pode ser vazio")
	@Pattern(regexp = "[0-9]{14}", message = "O CNPJ possui formato invalido")
	@Column(length = 14, nullable = false)
	private String cnpj;
	
	@Size(max = 80)
	private String logotipo;
	
	@UploadConstraint(acceptedTypes = FileType.PNG)
	private transient MultipartFile logotipoFile;
	
	@NotNull(message = "A taxa de entrega não pode ser vazia")
	@Min(0)
	@Max(99)
	private BigDecimal taxaEntrega;
	
	@NotNull(message = "O tempo de entrega não pode ser vazio")
	@Min(0)
	@Max(120)
	private Integer tempoEntregaBase;
    
	@ManyToMany                                                               //cria um relacionamneto muitos para muitos
	@JoinTable(                                                                //define os criterios da nova tabela
			name="restaurante_has_categoria",                                 //nome da nova tabela
			joinColumns = @JoinColumn(name="restaurante_id"),                 // o nome da coluna que vai fazer refenecia a restaurante na nova tabela
			inverseJoinColumns = @JoinColumn(name="categoria_restaurante_id") //relacionamento do outro lado que no caso é categoria
			)                                                                 
	private Set<CategoriaRestaurante>categorias= new HashSet<>(0); 
	//o atributo do set "Categoriarestaurante" sera o "categoria_restaurante_id"
	
	@OneToMany(mappedBy = "restaurante")
	private Set<ItemCardapio> itensCardapio = new HashSet<>(0);
	
	//metodo para colocar um nome para o restaurante
	public void setLogotipoFileName() {
		
		if(getId() ==null) {                                                           //id nao pode ser nulo pois tem que esta preenchido
			throw new IllegalStateException("é preciso primeiro gravar o registro");
		}
		this.logotipo=String.format("%04d-logo.%s",getId() , FileType.of(logotipoFile.getContentType()).getExtension());                                //pege o primeiro parametro de args , complete com "0" a esquerda ate que o tamanho fique com tamanho "4"
	}

	public MultipartFile getLogotipoFile() {
		return logotipoFile;
	}

	public void setLogotipoFile(MultipartFile logotipoFile) {
		this.logotipoFile = logotipoFile;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public Set<CategoriaRestaurante> getCategorias() {
		return categorias;
	}

	public void setCategorias(Set<CategoriaRestaurante> categorias) {
		this.categorias = categorias;
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
