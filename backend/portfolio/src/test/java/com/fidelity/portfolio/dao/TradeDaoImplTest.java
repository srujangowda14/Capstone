package com.fidelity.portfolio.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import com.fidelity.portfolio.Client;
import com.fidelity.portfolio.ClientIdentification;
import com.fidelity.portfolio.Order;
import com.fidelity.portfolio.Portfolio;
import com.fidelity.portfolio.Price;
import com.fidelity.portfolio.Trade;
import com.fidelity.portfolio.TradeRequest;

@ExtendWith(SpringExtension.class)
@ContextConfiguration("classpath:beans.xml")
@Transactional
class TradeDaoImplTest {

	List<Price> instrumentPrices;
	Client c;
	List<Portfolio> portfolios;

	@Autowired
	TradeDao fixture;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {

		Set<ClientIdentification> identifications = new HashSet<>();
		ClientIdentification id = new ClientIdentification("SSN", "2222222");
		identifications.add(id);

		c = new Client("abc@gmail.com", "50997", "ABC", LocalDate.now().minusYears(20), "USA", identifications);

		// setting up current client's portfolio
		Portfolio portfolio1 = new Portfolio("N123", "JP Morgan",
				new BigDecimal(104.25).setScale(2, RoundingMode.HALF_DOWN),
				new BigDecimal(105.29).setScale(2, RoundingMode.HALF_DOWN), 1, 943842911);
		portfolios = new ArrayList<>();
		portfolios.add(portfolio1);

		MockitoAnnotations.openMocks(this);
//
//		TradeDao testDao = new TradeDaoImpl();
//		fixture = new TradeService();
//		ReflectionTestUtils.setField(fixture, "dao", testDao);

	}

	@AfterEach
	void tearDown() throws Exception {
		fixture = null;
		c = null;

	}

//	@Test
//	void testRetreiveInstruments_Success() {
//
//		List<Price> expectedPrices = List.of(
//				new Price(new BigDecimal(100), "N123", new BigDecimal(105), LocalDateTime.of(2023, 9, 19, 14, 30, 0)));
//
//		List<Price> actualResult = fixture.getInstrumentPrices();
//		assertEquals(1, actualResult.size());
//
//		assertEquals(expectedPrices, actualResult);
//
//	}

//	@Test
//
//	void buy_Success() throws Exception {
//
//		c.setClientId(943842911);
//		c.setBalance(new BigDecimal(2000).setScale(2, RoundingMode.HALF_DOWN));
//		Trade expectedResult = new Trade(new BigDecimal(106.05).setScale(2, RoundingMode.HALF_DOWN), 1, "B", "N123",
//				c.getClientId(), "XYZ", new BigDecimal(105).setScale(2, RoundingMode.HALF_DOWN));
//		Order order = new Order("N123", 1, new BigDecimal(105).setScale(2, RoundingMode.HALF_DOWN), "B",
//				c.getClientId(), "XYZ");
//
//		int oldSizeTrade = fixture.getAllTrades().size();
//		assertEquals(1, oldSizeTrade);
//
//		Trade actualResult = fixture.buy(order, c);
//
//		assertEquals(expectedResult, actualResult);
//
//		int newSizeTrade = fixture.getAllTrades().size();
//		assertEquals(oldSizeTrade + 1, newSizeTrade);
//
//	}

//	@Test
//	void buy_Failure_InvalidTargetPrice() {
//
//		c.setClientId(943842911);
//		c.setBalance(new BigDecimal(2000).setScale(2, RoundingMode.HALF_DOWN));
//		Order order = new Order("N123", 1, new BigDecimal(90).setScale(2, RoundingMode.HALF_DOWN), "B", c.getClientId(),
//				"XYZ");
//
//		assertThrows(IllegalArgumentException.class, () -> fixture.buy(order, c), "Bad target price");
//
//	}

//	@Test
//	void buy_Failure_InvalidInstrumentPrices() {
//		fixture.setInstruments(null);
//		c.setClientId("ABC222222222");
//		c.setBalance(new BigDecimal(2000).setScale(2, RoundingMode.HALF_DOWN));
//		Order order = new Order("N123", 1, new BigDecimal(105).setScale(2, RoundingMode.HALF_DOWN), "B",
//				c.getClientId(), "XYZ");
//		assertThrows(NullPointerException.class, () -> fixture.buy(dao, order, c));
//
//	}
//
//	@Test
//	void buy_Failure_InsufficientBalance() {
//
//		c.setClientId(943842911);
//		c.setBalance(new BigDecimal(2000).setScale(2, RoundingMode.HALF_DOWN));
//		Order order = new Order("N123", 1, new BigDecimal(90).setScale(2, RoundingMode.HALF_DOWN), "B", c.getClientId(),
//				"XYZ");
//		assertThrows(Exception.class, () -> fixture.buy(order, c));
//
//	}

////
//	@Test
//
//	void buy_Success_CheckBalance() throws Exception {
//		c.setClientId(943842911);
//		c.setBalance(new BigDecimal(2000).setScale(2, RoundingMode.HALF_DOWN));
//		TradeRequest req = new TradeRequest("N123", 1, new BigDecimal(105).setScale(2, RoundingMode.HALF_DOWN), "B", 943842911L, "XYZ", "abc@example.com", 943719455L);
//		
//		Order order = new Order("N123", 1, new BigDecimal(105).setScale(2, RoundingMode.HALF_DOWN), "B",
//				c.getClientId(), "XYZ");
//
//		Trade actualResult = fixture.buy(req);
//
//		Trade expectedResult = new Trade(new BigDecimal(105.29).setScale(2, RoundingMode.HALF_DOWN), 1, "B", "N123",
//				c.getClientId(), "XYZ", new BigDecimal(104.25).setScale(2, RoundingMode.HALF_DOWN));
//
//		assertEquals(new BigDecimal("1893.95"), fixture.getClientById(c).getBalance());
//
//	}
//
//	@Test
//
//	void sell_Success() throws Exception {
//
//		c.setClientId(943842911);
//		c.setBalance(new BigDecimal(2000).setScale(2, RoundingMode.HALF_DOWN));
//
//		int oldSizeTrade = fixture.getAllTrades().size();
//		assertEquals(1, oldSizeTrade);
//
//		Order order = new Order("N123", 1, new BigDecimal(100).setScale(2, RoundingMode.HALF_DOWN), "S",
//				c.getClientId(), "XYZ");
//
//		Trade actualResult = fixture.sell(order, c, portfolios);
//
//		Trade expectedResult = new Trade(new BigDecimal(106.05).setScale(2, RoundingMode.HALF_DOWN), 1, "S", "N123",
//				c.getClientId(), "XYZ", new BigDecimal(105).setScale(2, RoundingMode.HALF_DOWN));
//
//		assertEquals(expectedResult, actualResult);
//		int newSizeTrade = fixture.getAllTrades().size();
//		assertEquals(oldSizeTrade + 1, newSizeTrade);
//
//	}
//
//	@Test
//
//	void sell_Success_CheckBalance() throws Exception {
//		c.setClientId(943842911);
//		c.setBalance(new BigDecimal(2000).setScale(2, RoundingMode.HALF_DOWN));
//
//		Order order = new Order("N123", 1, new BigDecimal(100).setScale(2, RoundingMode.HALF_DOWN), "S",
//				c.getClientId(), "XYZ");
//
//		Trade actualResult = fixture.sell(order, c, portfolios);
//		assertEquals(new BigDecimal(2106.05).setScale(2, RoundingMode.HALF_DOWN),
//				fixture.getClientById(c).getBalance());
//	}
//
//	@Test
//	void sell_Failure_InvalidTargetPrice() {
//
//		c.setClientId(943842911);
//		c.setBalance(new BigDecimal(2000).setScale(2, RoundingMode.HALF_DOWN));
//
//		Order order = new Order("N123", 2, new BigDecimal(90).setScale(2, RoundingMode.HALF_DOWN), "S", c.getClientId(),
//				"XYZ");
//
//		assertThrows(Exception.class, () -> fixture.sell(order, c, portfolios), "Bad target price");
//
//	}
//
//	@Test
//	void sell_Failure_QuantityMoreThanYouHold() {
//
//		c.setClientId(943842911);
//		c.setBalance(new BigDecimal(2000).setScale(2, RoundingMode.HALF_DOWN));
//
//		Order order = new Order("N123", 2, new BigDecimal(100).setScale(2, RoundingMode.HALF_DOWN), "S",
//				c.getClientId(), "XYZ");
//
//		assertThrows(Exception.class, () -> fixture.sell(order), "Quantity more than you hold");
//
//	}

}
