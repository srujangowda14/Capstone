package com.fidelity.portfolio;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

public class Report {

	private String instrumentId;
	private String tradeId;
	private int quantity;
	private BigDecimal price;
	private BigDecimal cashValue;
	private BigDecimal profit;
	
	
	public String getInstrumentId() {
		return instrumentId;
	}
	public void setInstrumentId(String instrumentId) {
		if(instrumentId.length()<=0)
			throw new IllegalArgumentException("Instrument ID can't be empty");
		this.instrumentId = instrumentId;
	}
	
	
	public int getQuantity() {
		return quantity;
	}
	
	public void setQuantity(int quantity) {
		if(quantity<=0)
			throw new IllegalArgumentException("Invalid Quantity");
		this.quantity = quantity;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		if(price.compareTo(BigDecimal.ZERO)==0 ||
				price.compareTo(BigDecimal.ZERO)==-1)
			throw new IllegalArgumentException("Price invalid");
		this.price = price;
	}
	public BigDecimal getCashValue() {
		return cashValue;
	}
	public void setCashValue(BigDecimal cashValue) {
		if(cashValue.compareTo(BigDecimal.ZERO)==0 ||
				cashValue.compareTo(BigDecimal.ZERO)==-1)
			throw new IllegalArgumentException("Cash Value invalid");
		this.cashValue = cashValue;
	}
	
	public BigDecimal getProfit() {
		return profit;
	}
	public void setProfit(BigDecimal profit) {
		this.profit = profit;
	}
	
	public void setTradeId(String tradeId) {
		this.tradeId = tradeId;
	}
	@Override
	public int hashCode() {
		return Objects.hash(cashValue, instrumentId, price, profit, quantity, tradeId);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Report other = (Report) obj;
		return Objects.equals(cashValue, other.cashValue) && Objects.equals(instrumentId, other.instrumentId)
				&& Objects.equals(price, other.price) && Objects.equals(profit, other.profit)
				&& quantity == other.quantity && Objects.equals(tradeId, other.tradeId);
	}
	public String getTradeId() {
		return tradeId;
	}
	
	
	
}
