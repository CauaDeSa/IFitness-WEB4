package br.edu.ifsp.ifitness.model;

public enum ActivityType {

	CAMINHADA("Caminhada"),
	CICLISMO("Ciclismo"),
	CORRIDA("Corrida"),
	NATACAO("Natação");
	
	private String description;
	
	ActivityType(String description) {
		this.description = description;
	}
	
	public String getDescription() {
		return description;
	}
	
}
