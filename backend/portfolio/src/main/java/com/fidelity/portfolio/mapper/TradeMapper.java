package com.fidelity.portfolio.mapper;

import java.util.List;

import com.fidelity.portfolio.Client;
import com.fidelity.portfolio.Price;
import com.fidelity.portfolio.Trade;

public interface TradeMapper {

	public List<Price> queryAllPrices();

	public Price queryPriceById(String id);

	public List<Trade> queryAllTrades();

	int insertTrade(Trade trade);

	int updateClient(Client c);

	public Client queryClientById(Client c);

}
