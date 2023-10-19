package com.fidelity.portfolio;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.Objects;

public class Price {

	// private Instrument instrument;
	private BigDecimal bidPrice;
	private String instrumentId;
	private BigDecimal askPrice;
	private LocalDateTime timestamp;

	public Price(BigDecimal bidPrice, String instrumentId, BigDecimal askPrice, LocalDateTime timestamp) {
		// this.instrument = instrument;
		this.bidPrice = bidPrice.setScale(2, RoundingMode.HALF_DOWN);
		this.instrumentId = instrumentId;
		this.askPrice = askPrice.setScale(2, RoundingMode.HALF_DOWN);
		this.timestamp = timestamp;
	}

	@Override
	public String toString() {
		return "Price [bidPrice=" + bidPrice + ", instrumentId=" + instrumentId + ", askPrice=" + askPrice
				+ ", timestamp=" + timestamp + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(askPrice, bidPrice, instrumentId, timestamp);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Price other = (Price) obj;
		return Objects.equals(askPrice, other.askPrice) && Objects.equals(bidPrice, other.bidPrice)
				&& Objects.equals(instrumentId, other.instrumentId) && Objects.equals(timestamp, other.timestamp);
	}

	public Price() {

	}

//	public Instrument getInstrument() {
//		return instrument;
//	}
//
//	public void setInstrument(Instrument instrument) {
//		this.instrument = instrument;
//	}

	public BigDecimal getBidPrice() {
		return bidPrice;
	}

	public void setBidPrice(BigDecimal bidPrice) {
		this.bidPrice = bidPrice.setScale(2, RoundingMode.HALF_DOWN);
	}

	public String getInstrumentId() {
		return instrumentId;
	}

	public void setInstrumentId(String instrumentId) {
		this.instrumentId = instrumentId;
	}

	public BigDecimal getAskPrice() {
		return askPrice;
	}

	public void setAskPrice(BigDecimal askPrice) {
		this.askPrice = askPrice.setScale(2, RoundingMode.HALF_DOWN);
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}

}
