package com.fidelity.portfolio.dao;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.fidelity.portfolio.Client;
import com.fidelity.portfolio.Order;
import com.fidelity.portfolio.Portfolio;
import com.fidelity.portfolio.Price;
import com.fidelity.portfolio.Trade;
import com.fidelity.portfolio.mapper.TradeMapper;
import com.fidelity.portfolio.services.PortfolioService;

@Repository("tradeDao")
public class TradeDaoImpl implements TradeDao {

	public TradeDaoImpl() {
		super();
		// TODO Auto-generated constructor stub
	}

	BigDecimal tolerance = new BigDecimal(0.05).setScale(2, RoundingMode.HALF_DOWN);
	BigDecimal fee = new BigDecimal(0.01).setScale(2, RoundingMode.HALF_DOWN);

	@Autowired
	TradeMapper mapper;
	
	@Autowired
	PortfolioService portfolioService;

	@Override
	public List<Price> getInstrumentPrices() {
		return mapper.queryAllPrices();
	}

	@Override
	public List<Trade> getAllTrades() {
		return mapper.queryAllTrades();
	}


	@Override
	public Price getPriceByInstrumentId(String id) {
		return mapper.queryPriceById(id);
	}

	
	@Override
	public int insertTrade(Trade trade) {
		return mapper.insertTrade(trade);
	}

	

	
	
}
