package com.fidelity.portfolio;

import java.math.BigDecimal;
import java.util.Objects;

public class TradePrice {
	
	private BigDecimal askPrice;
    private BigDecimal bidPrice;
    private String priceTimestamp;
    private InstrumentTrade instrument;
    
	public TradePrice() {}
	
	public TradePrice(BigDecimal askPrice, BigDecimal bidPrice, String priceTimestamp, InstrumentTrade instrument) {
		this();
		this.askPrice = askPrice;
		this.bidPrice = bidPrice;
		this.priceTimestamp = priceTimestamp;
		this.instrument = instrument;
	}
	public BigDecimal getAskPrice() {
		return askPrice;
	}
	public void setAskPrice(BigDecimal askPrice) {
		this.askPrice = askPrice;
	}
	public BigDecimal getBidPrice() {
		return bidPrice;
	}
	public void setBidPrice(BigDecimal bidPrice) {
		this.bidPrice = bidPrice;
	}
	public String getPriceTimestamp() {
		return priceTimestamp;
	}
	public void setPriceTimestamp(String priceTimestamp) {
		this.priceTimestamp = priceTimestamp;
	}
	public InstrumentTrade getInstrument() {
		return instrument;
	}
	public void setInstrument(InstrumentTrade instrument) {
		this.instrument = instrument;
	}
	@Override
	public int hashCode() {
		return Objects.hash(askPrice, bidPrice, instrument, priceTimestamp);
	}

	@Override
	public String toString() {
		return "TradePrice [askPrice=" + askPrice + ", bidPrice=" + bidPrice + ", priceTimestamp=" + priceTimestamp
				+ ", instrument=" + instrument + "]";
	}
    
    

}


