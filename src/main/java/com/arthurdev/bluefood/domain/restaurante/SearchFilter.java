package com.arthurdev.bluefood.domain.restaurante;

public class SearchFilter {
   
	
	public enum SearchType {
		Texto, Categoria
	}
	
	private String texto;
	private SearchType searchType;
	private Integer categoriaId;
	
	public void processFilter() {
		if (searchType == SearchType.Texto) {
			categoriaId = null;
		} else if (searchType == SearchType.Categoria) {
			texto = null;
		}
	}
	
	

	public Integer getCategoriaId() {
		return categoriaId;
	}

	public void setCategoriaId(Integer categoriaId) {
		this.categoriaId = categoriaId;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public SearchType getSearchType() {
		return searchType;
	}

	public void setSearchType(SearchType searchType) {
		this.searchType = searchType;
	}
	
	
	
}
