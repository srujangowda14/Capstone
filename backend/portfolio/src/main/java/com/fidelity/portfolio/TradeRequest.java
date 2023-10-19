package com.fidelity.portfolio;

import java.math.BigDecimal;
import java.util.Objects;

public class TradeRequest {
	
	private String instrumentId;
	private int quantity;
	private BigDecimal targetPrice;
	private String direction;
	private long clientId;
	private String orderId;
	private String email;
	private String instrumentDescription;

	private long token;
	
	
	


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public TradeRequest(String instrumentId, int quantity, BigDecimal targetPrice, String direction, long clientId,
			String orderId, String email, long token, String instrumentDescription) {
		super();
		this.instrumentId = instrumentId;
		this.quantity = quantity;
		this.targetPrice = targetPrice;
		this.direction = direction;
		this.clientId = clientId;
		this.orderId = orderId;
		this.email = email;
		this.token = token;
		this.instrumentDescription = instrumentDescription;
	}


	public String getInstrumentDescription() {
		return instrumentDescription;
	}


	public void setInstrumentDescription(String instrumentDescription) {
		this.instrumentDescription = instrumentDescription;
	}


	@Override
	public int hashCode() {
		return Objects.hash(clientId, direction, email, instrumentDescription, instrumentId, orderId, quantity,
				targetPrice, token);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TradeRequest other = (TradeRequest) obj;
		return clientId == other.clientId && Objects.equals(direction, other.direction)
				&& Objects.equals(email, other.email)
				&& Objects.equals(instrumentDescription, other.instrumentDescription)
				&& Objects.equals(instrumentId, other.instrumentId) && Objects.equals(orderId, other.orderId)
				&& quantity == other.quantity && Objects.equals(targetPrice, other.targetPrice) && token == other.token;
	}


	@Override
	public String toString() {
		return "TradeRequest [instrumentId=" + instrumentId + ", quantity=" + quantity + ", targetPrice=" + targetPrice
				+ ", direction=" + direction + ", clientId=" + clientId + ", orderId=" + orderId + ", email=" + email
				+ ", instrumentDescription=" + instrumentDescription + ", token=" + token + "]";
	}


	public String getInstrumentId() {
		return instrumentId;
	}


	public void setInstrumentId(String instrumentId) {
		this.instrumentId = instrumentId;
	}


	public int getQuantity() {
		return quantity;
	}


	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}


	public BigDecimal getTargetPrice() {
		return targetPrice;
	}


	public void setTargetPrice(BigDecimal targetPrice) {
		this.targetPrice = targetPrice;
	}


	public String getDirection() {
		return direction;
	}


	public void setDirection(String direction) {
		this.direction = direction;
	}


	public long getClientId() {
		return clientId;
	}


	public void setClientId(long clientId) {
		this.clientId = clientId;
	}


	public String getOrderId() {
		return orderId;
	}


	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}


	public long getToken() {
		return token;
	}


	public void setToken(long token) {
		this.token = token;
	}
}
