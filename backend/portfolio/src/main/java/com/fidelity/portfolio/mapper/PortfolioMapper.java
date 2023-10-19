package com.fidelity.portfolio.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;

import com.fidelity.portfolio.Portfolio;
import com.fidelity.portfolio.Trade;

public interface PortfolioMapper {

	public int updatePortfolio(Portfolio portfolio);
	public List<Portfolio> getPortfolio(long clientId);
	
	 @Insert("INSERT INTO portfolio (client_id, instrument_id, instrument_description, bought_price, quantity, total_investment) " +
	            "VALUES (#{trade.clientId}, #{trade.instrumentId}, #{description}, " +
				
	            "#{trade.executionPrice}, #{trade.quantity}, #{trade.cashValue})")
	int insertPortfolio(@Param("trade") Trade trade, @Param("description") String description);
	
//	public void insertPortfolio(Trade trade);
	public List<Portfolio> getPortfolioParticularInstrument(Trade trade);
	
	public String getInstrumentDescription(String instrumentId);
	
	public int deletePortfolio(Portfolio portfolio);

}
