package com.fidelity.portfolio;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

public class Order {

	private String instrumentId;
	private int quantity;
	private BigDecimal targetPrice;
	private String direction;
	private long clientId;
	private String orderId;

	public Order(String instrumentId, int quantity, BigDecimal targetPrice, String direction, long clientId,
			String orderId) {
		super();
		this.instrumentId = instrumentId;
		this.quantity = quantity;
		this.targetPrice = targetPrice.setScale(2, RoundingMode.HALF_DOWN);
		this.direction = direction;
		this.clientId = clientId;
		this.orderId = orderId;
	}

	@Override
	public int hashCode() {
		return Objects.hash(clientId, direction, instrumentId, orderId, quantity, targetPrice);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Order other = (Order) obj;
		return clientId == other.clientId && Objects.equals(direction, other.direction)
				&& Objects.equals(instrumentId, other.instrumentId) && Objects.equals(orderId, other.orderId)
				&& quantity == other.quantity && Objects.equals(targetPrice, other.targetPrice);
	}

	public String getInstrumentId() {
		return instrumentId;
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
		this.targetPrice = targetPrice.setScale(2, RoundingMode.HALF_DOWN);
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

	public void setInstrumentId(String instrumentId) {
		this.instrumentId = instrumentId;
	}
}
