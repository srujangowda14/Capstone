 package com.fidelity.portfolio.services;

import java.math.BigDecimal;
import java.security.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fidelity.portfolio.Client;
import com.fidelity.portfolio.Order;
import com.fidelity.portfolio.Portfolio;
import com.fidelity.portfolio.Price;
import com.fidelity.portfolio.Trade;
import com.fidelity.portfolio.TradeRequest;
import com.fidelity.portfolio.dao.ClientDao;
import com.fidelity.portfolio.dao.TradeDao;

@Service
public class TradeService {
	
	
	Client c;

	
	@Autowired
	FmtsService fmtsService;

	@Autowired
	TradeDao dao;
	
	@Autowired
	ClientService clientService;
	
	@Autowired
	PortfolioService portfolioService;
	
	public static Trade mapToTrade(Object responseObject) {
	
            Map<String, Object> responseMap = (Map<String, Object>) responseObject;

            String instrumentId  = (String) responseMap.get("instrumentId");
            long clientId = 0 ;
            if (responseMap.get("clientId") instanceof Integer) {
                clientId = ((Integer) responseMap.get("clientId")).longValue();
            }
            
            String direction = (String) responseMap.get("direction");
            String tradeId = (String) responseMap.get("tradeId");
            int quantity = (int) responseMap.get("quantity");
            BigDecimal executionPrice = null;
            Object executionPriceObject = responseMap.get("executionPrice");
            if (executionPriceObject instanceof BigDecimal) {
            	executionPrice = (BigDecimal)executionPriceObject;  // Use the BigDecimal value directly
            } else if (executionPriceObject instanceof Integer) {
            	executionPrice = BigDecimal.valueOf((Integer) executionPriceObject);  // Convert Integer to BigDecimal
            } else if (executionPriceObject instanceof Double) {
            	executionPrice = BigDecimal.valueOf((Double) executionPriceObject);  // Convert Double to BigDecimal
            } 
            
            
            BigDecimal cashValue = null;
            Object  cashValueObject = responseMap.get("cashValue");
            if ( cashValueObject instanceof BigDecimal) {
            	 cashValue = (BigDecimal) cashValueObject; 
            } else if (executionPriceObject instanceof Integer) {
            	 cashValue = BigDecimal.valueOf((Integer)  cashValueObject); 
            } else if (executionPriceObject instanceof Double) {
            	 cashValue = BigDecimal.valueOf((Double)  cashValueObject);  
            } 
            LocalDate timestamp = LocalDate.now();
            
            
              Trade trade = new Trade( cashValue, quantity, direction,timestamp, instrumentId,  clientId,
           			 tradeId,  executionPrice);
          


          
            
            return trade;

        
	}
	
	
	
	public static List<Price> mapToPriceList(List<Map<String, Object>> dataList) {
        List<Price> priceList = new ArrayList<>();
        
        
        Map<String, String> monthAbbreviations = new HashMap<>();
        monthAbbreviations.put("JAN", "01");
        monthAbbreviations.put("FEB", "02");
        monthAbbreviations.put("MAR", "03");
        monthAbbreviations.put("APR", "04");
        monthAbbreviations.put("MAY", "05");
        monthAbbreviations.put("JUN", "06");
        monthAbbreviations.put("JUL", "07");
        monthAbbreviations.put("AUG", "08");
        monthAbbreviations.put("SEP", "09");
        monthAbbreviations.put("OCT", "10");
        monthAbbreviations.put("NOV", "11");
        monthAbbreviations.put("DEC", "12");


        for (Map<String, Object> data : dataList) {
            Price price = new Price();
            Object askPriceObject = data.get("askPrice");
            BigDecimal askPrice = null;
            if (askPriceObject instanceof BigDecimal) {
                askPrice = (BigDecimal) askPriceObject;  // Use the BigDecimal value directly
            } else if (askPriceObject instanceof Integer) {
                askPrice = BigDecimal.valueOf((Integer) askPriceObject);  // Convert Integer to BigDecimal
            } else if (askPriceObject instanceof Double) {
                askPrice = BigDecimal.valueOf((Double) askPriceObject);  // Convert Double to BigDecimal
            } 
            
            price.setAskPrice(askPrice);
            
            
            BigDecimal bidPrice = null;
            Object bidPriceObject = data.get("bidPrice");

            if (bidPriceObject instanceof BigDecimal) {
                bidPrice = (BigDecimal) bidPriceObject;
            } else if (bidPriceObject instanceof Integer) {
                bidPrice = BigDecimal.valueOf((Integer) bidPriceObject);
            } else if (bidPriceObject instanceof Double) {
                bidPrice = BigDecimal.valueOf((Double) bidPriceObject);
            }

            price.setBidPrice(bidPrice);

            
            
            String dateString = (String) data.get("priceTimestamp");
            for (Map.Entry<String, String> entry : monthAbbreviations.entrySet()) {
                dateString = dateString.replace(entry.getKey(), entry.getValue());
            }
            int lastSpaceIndex = dateString.lastIndexOf(' ');

         String dateStringWithoutZone = dateString.substring(0, lastSpaceIndex);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yy hh.mm.ss.SSSSSSSSS a", Locale.ENGLISH);

            LocalDateTime localDateTime = LocalDateTime.parse(dateStringWithoutZone, formatter);

            price.setTimestamp(localDateTime);
            price.setInstrumentId(((Map<String, String>) data.get("instrument")).get("instrumentId"));

            priceList.add(price);
        }
        
        System.out.print(priceList.get(0).getInstrumentId());

        return priceList;
    }
	
	

	

	public List<Price> retrieveInstruments() throws JsonMappingException, JsonProcessingException {
		List<Object> prices = fmtsService.getTradesPrices();
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.registerModule(new JavaTimeModule());
		objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
		  String jsonData = objectMapper.writeValueAsString(prices);


       
        CollectionType mapType = objectMapper.getTypeFactory().constructCollectionType(List.class, Map.class);
        List<Map<String, Object>> dataList = objectMapper.readValue(jsonData, mapType);

        List<Price> priceList = mapToPriceList(dataList);
		return priceList;
	
	}

	public List<Trade> retrieveTrades() {
		return dao.getAllTrades();
	}

	public void setClient(Client client) {
		c = client;
	}

	public Trade trade(TradeRequest order) throws Exception {
		
		
		try {
			
			
			
			if(order.getDirection().equals("S")) {
				
				List<Portfolio> myPortfolio = portfolioService.getPortfolio(order.getClientId());
				for(Portfolio portfolio: myPortfolio) {
					if(portfolio.getInstrumentId().equals(order.getInstrumentId()) ) {
						if(portfolio.getQuantity() < order.getQuantity()) {
							throw new Exception("Can't sell more than you hold");
						}
					}
				}
				
			}
			
			
			BigDecimal currentBalance = clientService.getBalance(order.getClientId());
			
			if(order.getDirection().equals("B") &&  currentBalance.compareTo(order.getTargetPrice().multiply(new BigDecimal(order.getQuantity()))) < 0) {
				throw new Exception("not enough balance");
			}
			
			
			Object unprocessedTrade = fmtsService.trade(order);
			Trade trade = mapToTrade(unprocessedTrade);
			trade.setInstrumentDescription(order.getInstrumentDescription());
			System.out.print(trade);
			dao.insertTrade(trade);
			
			return trade;
		}
		catch(Exception e) {
			throw new Exception(e.getMessage());
		}
		
		
	}

	



}
