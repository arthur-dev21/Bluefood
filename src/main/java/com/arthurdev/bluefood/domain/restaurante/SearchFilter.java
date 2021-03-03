package com.arthurdev.bluefood.domain.restaurante;

import com.arthurdev.bluefood.util.StringUtils;

public class SearchFilter {
   
	
	public enum SearchType {
		Texto, Categoria
	}
	
	public enum Order {
		Taxa, Tempo;
	}
	
	public enum Command {
		EntregaGratis, MaiorTaxa, MenorTaxa, MaiorTempo, MenorTempo;
	}
	
	private String texto;
	private SearchType searchType;
	private Integer categoriaId;
	private Order order = Order.Taxa;
	private boolean asc;
	private boolean entregaGratis;
	
	public void processFilter( String cmdString) {
		
		
		if (!StringUtils.isEmpty(cmdString)) {
			Command cmd = Command.valueOf(cmdString);
			
			if (cmd == Command.EntregaGratis) {
				entregaGratis = !entregaGratis;
			
			} else if (cmd == Command.MaiorTaxa) {
				order = Order.Taxa;
				asc = false;
			
			} else if (cmd == Command.MenorTaxa) {
				order = Order.Taxa;
				asc = true;
			
			} else if (cmd == Command.MaiorTempo) {
				order = Order.Tempo;
				asc = false;
			
			} else if (cmd == Command.MenorTempo) {
				order = Order.Tempo;
				asc = true;
			}
		}
		
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



	public Order getOrder() {
		return order;
	}



	public void setOrder(Order order) {
		this.order = order;
	}



	public boolean isAsc() {
		return asc;
	}



	public void setAsc(boolean asc) {
		this.asc = asc;
	}



	public boolean isEntregaGratis() {
		return entregaGratis;
	}



	public void setEntregaGratis(boolean entregaGratis) {
		this.entregaGratis = entregaGratis;
	}
	
	
	
}
