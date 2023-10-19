package com.fidelity.portfolio;

import java.time.LocalDate;
import java.util.Objects;

public class InstrumentHistory {
	
	String instrument_id;
	@Override
	public String toString() {
		return "InstrumentHistory [instrument_id=" + instrument_id + ", price_date=" + price_date + ", opening_price="
				+ opening_price + ", closing_price=" + closing_price + ", volume_purchased=" + volume_purchased
				+ ", category_id=" + category_id + "]";
	}
	@Override
	public int hashCode() {
		return Objects.hash(category_id, closing_price, instrument_id, opening_price, price_date, volume_purchased);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		InstrumentHistory other = (InstrumentHistory) obj;
		return Objects.equals(category_id, other.category_id)
				&& Float.floatToIntBits(closing_price) == Float.floatToIntBits(other.closing_price)
				&& Objects.equals(instrument_id, other.instrument_id)
				&& Float.floatToIntBits(opening_price) == Float.floatToIntBits(other.opening_price)
				&& Objects.equals(price_date, other.price_date)
				&& Float.floatToIntBits(volume_purchased) == Float.floatToIntBits(other.volume_purchased);
	}
	public String getInstrument_id() {
		return instrument_id;
	}
	public void setInstrument_id(String instrument_id) {
		this.instrument_id = instrument_id;
	}
	public LocalDate getPrice_date() {
		return price_date;
	}
	public void setPrice_date(LocalDate price_date) {
		this.price_date = price_date;
	}
	public float getOpening_price() {
		return opening_price;
	}
	public void setOpening_price(float opening_price) {
		this.opening_price = opening_price;
	}
	public float getClosing_price() {
		return closing_price;
	}
	public void setClosing_price(float closing_price) {
		this.closing_price = closing_price;
	}
	public float getVolume_purchased() {
		return volume_purchased;
	}
	public void setVolume_purchased(float volume_purchased) {
		this.volume_purchased = volume_purchased;
	}
	public String getCategory_id() {
		return category_id;
	}
	public void setCategory_id(String category_id) {
		this.category_id = category_id;
	}
	LocalDate price_date;
	float opening_price;
	float closing_price;
	float volume_purchased;
	String category_id;
	public InstrumentHistory(String instrument_id, LocalDate price_date, float opening_price, float closing_price,
			float volume_purchased, String category_id) {
		super();
		this.instrument_id = instrument_id;
		this.price_date = price_date;
		this.opening_price = opening_price;
		this.closing_price = closing_price;
		this.volume_purchased = volume_purchased;
		this.category_id = category_id;
	}

}
