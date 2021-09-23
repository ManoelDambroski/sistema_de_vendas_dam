package com.dambroski.domain;

import java.io.Serializable;

import javax.persistence.Entity;

import com.dambroski.domain.enuns.EstadoPagamento;
import com.fasterxml.jackson.annotation.JsonTypeName;

@Entity
@JsonTypeName("pagamentoComCartao")
public class PagamentoComCartao extends Pagamento implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer numeroDeParcelas;

	public PagamentoComCartao() {

	}

	public PagamentoComCartao(EstadoPagamento estadoPagamento, Pedido pedido, Integer numeroDeParcelas) {
		super(estadoPagamento, pedido);
		this.numeroDeParcelas = numeroDeParcelas;

	}

	public Integer getNumeroDeParcelas() {
		return numeroDeParcelas;
	}

	public void setNumeroDeParcelas(Integer numeroDeParcelas) {
		this.numeroDeParcelas = numeroDeParcelas;
	}

}