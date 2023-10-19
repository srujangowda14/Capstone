package com.fidelity.portfolio.restcontroller;

import java.util.logging.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fidelity.portfolio.Client;
import com.fidelity.portfolio.Order;
import com.fidelity.portfolio.Price;
import com.fidelity.portfolio.PriceList;
import com.fidelity.portfolio.ResponseObject;
import com.fidelity.portfolio.Trade;
import com.fidelity.portfolio.TradeRequest;
import com.fidelity.portfolio.TradeResponse;
import com.fidelity.portfolio.services.ClientService;
import com.fidelity.portfolio.services.PortfolioService;
import com.fidelity.portfolio.services.TradeService;

@CrossOrigin
@RestController
@RequestMapping("/trade")
public class TradeController {
	
	private static final String DB_ERROR_MSG = 
			"Error communicating with the warehouse database";
	
	private static final Logger logger = LoggerFactory.getLogger(TradeController.class);
	
	@Autowired
	ClientService clientService;
	
	@Autowired
	PortfolioService portfolioService;

	
	@Autowired
	private TradeService service;
	
	
	@GetMapping(value="/ping",
			produces=MediaType.ALL_VALUE)
	public String ping() {
		return "Trade web service is alive and awaits your command";
	}
	
	
	@GetMapping(value="/prices",
			produces={ MediaType.APPLICATION_JSON_VALUE, 
					MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<PriceList> getTradePrices() throws JsonMappingException, JsonProcessingException {
		List<Price> pricesResponse =  service.retrieveInstruments();
		System.out.print(pricesResponse);
	
      PriceList priceList = new PriceList(pricesResponse);
      System.out.print(priceList.getItems());
      if(priceList.getItems().isEmpty()) return ResponseEntity.noContent().build();
      return ResponseEntity.ok(priceList);
	}
	
	
	
	
	
	@PostMapping(value="/performTrade",
			produces={ MediaType.APPLICATION_JSON_VALUE, 
			MediaType.APPLICATION_XML_VALUE },
			consumes = {MediaType.APPLICATION_JSON_VALUE, 
					MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<String> trade(@RequestBody TradeRequest order) {
			
			try {
			
				TradeResponse res;
				ResponseObject resUnauthorized = new ResponseObject("trade unsuccesful", 401);
				Trade trade = service.trade(order);
				if( trade  != null) {
					
					res = new TradeResponse(trade, 200, "trade successful");
					clientService.updateBalance(trade);
					portfolioService.updatePortfolio(trade);
					
					return ResponseEntity.ok(res.toJson());
				}
					
				return ResponseEntity.status(401).body(resUnauthorized.toJson());
			}
			catch(Exception e) {
				ResponseObject res = new ResponseObject(e.getMessage(), 500);
				return ResponseEntity.status(500).body(res.toJson());
			}
			
			
		}
}
