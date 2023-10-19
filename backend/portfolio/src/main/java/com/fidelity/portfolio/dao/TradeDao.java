package com.fidelity.portfolio.dao;

import java.util.List;

import com.fidelity.portfolio.Client;
import com.fidelity.portfolio.Order;
import com.fidelity.portfolio.Portfolio;
import com.fidelity.portfolio.Price;
import com.fidelity.portfolio.Trade;

public interface TradeDao {

	List<Price> getInstrumentPrices();

	List<Trade> getAllTrades();

	
	Price getPriceByInstrumentId(String id);

	
	int insertTrade(Trade t);

}
