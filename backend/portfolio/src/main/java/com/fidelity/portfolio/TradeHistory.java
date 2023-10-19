package com.fidelity.portfolio;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Objects;

public class TradeHistory {

	BigDecimal cash_value;
	int quantity;
	String direction;
	String instrument_id;
	long client_id;
	String trade_id;
	BigDecimal execution_price;
	Date timestamp;
	
	public BigDecimal getCash_value() {
		return cash_value;
	}

	public void setCash_value(BigDecimal cash_value) {
		this.cash_value = cash_value;
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

	public String getInstrument_id() {
		return instrument_id;
	}

	public void setInstrument_id(String instrument_id) {
		this.instrument_id = instrument_id;
	}

	public long getClient_id() {
		return client_id;
	}

	public void setClient_id(long client_id) {
		this.client_id = client_id;
	}

	public String getTrade_id() {
		return trade_id;
	}

	public void setTrade_id(String trade_id) {
		this.trade_id = trade_id;
	}

	public BigDecimal getExecution_price() {
		return execution_price;
	}

	public void setExecution_price(BigDecimal execution_price) {
		this.execution_price = execution_price;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}
	

	public TradeHistory(BigDecimal cash_value, int quantity, String direction, String instrument_id, long client_id,
			String trade_id, BigDecimal execution_price, Date timestamp) {
		this();
		this.cash_value = cash_value;
		this.quantity = quantity;
		this.direction = direction;
		this.instrument_id = instrument_id;
		this.client_id = client_id;
		this.trade_id = trade_id;
		this.execution_price = execution_price;
		this.timestamp = timestamp;
	}

	public TradeHistory() {
		
	}
	


	
}
