package com.fidelity.portfolio.dao;

import com.fidelity.portfolio.exceptions.*;
import com.fidelity.portfolio.mapper.TradeHistoryMapper;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.*;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.fidelity.portfolio.Client;
import com.fidelity.portfolio.Instrument;
import com.fidelity.portfolio.Trade;
import com.fidelity.portfolio.TradeHistory;

//sample trade

@Repository("tradeHistoryDao")
public class TradeHistoryImpl implements TradeHistoryDao {
	
	
	private final Logger logger = LoggerFactory.getLogger(getClass());

	
	
	@Autowired
	TradeHistoryMapper mapper;
	

	@Override
	public List<TradeHistory> getTradeHistory(long client_id) {
		

		try {
		List<TradeHistory> temp = mapper.getTradeHistory(client_id);
		return temp;
		}
		catch(Exception e) {
			e.printStackTrace();
			throw new DatabaseException("Error fetching trade history");
		}
	
}


}
