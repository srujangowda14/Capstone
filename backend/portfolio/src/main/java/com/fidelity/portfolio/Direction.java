package com.fidelity.portfolio;

public enum Direction {
	
	 BUY("B"),
	 SELL("S");
	private String value;

	Direction(String string) {
		// TODO Auto-generated constructor stub
		this.value = value;
	}
	
	public String getValue() {
        return value;
    }
}
