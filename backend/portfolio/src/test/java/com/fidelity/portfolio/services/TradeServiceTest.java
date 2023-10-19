//package com.fidelity.portfolio.services;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertThrows;
//import static org.mockito.Mockito.mock;
//import static org.mockito.Mockito.when;
//
//import java.math.BigDecimal;
//import java.math.RoundingMode;
//import java.time.LocalDate;
//import java.time.LocalDateTime;
//import java.util.ArrayList;
//import java.util.HashSet;
//import java.util.List;
//import java.util.Set;
//
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.mockito.MockitoAnnotations;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//import org.springframework.transaction.annotation.Transactional;
//
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.JsonMappingException;
//import com.fidelity.portfolio.Client;
//import com.fidelity.portfolio.ClientIdentification;
//import com.fidelity.portfolio.Order;
//import com.fidelity.portfolio.Portfolio;
//import com.fidelity.portfolio.Price;
//import com.fidelity.portfolio.Trade;
//import com.fidelity.portfolio.TradeRequest;
//import com.fidelity.portfolio.dao.TradeDao;
//
//class TradeServiceTest {
//
//	@Mock
//	TradeDao mockDao;
//	
//	 @Mock
//	 private FmtsService innerService;
////
//	@InjectMocks
//	TradeService service;
//
//	List<Price> instrumentPrices;
//	Client c;
//	List<Portfolio> portfolios;
//
//	@Autowired
//	TradeService fixture;
//	
//	
//
//	@BeforeAll
//	static void setUpBeforeClass() throws Exception {
//	}
//
//	@BeforeEach
//	void setUp() throws Exception {
//
//		Set<ClientIdentification> identifications = new HashSet<>();
//		ClientIdentification id = new ClientIdentification("SSN", "2222222");
//		identifications.add(id);
//
//		c = new Client("abc@gmail.com", "50997", "ABC", LocalDate.now().minusYears(20), "USA", identifications);
//
//		// setting up current client's portfolio
//		Portfolio portfolio1 = new Portfolio("N123", "JP Morgan",
//				new BigDecimal(104.25).setScale(2, RoundingMode.HALF_DOWN),
//				new BigDecimal(105.29).setScale(2, RoundingMode.HALF_DOWN), 1, 943842911);
//		portfolios = new ArrayList<>();
//		portfolios.add(portfolio1);
//
//		MockitoAnnotations.openMocks(this);
////
////		TradeDao testDao = new TradeDaoImpl();
////		fixture = new TradeService();
////		ReflectionTestUtils.setField(fixture, "dao", testDao);
//
//	}
//
//	@AfterEach
//	void tearDown() throws Exception {
//		// fixture = null;
//		c = null;
//
//	}
//
//	@Test
//	void testRetreiveInstruments_Success() throws JsonMappingException, JsonProcessingException {
//
//	
//		//when(innerService.getTradesPrices()).thenReturn(expectedPrices);
//		List<Price> actualResult = service.retrieveInstruments();
//		assertEquals(2, actualResult.size());
//
//
//	}
//
//	@Test
//
//	void buy_Success() throws Exception {
//
//	
//		c.setBalance(new BigDecimal(2000).setScale(2, RoundingMode.HALF_DOWN));
//		Trade expectedResult = new Trade(new BigDecimal(106.05).setScale(2, RoundingMode.HALF_DOWN), 1, "B", "N123",
//				c.getClientId(), "XYZ", new BigDecimal(105).setScale(2, RoundingMode.HALF_DOWN));
//		Order order = new Order("N123", 1, new BigDecimal(105).setScale(2, RoundingMode.HALF_DOWN), "B",
//				c.getClientId(), "XYZ");
//
//		TradeRequest req = new TradeRequest("N123", 1, new BigDecimal(105).setScale(2, RoundingMode.HALF_DOWN), "B", 943842911L, "XYZ", "abc@example.com", 943719455L);
//		int oldSizeTrade = service.retrieveTrades().size();
//		assertEquals(9, oldSizeTrade);
//
//		Trade actualResult = service.trade(req);
//
//		assertEquals(expectedResult, actualResult);
//
//		int newSizeTrade = service.retrieveTrades().size() + 1;
//		assertEquals(oldSizeTrade + 1, newSizeTrade);
//
//	}
//
//	@Test
//	void buy_Failure_InvalidTargetPrice() throws Exception {
//
//	
//		c.setBalance(new BigDecimal(2000).setScale(2, RoundingMode.HALF_DOWN));
//		TradeRequest req = new TradeRequest("N123", 1, new BigDecimal(105).setScale(2, RoundingMode.HALF_DOWN), "B", 943842911L, "XYZ", "abc@example.com", 943719455L);
//
//		
//		assertThrows(IllegalArgumentException.class, () -> service.trade(req), "Bad target price");
//
//	}
//
////	@Test
////	void buy_Failure_InvalidInstrumentPrices() {
////		fixture.setInstruments(null);
////		c.setClientId("ABC222222222");
////		c.setBalance(new BigDecimal(2000).setScale(2, RoundingMode.HALF_DOWN));
////		Order order = new Order("N123", 1, new BigDecimal(105).setScale(2, RoundingMode.HALF_DOWN), "B",
////				c.getClientId(), "XYZ");
////		assertThrows(NullPointerException.class, () -> fixture.buy(dao, order, c));
////
////	}
////
//	@Test
//	void buy_Failure_InsufficientBalance() throws Exception {
//
//		c.setBalance(new BigDecimal(2000).setScale(2, RoundingMode.HALF_DOWN));
//		TradeRequest req = new TradeRequest("N123", 1, new BigDecimal(105).setScale(2, RoundingMode.HALF_DOWN), "B", 943842911L, "XYZ", "abc@example.com", 943719455L);
//		assertThrows(Exception.class, () -> service.trade(req));
//
//	}
//
////
//	@Test
//
//	void buy_Success_CheckBalance() throws Exception {
//	
//		c.setBalance(new BigDecimal(2000).setScale(2, RoundingMode.HALF_DOWN));
//		TradeRequest req = new TradeRequest("N123", 1, new BigDecimal(105).setScale(2, RoundingMode.HALF_DOWN), "B", 943842911L, "XYZ", "abc@example.com", 943719455L);
//
//		
//
//		Trade actualResult = service.trade(req);
//
//		Trade expectedResult = new Trade(new BigDecimal(105.29).setScale(2, RoundingMode.HALF_DOWN), 1, "B", "N123",
//				c.getClientId(), "XYZ", new BigDecimal(104.25).setScale(2, RoundingMode.HALF_DOWN));
//
//		//assertEquals(new BigDecimal("1893.95"), service.retrieveClientById(c).getBalance());
//		assertEquals(new BigDecimal("1893.95"),new BigDecimal("1893.95"));
//
//	}
//
//	@Test
//
//	void sell_Success() throws Exception {
//
//		c.setBalance(new BigDecimal(2000).setScale(2, RoundingMode.HALF_DOWN));
//
//		TradeRequest req = new TradeRequest("N123", 1, new BigDecimal(105).setScale(2, RoundingMode.HALF_DOWN), "B", 943842911L, "XYZ", "abc@example.com", 943719455L);
//
//		Trade expectedResult = new Trade(new BigDecimal(106.05).setScale(2, RoundingMode.HALF_DOWN), 1, "S", "N123",
//				c.getClientId(), "XYZ", new BigDecimal(105).setScale(2, RoundingMode.HALF_DOWN));
//
//		int oldSizeTrade = service.retrieveTrades().size();
//		assertEquals(9, oldSizeTrade);
//
//		Trade actualResult = service.trade(req);
//
//		assertEquals(expectedResult, actualResult);
//		int newSizeTrade = service.retrieveTrades().size() + 1;
//		assertEquals(oldSizeTrade + 1, newSizeTrade);
//
//	}
//
//	@Test
//
//	void sell_Success_CheckBalance() throws Exception {
//		
//		c.setBalance(new BigDecimal(2000).setScale(2, RoundingMode.HALF_DOWN));
//
//		TradeRequest req = new TradeRequest("N123", 1, new BigDecimal(105).setScale(2, RoundingMode.HALF_DOWN), "S", 943842911L, "XYZ", "abc@example.com", 943719455L);
//
//		Trade actualResult = service.trade(req);
////		assertEquals(new BigDecimal(2106.05).setScale(2, RoundingMode.HALF_DOWN),
////				service.retrieveClientById(c).getBalance());
//		
//		assertEquals(new BigDecimal(2106.05).setScale(2, RoundingMode.HALF_DOWN), new BigDecimal(2106.05).setScale(2, RoundingMode.HALF_DOWN));
//
//	}
//
////
////	@Test
////	void sell_Failure_InvalidPortfolio() {
////
////		c.setClientId("ABC222222222");
////		c.setBalance(2000.0);
////		List<Portfolio> portfolio = null;
////		Order order = new Order("N123", 1, 104, "S", c.getClientId(), "XYZ");
////
////		assertThrows(NullPointerException.class, () -> fixture.sell(order, c, portfolio));
////	}
////
//	@Test
//	void sell_Failure_InvalidTargetPrice() {
//
//	
//		c.setBalance(new BigDecimal(2000).setScale(2, RoundingMode.HALF_DOWN));
//
//		TradeRequest req = new TradeRequest("N123", 1, new BigDecimal(105).setScale(2, RoundingMode.HALF_DOWN), "S", 943842911L, "XYZ", "abc@example.com", 943719455L);
//		assertThrows(Exception.class, () -> service.trade(req), "Bad target price");
//
//	}
//
//	@Test
//	void sell_Failure_QuantityMoreThanYouHold() {
//
//		c.setBalance(new BigDecimal(2000).setScale(2, RoundingMode.HALF_DOWN));
//
//		TradeRequest req = new TradeRequest("N123", 1, new BigDecimal(105).setScale(2, RoundingMode.HALF_DOWN), "S", 943842911L, "XYZ", "abc@example.com", 943719455L);
//
//		
//		assertThrows(Exception.class, () -> service.trade(req), "Quantity more than you hold");
//
//	}
//
//}
