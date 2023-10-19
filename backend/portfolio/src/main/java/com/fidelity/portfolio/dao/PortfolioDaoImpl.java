package com.fidelity.portfolio.dao;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.fidelity.portfolio.Portfolio;
import com.fidelity.portfolio.Trade;
import com.fidelity.portfolio.mapper.PortfolioMapper;

@Repository("portfoliodao")
public class PortfolioDaoImpl implements PortfolioDao{
	
	@Autowired
	private PortfolioMapper mapper;

	public PortfolioDaoImpl() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<Portfolio> retreivePortfolio(long clientId) {
		return mapper.getPortfolio(clientId);

	}
	

	@Override
	public boolean insertPortfolio(Trade trade, String description) {
		return mapper.insertPortfolio(trade, description)==1;
		
	}

	@Override
	public List<Portfolio> getPortfolioParticularInstrument(Trade trade) {
		return mapper.getPortfolioParticularInstrument(trade);
		
	}

	@Override
	public boolean updatePortfolio(Portfolio portfolio) {
		return mapper.updatePortfolio(portfolio)==1;
		
	}

	@Override
	public String getInstrumentDescription(String instrumentId) {
		// TODO Auto-generated method stub
		return mapper.getInstrumentDescription(instrumentId);
	}

	@Override
	public boolean deletePortfolio(Portfolio portfolio) {
		return mapper.deletePortfolio(portfolio)==1;
		
	}

	
}
