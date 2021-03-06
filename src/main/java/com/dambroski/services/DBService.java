package com.dambroski.services;



import java.text.ParseException;
import java.time.Instant;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.dambroski.domain.Categoria;
import com.dambroski.domain.Cidade;
import com.dambroski.domain.Cliente;
import com.dambroski.domain.Endereco;
import com.dambroski.domain.Estado;
import com.dambroski.domain.ItemPedido;
import com.dambroski.domain.Pagamento;
import com.dambroski.domain.PagamentoComBoleto;
import com.dambroski.domain.PagamentoComCartao;
import com.dambroski.domain.Pedido;
import com.dambroski.domain.Produto;
import com.dambroski.domain.enuns.EstadoPagamento;
import com.dambroski.domain.enuns.Perfil;
import com.dambroski.domain.enuns.TipoCliente;
import com.dambroski.repositories.CategoriaRepository;
import com.dambroski.repositories.CidadeRepository;
import com.dambroski.repositories.ClienteRepository;
import com.dambroski.repositories.EnderecoRepository;
import com.dambroski.repositories.EstadoRepository;
import com.dambroski.repositories.ItemPedidoRepository;
import com.dambroski.repositories.PagamentoRepository;
import com.dambroski.repositories.PedidoRepository;
import com.dambroski.repositories.ProdutoRepository;



@Service
public class DBService {

	@Autowired
	private CategoriaRepository categoriaRepository;
	@Autowired
	private ProdutoRepository produtoRepository;
	@Autowired
	private EstadoRepository estadoRepository;
	@Autowired
	private CidadeRepository cidadeRepository;
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private EnderecoRepository enderecoRepository;
	@Autowired
	private PedidoRepository pedidoRepository;
	@Autowired
	private PagamentoRepository pagamentoRepository;
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;
	@Autowired
	private BCryptPasswordEncoder BPC;

	public void instantiateTestDatabase() throws ParseException {

		Categoria cat1 = new Categoria("Inform??tica");
		Categoria cat2 = new Categoria("Escrit??rio");
		Categoria cat3 = new Categoria("Cama mesa e banho");
		Categoria cat4 = new Categoria("Eletr??nicos");
		Categoria cat5 = new Categoria("Jardinagem");
		Categoria cat6 = new Categoria("Decora????o");
		Categoria cat7 = new Categoria("Perfumaria");

		Produto p1 = new Produto("Computador", 2000.00);
		Produto p2 = new Produto("Impressora", 800.00);
		Produto p3 = new Produto("Mouse", 80.00);
		Produto p4 = new Produto("Mesa de escrit??rio", 300.00);
		Produto p5 = new Produto("Toalha", 50.00);
		Produto p6 = new Produto("Colcha", 200.00);
		Produto p7 = new Produto("TV true color", 1200.00);
		Produto p8 = new Produto("Ro??adeira", 800.00);
		Produto p9 = new Produto("Abajour", 100.00);
		Produto p10 = new Produto("Pendente", 180.00);
		Produto p11 = new Produto("Shampoo", 90.00);

		Produto p12 = new Produto("Produto 12", 10.00);
		Produto p13 = new Produto("Produto 13", 10.00);
		Produto p14 = new Produto("Produto 14", 10.00);
		Produto p15 = new Produto("Produto 15", 10.00);
		Produto p16 = new Produto("Produto 16", 10.00);
		Produto p17 = new Produto("Produto 17", 10.00);
		Produto p18 = new Produto("Produto 18", 10.00);
		Produto p19 = new Produto("Produto 19", 10.00);
		Produto p20 = new Produto("Produto 20", 10.00);
		Produto p21 = new Produto("Produto 21", 10.00);
		Produto p22 = new Produto("Produto 22", 10.00);
		Produto p23 = new Produto("Produto 23", 10.00);
		Produto p24 = new Produto("Produto 24", 10.00);
		Produto p25 = new Produto("Produto 25", 10.00);
		Produto p26 = new Produto("Produto 26", 10.00);
		Produto p27 = new Produto("Produto 27", 10.00);
		Produto p28 = new Produto("Produto 28", 10.00);
		Produto p29 = new Produto("Produto 29", 10.00);
		Produto p30 = new Produto("Produto 30", 10.00);
		Produto p31 = new Produto("Produto 31", 10.00);
		Produto p32 = new Produto("Produto 32", 10.00);
		Produto p33 = new Produto("Produto 33", 10.00);
		Produto p34 = new Produto("Produto 34", 10.00);
		Produto p35 = new Produto("Produto 35", 10.00);
		Produto p36 = new Produto("Produto 36", 10.00);
		Produto p37 = new Produto("Produto 37", 10.00);
		Produto p38 = new Produto("Produto 38", 10.00);
		Produto p39 = new Produto("Produto 39", 10.00);
		Produto p40 = new Produto("Produto 40", 10.00);
		Produto p41 = new Produto("Produto 41", 10.00);
		Produto p42 = new Produto("Produto 42", 10.00);
		Produto p43 = new Produto("Produto 43", 10.00);
		Produto p44 = new Produto("Produto 44", 10.00);
		Produto p45 = new Produto("Produto 45", 10.00);
		Produto p46 = new Produto("Produto 46", 10.00);
		Produto p47 = new Produto("Produto 47", 10.00);
		Produto p48 = new Produto("Produto 48", 10.00);
		Produto p49 = new Produto("Produto 49", 10.00);
		Produto p50 = new Produto("Produto 50", 10.00);

		cat1.getProdutos()
				.addAll(Arrays.asList(p12, p13, p14, p15, p16, p17, p18, p19, p20, p21, p22, p23, p24, p25, p26, p27,
						p28, p29, p30, p31, p32, p34, p35, p36, p37, p38, p39, p40, p41, p42, p43, p44, p45, p46, p47,
						p48, p49, p50));

		p12.getCategorias().add(cat1);
		p13.getCategorias().add(cat1);
		p14.getCategorias().add(cat1);
		p15.getCategorias().add(cat1);
		p16.getCategorias().add(cat1);
		p17.getCategorias().add(cat1);
		p18.getCategorias().add(cat1);
		p19.getCategorias().add(cat1);
		p20.getCategorias().add(cat1);
		p21.getCategorias().add(cat1);
		p22.getCategorias().add(cat1);
		p23.getCategorias().add(cat1);
		p24.getCategorias().add(cat1);
		p25.getCategorias().add(cat1);
		p26.getCategorias().add(cat1);
		p27.getCategorias().add(cat1);
		p28.getCategorias().add(cat1);
		p29.getCategorias().add(cat1);
		p30.getCategorias().add(cat1);
		p31.getCategorias().add(cat1);
		p32.getCategorias().add(cat1);
		p33.getCategorias().add(cat1);
		p34.getCategorias().add(cat1);
		p35.getCategorias().add(cat1);
		p36.getCategorias().add(cat1);
		p37.getCategorias().add(cat1);
		p38.getCategorias().add(cat1);
		p39.getCategorias().add(cat1);
		p40.getCategorias().add(cat1);
		p41.getCategorias().add(cat1);
		p42.getCategorias().add(cat1);
		p43.getCategorias().add(cat1);
		p44.getCategorias().add(cat1);
		p45.getCategorias().add(cat1);
		p46.getCategorias().add(cat1);
		p47.getCategorias().add(cat1);
		p48.getCategorias().add(cat1);
		p49.getCategorias().add(cat1);
		p50.getCategorias().add(cat1);

		cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProdutos().addAll(Arrays.asList(p2, p4));
		cat3.getProdutos().addAll(Arrays.asList(p5, p6));
		cat4.getProdutos().addAll(Arrays.asList(p1, p2, p3, p7));
		cat5.getProdutos().addAll(Arrays.asList(p8));
		cat6.getProdutos().addAll(Arrays.asList(p9, p10));
		cat7.getProdutos().addAll(Arrays.asList(p11));

		categoriaRepository.saveAll(Arrays.asList(cat1, cat2, cat3, cat4, cat5, cat6, cat7));
		produtoRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11));

		produtoRepository.saveAll(Arrays.asList(p12, p13, p14, p15, p16, p17, p18, p19, p20, p21, p22, p23, p24, p25,
				p26, p27, p28, p29, p30, p31, p32, p33, p34, p35, p36, p37, p38, p39, p40, p41, p42, p43, p44, p45, p46,
				p47, p48, p49, p50));

		Estado est1 = new Estado("Minas Gerais");
		Estado est2 = new Estado("S??o Paulo");

		Cidade c1 = new Cidade("Uberl??ndia", est1);
		Cidade c2 = new Cidade("S??o Paulo", est2);
		Cidade c3 = new Cidade("Campinas", est2);

		estadoRepository.saveAll(Arrays.asList(est1, est2));
		cidadeRepository.saveAll(Arrays.asList(c1, c2, c3));

		Cliente cli1 = new Cliente("Maria Silva",BPC.encode("4455hhr"),  "dambroskitestedev@gmail.com", "90281220018",
				TipoCliente.PESSOAFISICA);
		cli1.addPerfil(Perfil.ADMIN);
		

		cli1.getTelefones().addAll(Arrays.asList("27363323", "93838393"));

		Cliente cli2 = new Cliente("Ana Costa",BPC.encode("88874"), "m3anoeldambroski@gmail.com", "27286423000176", TipoCliente.PESSOAJURIDICA);
		cli2.getTelefones().addAll(Arrays.asList("93883321", "34252625"));
		
		Cliente cli3 = new Cliente();
		cli3.setCpfOuCnpj("05231738492");
		cli3.setEmail("manoel@gmail.com");
		cli3.setNome("Manoel dambroksi");
		cli3.setSenha(BPC.encode("1234"));
		cli3.setTipo(TipoCliente.PESSOAFISICA);
		cli3.getTelefones().addAll(Arrays.asList("93883321", "34252625"));
		cli3.addPerfil(Perfil.ADMIN);

		
		Endereco e1 = new Endereco("Rua Flores", "300", "Apto 303", "Jardim", "38220834", cli1, c1);
		Endereco e2 = new Endereco("Avenida Matos", "105", "Sala 800", "Centro", "38777012", cli1, c2);
		Endereco e3 = new Endereco("Avenida Floriano", "2106", null, "Centro", "281777012", cli2, c2);

		cli1.getEnderecos().addAll(Arrays.asList(e1, e2));
		cli2.getEnderecos().addAll(Arrays.asList(e3));

		clienteRepository.saveAll(Arrays.asList(cli1, cli2, cli3));
		enderecoRepository.saveAll(Arrays.asList(e1, e2, e3));

		Pedido ped1 = new Pedido(Instant.now(), cli1, e1);
		Pedido ped2 = new Pedido(Instant.now(), cli2, e3);

		Pagamento pagto1 = new PagamentoComCartao(EstadoPagamento.QUITADO, ped1, 6);
		ped1.setPagamento(pagto1);

		Pagamento pagto2 = new PagamentoComBoleto(EstadoPagamento.PENDENTE, ped2, ped2.getInstant(), null);
		ped2.setPagamento(pagto2);

		cli1.getPedidos().addAll(Arrays.asList(ped1));
		cli2.getPedidos().addAll(Arrays.asList(ped2));

		pedidoRepository.saveAll(Arrays.asList(ped1, ped2));
		pagamentoRepository.saveAll(Arrays.asList(pagto1, pagto2));

		ItemPedido ip1 = new ItemPedido(ped1, p1, 0.00, 1);
		ItemPedido ip2 = new ItemPedido(ped1, p3, 0.00, 2);
		ItemPedido ip3 = new ItemPedido(ped2, p2, 0.3, 1);

		ped1.getItens().addAll(Arrays.asList(ip1, ip2));
		ped2.getItens().addAll(Arrays.asList(ip3));

		p1.getItens().addAll(Arrays.asList(ip1));
		p2.getItens().addAll(Arrays.asList(ip3));
		p3.getItens().addAll(Arrays.asList(ip2));

		itemPedidoRepository.saveAll(Arrays.asList(ip1, ip2, ip3));

	}

}
