package com.fidelity.portfolio.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.server.ServerErrorException;
import com.fidelity.portfolio.*;
import com.fidelity.portfolio.dao.TradeHistoryImpl;
import com.fidelity.portfolio.services.Transaction;

@CrossOrigin
@RestController("tradingHistory")
@RequestMapping("/history")
public class TradeHistoryController {

	private static final String DB_ERROR_MSG = 
			"Error communicating with the trade database";
	
	@Autowired
	private Transaction service;
	
	
	@GetMapping(value="/ping",
			produces=MediaType.ALL_VALUE)
	public String ping() {
	return "service is alive and awaits your command";
	
}
	@GetMapping(value="/{id}",
			produces=MediaType.APPLICATION_JSON_VALUE)
public ResponseEntity<List<TradeHistory>> queryForWidgetById(@PathVariable long id) {
	
	ResponseEntity<List<TradeHistory>> result;
	List<TradeHistory> history;
	try {
		List<TradeHistory> history1 = service.getTransactions(id);
//		List<TradeHistory> history1 = dao.getTradeHistory(id);
		history = history1;
	} 
	catch (Exception e) {
		System.out.println("dfvddfd");
		throw new ServerErrorException(DB_ERROR_MSG, e);
	}
	
	if (history.size() > 0) {
		result = ResponseEntity.ok(history);
	}
	else {
		result = ResponseEntity.noContent().build();
	}
	return result;
}
	
}
