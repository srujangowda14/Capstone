package com.fidelity.portfolio;

import java.math.BigDecimal;

public class InstrumentSuggestion {
	
	private String description;
	private String instrument_id;
	private BigDecimal price;
	public InstrumentSuggestion(String description, String instrument_id, BigDecimal price) {
		this();
		this.description = description;
		this.instrument_id = instrument_id;
		this.price = price;
	}
	public InstrumentSuggestion() {

		// TODO Auto-generated constructor stub
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getInstrument_id() {
		return instrument_id;
	}
	public void setInstrument_id(String instrument_id) {
		this.instrument_id = instrument_id;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}

}
