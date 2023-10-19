package com.fidelity.portfolio.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerErrorException;

import com.fidelity.portfolio.Report;
import com.fidelity.portfolio.TradeHistory;
import com.fidelity.portfolio.services.ReportService;
import com.fidelity.portfolio.services.Transaction;

@CrossOrigin
@RestController
@RequestMapping("/report")
public class ReportsController {

	@Autowired
	private ReportService service;
	
	@Autowired
	private Transaction transactions;
	
	
	@GetMapping("/buyReport/{clientId}")
	public ResponseEntity<List<Report>> getBuyReport(@PathVariable long clientId) {
		ResponseEntity<List<Report>> response;
		List<TradeHistory> trade;
		List<Report> report;
		try {
			trade = transactions.getTransactions(clientId);
			report = service.getBuyReport(trade, clientId);
		}catch(Exception e) {
			throw new ServerErrorException("Error in reports",e);
		}
		
		response = ResponseEntity.ok(report);
		return response;
	}
	
	
	@GetMapping("/sellReport/{clientId}")
	public ResponseEntity<List<Report>> getSellReport(@PathVariable long clientId) {
		ResponseEntity<List<Report>> response;
		List<TradeHistory> trade;
		List<Report> report;
		try {
			trade = transactions.getTransactions(clientId);
			report = service.getSellReport(trade, clientId);
		}catch(Exception e) {
			throw new ServerErrorException("Error in reports",e);
		}
		
		response = ResponseEntity.ok(report);
		return response;
	}
}

