package com.fidelity.portfolio.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.jdbc.JdbcTestUtils.countRowsInTable;
import static org.springframework.test.jdbc.JdbcTestUtils.countRowsInTableWhere;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import com.fidelity.portfolio.Portfolio;
import com.fidelity.portfolio.Trade;
import com.fidelity.portfolio.dao.PortfolioDao;

@ExtendWith(SpringExtension.class)
@ContextConfiguration("classpath:beans.xml")
@Transactional
class PortfolioServiceTest {

	@Mock
	private PortfolioDao mockDao;
	
	@InjectMocks
	private PortfolioService service;
	
	private Trade trade;
	List<Portfolio> allPortfolio;
	
	@BeforeEach
	void setUp() throws Exception {
        allPortfolio = new ArrayList<Portfolio>();
		MockitoAnnotations.openMocks(this);

	}

	@Test
	void verifyObjectInstantiation() {
		assertNotNull(service);
	}
	
	@Test
	void verifyGetPortfolio1() {
		Portfolio portfolio1 =  new Portfolio("N123456", "JPMorgan Chase Capital Stock", new BigDecimal(104.75), new BigDecimal(628.50), 6, 943842911);
		Portfolio portfolio2 = new Portfolio("N234", "Apple", new BigDecimal(104.75), new BigDecimal(628.50), 6, 943842911);
		allPortfolio.add(portfolio1);
		allPortfolio.add(portfolio2);
		Mockito.when(mockDao.retreivePortfolio(943842911)).thenReturn(allPortfolio);
		assertEquals(2,service.getPortfolio(943842911).size());
		
	}

	
	@Test
	void verifyGetPortfolioThatIsEmpty() {
		Mockito.when(mockDao.retreivePortfolio(94384291)).thenReturn(null);
		assertThrows(NullPointerException.class,()->{
			service.getPortfolio(943842911);
		});
	}
	
	
	@Test
	void verifyUpdatePortfolioForNewClient() {
		trade = new Trade(new BigDecimal(900.00), 3, "B", "N123456", "JP Morgan Chase Capital Stock", 943842911, "XCVVFGH123", new BigDecimal(300.00));
		Mockito.when(mockDao.retreivePortfolio(94384291)).thenReturn(allPortfolio);
		Mockito.when(mockDao.getInstrumentDescription("N123456")).thenReturn("JP Morgan Chase Capital Stock");
		Mockito.when(mockDao.insertPortfolio(trade, "JP Morgan Chase Capital Stock")).thenReturn(true);
		assertTrue(service.updatePortfolio(trade));
	}
	
	@Test
	void verifyUpdatePortfolioForExistingClientWithNewInstrument() {
		trade = new Trade(new BigDecimal(900.00), 3, "B", "N123456","JP Morgan Chase Capital Stock",943842911, "XCVVFGH123", new BigDecimal(300.00));
		Portfolio portfolio1 =  new Portfolio("N123456", "JPMorgan Chase Capital Stock", new BigDecimal(104.75), new BigDecimal(628.50), 6, 943842911);
		allPortfolio.add(portfolio1);
		Mockito.when(mockDao.retreivePortfolio(943842911)).thenReturn(allPortfolio);
		Mockito.when(mockDao.getInstrumentDescription("N123456")).thenReturn("JP Morgan Chase Capital Stock");
		List<Portfolio> emptyPortfolio = new ArrayList<Portfolio>();
		Mockito.when(mockDao.getPortfolioParticularInstrument(trade)).thenReturn(emptyPortfolio);
		Mockito.when(mockDao.insertPortfolio(trade, "JP Morgan Chase Capital Stock")).thenReturn(true);
		assertTrue(service.updatePortfolio(trade));
	}
	
	@Test
	void verifyUpdatePortfolioForExisitingClientWithExistingInstrumentBuy() {
		trade = new Trade(new BigDecimal(900.00), 3, "B", "N234", "JP Morgan Chase Capital Stock",943842911, "XCVVFGH123", new BigDecimal(300.00));
		Portfolio portfolio1 =  new Portfolio("N123456", "JPMorgan Chase Capital Stock", new BigDecimal(104.75), new BigDecimal(628.50), 6, 943842911);
		allPortfolio.add(portfolio1);
		Mockito.when(mockDao.retreivePortfolio(943842911)).thenReturn(allPortfolio);
		Mockito.when(mockDao.getInstrumentDescription("N123456")).thenReturn("JP Morgan Chase Capital Stock");
		Mockito.when(mockDao.getPortfolioParticularInstrument(trade)).thenReturn(allPortfolio);
		Mockito.when(mockDao.updatePortfolio(portfolio1)).thenReturn(true);
		assertTrue(service.updatePortfolio(trade));
		
	}
	
	@Test
	void verifyUpdatePortfolioForExisitingClientWithExistingInstrumentSell() {
		trade = new Trade(new BigDecimal(300.00), 1, "S", "N234", "JP Morgan Chase Capital Stock",943842911, "XCVVFGH123", new BigDecimal(300.00));
		Portfolio portfolio1 =  new Portfolio("N123456", "JPMorgan Chase Capital Stock", new BigDecimal(104.75), new BigDecimal(628.50), 6, 943842911);
		allPortfolio.add(portfolio1);
		Mockito.when(mockDao.retreivePortfolio(943842911)).thenReturn(allPortfolio);
		Mockito.when(mockDao.getInstrumentDescription("N123456")).thenReturn("JP Morgan Chase Capital Stock");
		Mockito.when(mockDao.getPortfolioParticularInstrument(trade)).thenReturn(allPortfolio);
		Mockito.when(mockDao.updatePortfolio(portfolio1)).thenReturn(true);
		assertTrue(service.updatePortfolio(trade));
	}
	
	@Test
	void verifyUpdatePortfolioForExisitingClientWithExistingInstrumentSellAllQuantity() {
		trade = new Trade(new BigDecimal(300.00), 8, "S", "N234", "JP Morgan Chase Capital Stock", 943842911, "XCVVFGH123", new BigDecimal(2400.00));
		Portfolio portfolio1 =  new Portfolio("N234", "JPMorgan Chase Capital Stock", new BigDecimal(104.75), new BigDecimal(628.50), 8, 943842911);
		allPortfolio.add(portfolio1);
		Mockito.when(mockDao.retreivePortfolio(943842911)).thenReturn(allPortfolio);
		Mockito.when(mockDao.getInstrumentDescription("N123456")).thenReturn("JP Morgan Chase Capital Stock");
		Mockito.when(mockDao.getPortfolioParticularInstrument(trade)).thenReturn(allPortfolio);
		Mockito.when(mockDao.deletePortfolio(portfolio1)).thenReturn(true);
		assertTrue(service.updatePortfolio(trade));
		assertTrue(service.updatePortfolio(trade));
	}
	

}
