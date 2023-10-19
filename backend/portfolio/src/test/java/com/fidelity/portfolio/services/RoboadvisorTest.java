//package com.fidelity.portfolio.services;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//import java.io.IOException;
//import java.math.BigDecimal;
//import java.util.ArrayList;
//import java.util.List;
//
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import com.fidelity.portfolio.Portfolio;
//import com.fidelity.portfolio.Stock;
//
//class RoboadvisorTest {
//	ArrayList<Portfolio> ClientHoldings;
//	
//	
//
//	Roboadvisor x;
//	
//	@BeforeEach
//	void setUp() throws Exception {
//		
//		 x = new Roboadvisor();
//		
//		 ClientHoldings = new ArrayList<Portfolio>();
//	   	 ClientHoldings.add(new Portfolio("N123456", "Apple Inc.", new BigDecimal(104.75), new BigDecimal(213.109), 2, "abc513275530"));
//	   	 ClientHoldings.add(new Portfolio("N123457", "Microsoft Corporation", new BigDecimal(104.75), new BigDecimal(213.109), 2, "abc513275530"));
//	   	 ClientHoldings.add(new Portfolio("N123451", "JPMorgan Chase & Co. Capital Stock", new BigDecimal(104.75), new BigDecimal(213.109), 2, "abc513275530"));
//	}
//
//	@AfterEach
//	void tearDown() throws Exception {
//	}
//
//	@Test
//	void test() throws IllegalArgumentException, IOException {
//
//		x.suggestStocksToBuy("Aggressive","234567891");
//	}
//	
//	@Test
//	void teststockSellFunctionality() throws IllegalArgumentException, IOException {
//		
//		
//		List<Stock> suggestions = x.suggestStocksToSell("Aggressive",ClientHoldings);
//		assertTrue(suggestions.size()==1);
//		
//	}
//	
//	@Test
//	void teststocSellFunctionalityWithoutInstrument() throws IllegalArgumentException, IOException {
//		ClientHoldings.remove(0);
//		List<Stock> suggestions = x.suggestStocksToSell("Aggressive",ClientHoldings);
//		assertTrue(suggestions.size()==0);
//	}
//	
//	
//	@Test
//	void teststockBuyFunctionalityWithBalance() throws IllegalArgumentException, IOException {
//		
//		
//		List<Stock> suggestions = x.suggestStocksToBuy("Aggressive","234567891");
//		assertNotNull(suggestions);
//		
//	}
//	
//	@Test
//	void teststockBuyFunctionalityWithoutBalance() throws IllegalArgumentException, IOException {
//
//		List<Stock> suggestions = x.suggestStocksToBuy("Aggressive","234567893");
//		assertTrue(suggestions.size()==0);
//		
//	}
//
//}
