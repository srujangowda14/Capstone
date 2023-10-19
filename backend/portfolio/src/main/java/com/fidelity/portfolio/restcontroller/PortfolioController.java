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

import com.fidelity.portfolio.Portfolio;
import com.fidelity.portfolio.services.PortfolioService;

@CrossOrigin
@RestController
@RequestMapping("/portfolio")
public class PortfolioController {
	private static final String DB_ERROR_MSG = 
			"Error communicating with the portfolio database";
	
	@Autowired
	private PortfolioService service;
	
	
	@GetMapping("/{clientId}")
	public ResponseEntity<List<Portfolio>> getPortfolio(@PathVariable long clientId){
		ResponseEntity<List<Portfolio>> response=null;
		List<Portfolio> portfolio = null;
		try {
			portfolio = service.getPortfolio(clientId);
		}catch(Exception e) {
			throw new ServerErrorException(DB_ERROR_MSG,e);
		}
		
		if(portfolio.size()==0) {
			response = ResponseEntity.noContent().build();
		}else {
			response = ResponseEntity.ok(portfolio);
		}
		
		return response;
	}
}
