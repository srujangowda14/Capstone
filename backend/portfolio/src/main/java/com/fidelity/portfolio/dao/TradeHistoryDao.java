package com.fidelity.portfolio.dao;
import com.fidelity.portfolio.Trade;
import com.fidelity.portfolio.TradeHistory;

import java.util.List;

public interface TradeHistoryDao {

	public List<TradeHistory> getTradeHistory(long clientId);

}
