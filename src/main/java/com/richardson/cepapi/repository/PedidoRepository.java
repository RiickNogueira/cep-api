package com.richardson.cepapi.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.richardson.cepapi.model.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, String> {
	public Optional<Pedido> findByPedido(String pedido);

}
