package com.fidelity.portfolio;

import java.util.ArrayList;
import java.util.List;


public class PriceList {
	
	private List<Price> priceList;

	public PriceList() {
		priceList = new ArrayList<>();
	}

	public PriceList(List<Price> list) {
		priceList = list;
	}

	public List<Price> getItems() {
		return priceList;
	}


}
