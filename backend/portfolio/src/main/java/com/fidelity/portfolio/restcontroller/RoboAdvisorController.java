package com.fidelity.portfolio.restcontroller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerErrorException;

import com.fidelity.portfolio.InstrumentHistory;
import com.fidelity.portfolio.InstrumentSuggestion;
import com.fidelity.portfolio.TradeHistory;
import com.fidelity.portfolio.dao.InstrumentHistoryDao;
import com.fidelity.portfolio.services.Roboadvisor;
import com.fidelity.portfolio.services.Transaction;


@CrossOrigin
@RestController("instrumentHistory")
@RequestMapping("/roboadvisor")
public class RoboAdvisorController {

	private static final String DB_ERROR_MSG = 
			"Error communicating with the trade database";
	
	@Autowired
	private Roboadvisor service;
	
	
	@GetMapping(value="/analyse/buy/{id}",
			produces=MediaType.APPLICATION_JSON_VALUE)
public ResponseEntity<ArrayList<InstrumentSuggestion>> queryForWidgetById(@PathVariable long id) {
	
	ArrayList<InstrumentSuggestion> history;
	ResponseEntity<ArrayList<InstrumentSuggestion>> result;
	try {
		
		history =service.suggestStocksToBuy(id);
		result = ResponseEntity.ok(history);
		
		return result;
//		for(InstrumentHistory i : history) {
//			System.out.println(i.getPrice_date()+","+i.getPrice_date());
//		}
	} 
	catch (Exception e) {
		System.out.println("dfvddfd");
		throw new ServerErrorException(DB_ERROR_MSG, e);
	}
	
}
	
	
	
	@GetMapping(value="/analyse/sell/{id}",
			produces=MediaType.APPLICATION_JSON_VALUE)
public ResponseEntity<ArrayList<InstrumentSuggestion>> queryForWidgetByIdForSell(@PathVariable long id) {
	
	ArrayList<InstrumentSuggestion> history;
	ResponseEntity<ArrayList<InstrumentSuggestion>> result;
	try {
		
		history =service.suggestStocksToSell(id);
		result = ResponseEntity.ok(history);
		
		return result;
//		for(InstrumentHistory i : history) {
//			System.out.println(i.getPrice_date()+","+i.getPrice_date());
//		}
	} 
	catch (Exception e) {
		System.out.println("dfvddfd");
		throw new ServerErrorException(DB_ERROR_MSG, e);
	}
	
}
	
	
}
