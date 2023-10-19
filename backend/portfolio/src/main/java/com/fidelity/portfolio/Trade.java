package com.fidelity.portfolio;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

public class Trade {

	private BigDecimal cashValue;
	public LocalDate getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(LocalDate timestamp) {
		this.timestamp = timestamp;
	}

	private int quantity;
	private String direction;
	private LocalDate timestamp;
	private String instrumentDescription;

	public String getInstrumentDescription() {
		return instrumentDescription;
	}

	public void setInstrumentDescription(String instrumentDescription) {
		this.instrumentDescription = instrumentDescription;
	}

	
	public Trade(BigDecimal cashValue, int quantity, String direction, String instrumentDescription,
			String instrumentId, long clientId, String tradeId, BigDecimal executionPrice) {
		super();
		this.cashValue = cashValue;
		this.quantity = quantity;
		this.direction = direction;
		this.instrumentDescription = instrumentDescription;
		this.instrumentId = instrumentId;
		this.clientId = clientId;
		this.tradeId = tradeId;
		this.executionPrice = executionPrice;
	}

	public Trade(BigDecimal cashValue, int quantity, String direction, LocalDate timestamp, String instrumentId,
			long clientId, String tradeId, BigDecimal executionPrice) {
		super();
		this.cashValue = cashValue;
		this.quantity = quantity;
		this.direction = direction;
		this.timestamp = timestamp;
		this.instrumentId = instrumentId;
		this.clientId = clientId;
		this.tradeId = tradeId;
		this.executionPrice = executionPrice;
	}
	
	

	public Trade(BigDecimal cashValue, int quantity, String direction, LocalDate timestamp,
			String instrumentDescription, String instrumentId, long clientId, String tradeId,
			BigDecimal executionPrice) {
		super();
		this.cashValue = cashValue;
		this.quantity = quantity;
		this.direction = direction;
		this.timestamp = timestamp;
		this.instrumentDescription = instrumentDescription;
		this.instrumentId = instrumentId;
		this.clientId = clientId;
		this.tradeId = tradeId;
		this.executionPrice = executionPrice;
	}

	@Override
	public String toString() {
		return "Trade [cashValue=" + cashValue + ", quantity=" + quantity + ", direction=" + direction + ", timestamp="
				+ timestamp + ", instrumentId=" + instrumentId + ", clientId=" + clientId + ", tradeId=" + tradeId
				+ ", executionPrice=" + executionPrice + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(cashValue, clientId, direction, executionPrice, instrumentId, quantity, timestamp, tradeId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Trade other = (Trade) obj;
		return Objects.equals(cashValue, other.cashValue) && clientId == other.clientId
				&& Objects.equals(direction, other.direction) && Objects.equals(executionPrice, other.executionPrice)
				&& Objects.equals(instrumentId, other.instrumentId) && quantity == other.quantity
				&& Objects.equals(timestamp, other.timestamp) && Objects.equals(tradeId, other.tradeId);
	}

	public Trade() {
		super();
		// TODO Auto-generated constructor stub
	}

	private String instrumentId;
	private long clientId;
	private String tradeId;
	private BigDecimal executionPrice;



	public BigDecimal getCashValue() {
		return cashValue;
	}

	public void setCashValue(BigDecimal cashValue) {
		this.cashValue = cashValue;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}

	public String getInstrumentId() {
		return instrumentId;
	}

	public void setInstrumentId(String instrumentId) {
		this.instrumentId = instrumentId;
	}

	public long getClientId() {
		return clientId;
	}

	public void setClientId(long clientId) {
		this.clientId = clientId;
	}

	public String getTradeId() {
		return tradeId;
	}

	public void setTradeId(String tradeId) {
		this.tradeId = tradeId;
	}

	public BigDecimal getExecutionPrice() {
		return executionPrice;
	}

	public void setExecutionPrice(BigDecimal executionPrice) {
		this.executionPrice = executionPrice;
	}

	

	
}
