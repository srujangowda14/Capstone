package com.fidelity.portfolio.services;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fidelity.portfolio.Portfolio;
import com.fidelity.portfolio.Trade;
import com.fidelity.portfolio.dao.PortfolioDao;

@Service
public class PortfolioService {

	private List<Portfolio> allPortfolio;
	
	@Autowired
	private PortfolioDao dao;
	

	public PortfolioService() {
		allPortfolio = new ArrayList<Portfolio>();
		
	}
	
	public List<Portfolio> getPortfolio(long clientId){
		List<Portfolio> clientPortfolio = dao.retreivePortfolio(clientId);
		return clientPortfolio;
	}
	
	private void updatePortfolioForSellTransaction(Trade trade, Portfolio p) {
		p.setQuantity(p.getQuantity() - trade.getQuantity());
		p.setTotalInvestment(p.getTotalInvestment().subtract(trade.getCashValue()));
	}
	
	private void updatePortfolioForBuyTransaction(Trade trade, Portfolio p) {
		p.setBoughtPrice(trade.getExecutionPrice());
		p.setQuantity(p.getQuantity() + trade.getQuantity());
		p.setTotalInvestment(p.getTotalInvestment().add(trade.getCashValue()));
	}
	
	public boolean updatePortfolio(Trade trade) {
		List<Portfolio> portfolios = null;
		
		portfolios = dao.retreivePortfolio(trade.getClientId());
		String description = trade.getInstrumentDescription();
		System.out.println(description);
		if(portfolios.size()==0) {
			return dao.insertPortfolio(trade, description);
			
		}
		
		portfolios = dao.getPortfolioParticularInstrument(trade);
		
		if(portfolios.size()==0) {
			return dao.insertPortfolio(trade, description);
			
		}
		Portfolio p = portfolios.get(0);
		if(trade.getDirection().equals("B")) {
			updatePortfolioForBuyTransaction(trade, p);
			return dao.updatePortfolio(p);
			
		}else if(trade.getDirection().equals("S")) {
			if(p.getQuantity() == trade.getQuantity()) {
				return dao.deletePortfolio(p);
			}else {
				updatePortfolioForSellTransaction(trade, p);
				return dao.updatePortfolio(p);
			}
		}
		
		return true;
	}
	

	@Override
	public int hashCode() {
		return Objects.hash(allPortfolio);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PortfolioService other = (PortfolioService) obj;
		return Objects.equals(allPortfolio, other.allPortfolio);
	}
	
	

}
