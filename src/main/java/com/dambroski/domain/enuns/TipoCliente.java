package com.dambroski.domain.enuns;

public enum TipoCliente {

	PESSOAFISICA(1, "Pessoa Física"),
	PESSOAJURIDICA(2, "Pessoa jurídica");
	
	
	private Integer cod;
	
	private String descricao;
	
	
	private TipoCliente(Integer cod, String descricao) {
		this.cod = cod;
		this.descricao = descricao;
	}
	
	public Integer getCod() {
		return cod;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	
	public static TipoCliente toEnum(Integer cod) {
		
		if(cod == null) {
			return null;
		}
		
		for(TipoCliente x : TipoCliente.values()) {
			if(x.getCod() == cod) {
				return x;
			}
		}throw new IllegalArgumentException("Cod inválido" + cod);
		
	}
	
	
	
}