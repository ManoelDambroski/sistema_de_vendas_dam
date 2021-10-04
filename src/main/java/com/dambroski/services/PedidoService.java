package com.dambroski.services;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.dambroski.domain.Cliente;
import com.dambroski.domain.ItemPedido;
import com.dambroski.domain.PagamentoComBoleto;
import com.dambroski.domain.Pedido;
import com.dambroski.domain.enuns.EstadoPagamento;
import com.dambroski.domain.enuns.Perfil;
import com.dambroski.repositories.ClienteRepository;
import com.dambroski.repositories.ItemPedidoRepository;
import com.dambroski.repositories.PagamentoRepository;
import com.dambroski.repositories.PedidoRepository;
import com.dambroski.security.UserSS;
import com.dambroski.services.exceptions.AuthorizationException;
import com.dambroski.services.exceptions.EntitieNotFoundException;



@Service

public class PedidoService {

	@Autowired
	private PedidoRepository pedidoRepository;

	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private ClienteService clienteService;

	@Autowired
	private ItemPedidoRepository itemPedidoRepository;

	@Autowired
	private ProdutoService produtoService;

	@Autowired
	private PagamentoRepository pagamentoRepository;

	@Autowired
	private EmailService emailService;
	
	
	@Autowired
	private Environment environment;
	

	public Pedido findById(Integer id) {
		
		Optional<Pedido> ped = pedidoRepository.findById(id);
		
		return ped.orElseThrow(() -> new EntitieNotFoundException("Pedido não encontrado ID: " + id));
	}
	

	public Page<Pedido> findByCliente(String nome, Pageable pageable) {
		Cliente cliente = clienteRepository.findByNomeContainingIgnoreCase(nome);
		Page<Pedido> pedidos = pedidoRepository.findByCliente(cliente, pageable);
		return pedidos;
	}
	
	public Page<Pedido> findPage(Pageable pageable){
		UserSS user = UserService.authenticated();
		if(user == null) {
			throw new AuthorizationException("Acesso negado");
		}
		Cliente cliente  = clienteService.findById(user.getId());
		return pedidoRepository.findByCliente(cliente, pageable);	
	}
	
	@Transactional
	public Pedido insert(Pedido pedido) {
		Cliente cliente = clienteService.findById(pedido.getCliente().getId());
		pedido.setInstant(Instant.now());
		pedido.setCliente(cliente);
		pedido.setEnderecoDeEntrega(cliente.getEnderecoDeEntrega(pedido.getEnderecoDeEntrega().getId()));
		pedido.getPagamento().setEstadoPagamento(EstadoPagamento.PENDENTE);
		pedido.getPagamento().setPedido(pedido);

		pedido = pedidoRepository.save(pedido);

		// mock de geração de data do vencimento
		if (pedido.getPagamento() instanceof PagamentoComBoleto) {
			PagamentoComBoleto pg = (PagamentoComBoleto) pedido.getPagamento();
			pg.setDataVencimento(pedido.getInstant().plus(5, ChronoUnit.DAYS));
		}

		pagamentoRepository.save(pedido.getPagamento());

		for (ItemPedido x : pedido.getItens()) {

			x.getId().setProduto(produtoService.findById(x.getId().getProduto().getId()));
			x.getId().setPedido(pedido);
			x.setPreco(x.getProduto().getPreco());

		}

		itemPedidoRepository.saveAll(pedido.getItens());
		if(!Arrays.asList(environment.getActiveProfiles()).contains("test")) {
			emailService.sendOrderConfimationHtmlEmail(pedido);
		}
		
		
		return pedido;

	}

}