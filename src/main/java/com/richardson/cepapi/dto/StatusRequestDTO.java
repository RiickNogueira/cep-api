package com.richardson.cepapi.dto;

import java.io.Serializable;

public class StatusRequestDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private String status;
	private Integer itensAprovados;
	private double valorAprovado;
	private String pedido;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getItensAprovados() {
		return itensAprovados;
	}

	public void setItensAprovados(Integer itensAprovados) {
		this.itensAprovados = itensAprovados;
	}

	public double getValorAprovado() {
		return valorAprovado;
	}

	public void setValorAprovado(double valorAprovado) {
		this.valorAprovado = valorAprovado;
	}

	public String getPedido() {
		return pedido;
	}

	public void setPedido(String pedido) {
		this.pedido = pedido;
	}

}
