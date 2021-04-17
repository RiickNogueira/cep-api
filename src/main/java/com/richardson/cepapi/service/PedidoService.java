package com.richardson.cepapi.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.commons.math3.util.Precision;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.richardson.cepapi.dto.StatusRequestDTO;
import com.richardson.cepapi.dto.StatusResponseDTO;
import com.richardson.cepapi.model.Item;
import com.richardson.cepapi.model.Pedido;
import com.richardson.cepapi.repository.ItemRepository;
import com.richardson.cepapi.repository.PedidoRepository;
import com.richardson.springbootapi.service.exception.ObjectNotFoundException;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository repo;

	@Autowired
	private ItemRepository itemRepo;

	public List<Pedido> findAll() {
		return repo.findAll();
	}

	public Pedido findOrThrow(String id) {
		Optional<Pedido> obj = repo.findById(id);
		return obj.orElseThrow(
				() -> new ObjectNotFoundException("Não foi encontrado nenhum pedido com este número (" + id + ")"));
	}

	@Transactional
	public Pedido create(Pedido obj) {
		Pedido savedObj = repo.save(obj);
		for (Item item : obj.getItens()) {
			item.setPedido(savedObj);
			itemRepo.save(item);
		}
		return savedObj;
	}

	public void delete(String id) {
		repo.deleteById(findOrThrow(id).getPedido());
	}

	public StatusResponseDTO getStatus(StatusRequestDTO request) {
		Optional<Pedido> pedido = repo.findById(request.getPedido());

		StatusResponseDTO response = new StatusResponseDTO();
		response.setPedido(request.getPedido());

		checharSePedidoExiste(response, pedido, request);

		return response;
	}

	private void checharSePedidoExiste(StatusResponseDTO response, Optional<Pedido> pedido, StatusRequestDTO request) {
		if (!pedido.isPresent()) {
			response.setStatus(List.of("CODIGO_PEDIDO_INVALIDO"));
		} else {
			checarStatusPedido(response, pedido, request);
		}

	}

	private void checarStatusPedido(StatusResponseDTO response, Optional<Pedido> pedido, StatusRequestDTO request) {
		if (request.getStatus().equals("REPROVADO")) {
			response.setPedido(request.getPedido());
			response.setStatus(List.of("REPROVADO"));
		} else if (request.getStatus().equals("APROVADO")) {
			addStatusAprovado(response, pedido, request);
		}
	}

	private void addStatusAprovado(StatusResponseDTO response, Optional<Pedido> pedido, StatusRequestDTO request) {
		Integer qtdItens = pedido.get().getItens().stream().map(item -> item.getQtd()).reduce(0, (a, b) -> a + b);
		Double valorPedido = calcularValorTotalPedido(pedido);

		List<String> status = new ArrayList<String>();
		if (request.getItensAprovados() > qtdItens) {
			status.add("APROVADO_QTD_A_MAIOR");
		}
		if (request.getItensAprovados() < qtdItens) {
			status.add("APROVADO_QTD_A_MENOR");
		}
		if (request.getValorAprovado() > valorPedido) {
			status.add("APROVADO_VALOR_A_MAIOR");
		}
		if (request.getValorAprovado() < valorPedido) {
			status.add("APROVADO_VALOR_A_MENOR");
		}
		if (status.size() < 1) {
			status.add("APROVADO");
		}
		response.setStatus(status);
	}

	public Double calcularValorTotalPedido(Optional<Pedido> pedido) {
		Double valorPedido = 0.0;
		for (Item item : pedido.get().getItens()) {
			valorPedido += item.getQtd() * item.getPrecoUnitario();
		}
		valorPedido = Precision.round(valorPedido, 1);
		return valorPedido;
	}
}
