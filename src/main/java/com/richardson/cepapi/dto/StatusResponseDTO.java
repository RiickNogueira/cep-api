package com.richardson.cepapi.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class StatusResponseDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private String pedido;
	private List<String> status = new ArrayList<String>();

	public String getPedido() {
		return pedido;
	}

	public void setPedido(String pedido) {
		this.pedido = pedido;
	}

	public List<String> getStatus() {
		return status;
	}

	public void setStatus(List<String> status) {
		this.status = status;
	}

}
