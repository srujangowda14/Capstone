package com.fidelity.portfolio.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.fidelity.portfolio.Report;
import com.fidelity.portfolio.Trade;
import com.fidelity.portfolio.TradeHistory;

@Service
public class ReportService {

	public ReportService() {
		// TODO Auto-generated constructor stub
	}
	
	public Report createReportWithTradeData(TradeHistory trade) {
		Report report = new Report();
		report.setInstrumentId(trade.getInstrument_id());
		report.setTradeId(trade.getTrade_id());
		report.setPrice(trade.getExecution_price());
		report.setCashValue(trade.getCash_value());
		report.setQuantity(trade.getQuantity());
		return report;
	}
	
	public List<Report> getBuyReport(List<TradeHistory> trades, long clientId) {
		List<Report> reportList = new ArrayList<Report>();
		for(TradeHistory trade: trades) {
			if(trade.getClient_id() ==clientId
					&& trade.getDirection().equals("B")) {
				reportList.add(createReportWithTradeData(trade));
			}
		}
		return reportList;
	}
	
	public List<Report> getSellReport(List<TradeHistory> trades, long clientId) {
		List<Report> reportList = new ArrayList<Report>();
		for(TradeHistory trade: trades) {
			if(trade.getClient_id() ==clientId
					&& trade.getDirection().equals("S")) {
				reportList.add(createReportWithTradeData(trade));
			}
		}
		return reportList;
	}

}
