package com.fidelity.portfolio.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import com.fidelity.portfolio.Portfolio;
import com.fidelity.portfolio.Trade;

@ExtendWith(SpringExtension.class)
@ContextConfiguration("classpath:beans.xml")
@Transactional
class PortfolioDaoImplTest {

	@Autowired
	private PortfolioDao dao;
	
	private Trade trade;
	
	@Test
	void verifyObjectInstantiation() {
		assertNotNull(dao);
	}
	
	@Test
	void verifyGetPortfolio1() {
		assertEquals(2,dao.retreivePortfolio(943842911).size());
		
	}
	
	@Test
	void testInsertPortfolio() {
		trade = new Trade(new BigDecimal(900.00), 3, "B", "N123456", "JP Morgan Chase & Co",943842911, "XCVVFGH123", new BigDecimal(300.00));
		assertTrue(dao.insertPortfolio(trade, "JPMorgan Chase Capital Stock"));
	}
	
	@Test
	void testUpdatePortfolio() {
		Portfolio portfolio = new Portfolio("N123456", "JPMorgan Chase Capital Stock", new BigDecimal(104.75), new BigDecimal(628.50), 6, 943842911);
		assertTrue(dao.updatePortfolio(portfolio));
	}
	
	
	@Test
	void testDeletePortfolio() {
		Portfolio portfolio = new Portfolio("N123456", "JPMorgan Chase Capital Stock", new BigDecimal(104.75), new BigDecimal(628.50), 6, 943842911);
		assertTrue(dao.deletePortfolio(portfolio));
	}

}
