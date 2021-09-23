package com.dambroski.domain;

import java.io.Serializable;
import java.time.Instant;

import javax.persistence.Entity;

import com.dambroski.domain.enuns.EstadoPagamento;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonTypeName;



@Entity
@JsonTypeName("pagamentoComBoleto")
public class PagamentoComBoleto extends Pagamento implements Serializable {
	private static final long serialVersionUID = 1L;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "UTC")
	private Instant dataVencimento;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "UTC")
	private Instant dataPagamento;

	public PagamentoComBoleto() {

	}

	public PagamentoComBoleto(EstadoPagamento estadoPagamento, Pedido pedido, Instant dataVencimento,
			Instant dataPagamento) {
		super(estadoPagamento, pedido);
		this.dataPagamento = dataPagamento;
		this.dataVencimento = dataVencimento;
	}

	public Instant getDataVencimento() {
		return dataVencimento;
	}

	public void setDataVencimento(Instant dataVencimento) {
		this.dataVencimento = dataVencimento;
	}

	public Instant getDataPagamento() {
		return dataPagamento;
	}

	public void setDataPagamento(Instant dataPagamento) {
		this.dataPagamento = dataPagamento;
	}

}
