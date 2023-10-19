package com.fidelity.portfolio;

public class Instrument {
	
	private String description;
	private String externalId;
	private int maxQuantity;
	private int minQuantity;
	private String instrumentId;
	private String categoryId;
	
	public Instrument(String description, String externalId, int maxQuantity, int minQuantity, String instrumentId,
			String categoryId) {
		super();
		this.description = description;
		this.externalId = externalId;
		this.maxQuantity = maxQuantity;
		this.minQuantity = minQuantity;
		this.instrumentId = instrumentId;
		this.categoryId = categoryId;
	}
	
}
