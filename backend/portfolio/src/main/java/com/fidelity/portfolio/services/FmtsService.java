package com.fidelity.portfolio.services;


import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ServerErrorException;


import com.fidelity.portfolio.Client;
import com.fidelity.portfolio.LoginRequest;
import com.fidelity.portfolio.LoginResponse;
import com.fidelity.portfolio.Order;
import com.fidelity.portfolio.Price;
import com.fidelity.portfolio.TradePrice;
import com.fidelity.portfolio.TradeRequest;

 

@Service
public class FmtsService {
	

	    private String fmtsApiUrl="https://a720855.roifmr.com";
	    
	    
	    private RestTemplate restTemplate;

        
	    public FmtsService(RestTemplate restTemplate) {
	        this.restTemplate = restTemplate;
	    }

 
	    public List<Object> getTradesPrices() {
	        String apiUrl = fmtsApiUrl + "/fmts/trades/prices";
	        Object[] pricesArray = restTemplate.getForObject(apiUrl, Object[].class);
	      
	        return Arrays.asList(pricesArray);
	       
	    }
	    
	    public List<TradePrice> getTradesPrice() {
	        String apiUrl = fmtsApiUrl + "/fmts/trades/prices";
	        TradePrice[] pricesArray = restTemplate.getForObject(apiUrl, TradePrice[].class);
	      
	        return Arrays.asList(pricesArray);
	       
	    }
	    

	    public LoginResponse getClientIdFromFmts(LoginRequest client) throws Exception {
		   	 String apiUrl = fmtsApiUrl + "/fmts/client";
	    	 
	    	 HttpHeaders headers = new HttpHeaders();
	         headers.setContentType(MediaType.APPLICATION_JSON);
	
	    	 
	    	 HttpEntity<LoginRequest> requestEntity = new HttpEntity<>(client, headers);
	    	 ResponseEntity<LoginResponse> responseEntity = restTemplate.postForEntity(apiUrl, requestEntity, LoginResponse.class);
	    	 
	    	 if(responseEntity.getStatusCodeValue() != 200) {
	    		 throw new Exception("login failure, incorrect clientId");
	    	 }
	    	 
	    	LoginResponse response = responseEntity.getBody();
	    	
	    	System.out.print(response);
	    	
	    	return response;
	    }

	    
	    
	    
	    

		public Object trade(TradeRequest order) throws Exception {
	    	
			
			
			
			
	    	 String apiUrl = fmtsApiUrl + "/fmts/trades/trade";
	    	 
	    	 HttpHeaders headers = new HttpHeaders();
	         headers.setContentType(MediaType.APPLICATION_JSON);
	
	    	 
	    	 HttpEntity<TradeRequest> requestEntity = new HttpEntity<>(order, headers);
	    	 ResponseEntity<Object> responseEntity = restTemplate.postForEntity(apiUrl, requestEntity, Object.class);
	    	 
	    	 if(responseEntity.getStatusCodeValue() != 200) {
	    		 throw new Exception("trade failure");
	    	 }
	    	 
	    	 
	    	Object trade = responseEntity.getBody();
	    	
	    	System.out.print(trade);
	    	
	    	return trade;
	    }
		
		
		
		
		public LoginResponse login(LoginRequest client) throws Exception {
	    	
	    	 String apiUrl = fmtsApiUrl + "/fmts/client";
	    	 
	    	 HttpHeaders headers = new HttpHeaders();
	         headers.setContentType(MediaType.APPLICATION_JSON);
	
	    	 
	    	 HttpEntity<LoginRequest> requestEntity = new HttpEntity<>(client, headers);
	    	 ResponseEntity<LoginResponse> responseEntity = restTemplate.postForEntity(apiUrl, requestEntity, LoginResponse.class);
	    	 
	    	 if(responseEntity.getStatusCodeValue() != 200) {
	    		 throw new Exception("login failure, incorrect clientId");
	    	 }
	    	 
	    	LoginResponse response = responseEntity.getBody();
	    	
	    	System.out.print(response);
	    	
	    	return response;
	    }
	    
	    
	  
		

}

