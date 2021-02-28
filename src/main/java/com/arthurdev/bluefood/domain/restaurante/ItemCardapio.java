package com.arthurdev.bluefood.domain.restaurante;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.web.multipart.MultipartFile;

import com.arthurdev.bluefood.infrastructure.web.validator.UploadConstraint;
import com.arthurdev.bluefood.util.FileType;
import com.sun.istack.NotNull;

@SuppressWarnings("serial")
@Entity
@Table(name = "item_cardapio")
public class ItemCardapio implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@NotBlank(message = "O nome não pode ser vazio")
	@Size(max =  50)
	private String nome;
	
	@NotBlank(message = "A categoria não pode ser vazia")
	@Size(max =  25)
	private String categoria;
	
	@NotBlank(message = "A descrição não pode ser vazia")
	@Size(max =  80)
	private String descricao;
	
	@Size(max = 50)
	private String imagem;
	
	@NotNull
	@Min(0)
	private BigDecimal preco;
	
	@NotNull
	private Boolean destaque;
	
	@NotNull
	@ManyToOne                                              //dono do relacionamento
	@JoinColumn(name = "restaurante_id")                    
	private Restaurante restaurante;
	
	@UploadConstraint(acceptedTypes = FileType.PNG, message = "O arquivo não é um arquivo de imagem válido")
	private transient MultipartFile imagemFile;
	
	
	public void setImagemFileName() {
		if (getId() == null) {
			throw new IllegalStateException("O objeto precisa primeiro ser criado");
		}
		
		this.imagem = String.format("%04d-comida.%s", getId(), FileType.of(imagemFile.getContentType()).getExtension());
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


	public String getCategoria() {
		return categoria;
	}


	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}


	public String getDescricao() {
		return descricao;
	}


	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}


	public String getImagem() {
		return imagem;
	}


	public void setImagem(String imagem) {
		this.imagem = imagem;
	}


	public BigDecimal getPreco() {
		return preco;
	}


	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}


	public Boolean getDestaque() {
		return destaque;
	}


	public void setDestaque(Boolean destaque) {
		this.destaque = destaque;
	}


	public Restaurante getRestaurante() {
		return restaurante;
	}


	public void setRestaurante(Restaurante restaurante) {
		this.restaurante = restaurante;
	}


	public MultipartFile getImagemFile() {
		return imagemFile;
	}


	public void setImagemFile(MultipartFile imagemFile) {
		this.imagemFile = imagemFile;
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
		ItemCardapio other = (ItemCardapio) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
	
}
