package com.fidelity.portfolio.services;

import com.fidelity.portfolio.*;
import com.fidelity.portfolio.dao.TradeHistoryDao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.io.File;

@Service
public class Transaction {

	@Autowired
	TradeHistoryDao dao;
	

	public List<TradeHistory> getTransactions(long ClientId) {

		
			return dao.getTradeHistory(ClientId);
		
	}

}
