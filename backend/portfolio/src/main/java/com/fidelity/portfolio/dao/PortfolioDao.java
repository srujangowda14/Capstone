package com.fidelity.portfolio.dao;

import java.util.List;

import com.fidelity.portfolio.Portfolio;
import com.fidelity.portfolio.Trade;

public interface PortfolioDao {

	public List<Portfolio> retreivePortfolio(long clientId);
	
	public boolean updatePortfolio(Portfolio portfolio);
	
	public boolean insertPortfolio(Trade trade, String description);
	
	public List<Portfolio> getPortfolioParticularInstrument(Trade trade);
	
	public String getInstrumentDescription(String instrumentId);

	public boolean deletePortfolio(Portfolio portfolio);
}
