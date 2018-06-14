package com.kohatsu.projectdelta.domain.enums;


public enum Semanas {

		DOMINGO(1, "Domingo"),
		SEGUNDA(2, "Segunda-Feira"),
		TERCA(3, "Terça-Feira"),
		QUARTA(4, "Quarta-Feira"),
		QUINTA(5, "Quinta-Feira"),
		SEXTA(6, "Sexta-Feira"),
		SABADO(7, "Sábado");
	
	private int cod;
	private String descricao;
	
	private Semanas(int cod, String descricao) {
		
		this.cod=cod;
		this.descricao=descricao;
		
	}

	public int getCod() {
		return cod;
	}

	public String getDescricao() {
		return descricao;
	}


	
	public static Semanas toEnum(Integer cod) {
		
		if(cod == null) {
			
			return null;
			
		} else {
			
			for(Semanas x: Semanas.values()) {
				
				if(cod.equals(x.getCod())) {
					
					return x;
					
				}
				
			}
			
		}
		
		throw new IllegalArgumentException("Id inválido: "+cod);
		
	}
	
	
	
}
