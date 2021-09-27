package com.dambroski.domain.enuns;



public enum Perfil {

	ADMIN(1, "ROLE_ADMIN"),
	CLIENTE(2, "ROLE_CLIENTE");
	
	
	private Integer cod;
	
	private String descricao;
	
	
	private Perfil(Integer cod, String descricao) {
		this.cod = cod;
		this.descricao = descricao;
		
	}


	public int getCod() {
		return cod;
	}


	public void setCod(Integer cod) {
		this.cod = cod;
	}


	public String getDescricao() {
		return descricao;
	}


	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	
	
	public static Perfil getPerfil(Integer cod) {
		if(cod == null) {
			return null;
		}
		for(Perfil x : Perfil.values()) {
			if(x.getCod() == cod) {
				return x;
			} 
		}throw new IllegalArgumentException("Cod inválido" + cod);
	}
	
	
	
	
	
}
