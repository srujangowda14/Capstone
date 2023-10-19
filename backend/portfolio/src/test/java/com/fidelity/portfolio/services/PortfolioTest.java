package com.fidelity.portfolio.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.fidelity.portfolio.Portfolio;

class PortfolioTest {

	private Portfolio portfolio;
	
	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
		portfolio = null;
	}

	@Test
	void verifyPortfolioObjectCreation() {
		portfolio = new Portfolio("N123456", "JPMorgan Chase & Co. Capital Stock", new BigDecimal(104.75), new BigDecimal(213.109), 2, 943842911);
		assertNotNull(portfolio);
	}
	
	@Test
	void verifyConstructorInstantiation() {
		portfolio = new Portfolio("T67890", "USA, Note 3.125 15nov2028 10Y", new BigDecimal(1.03375), new BigDecimal(104.40875), 100, 943842911);
		assertEquals(portfolio, new Portfolio("T67890", "USA, Note 3.125 15nov2028 10Y", new BigDecimal(1.03375), new BigDecimal(104.40875), 100, 943842911));
	}
	
	@Test
	void verifyEmptyInstrumentId() {
		assertThrows(IllegalArgumentException.class, ()->{
			portfolio = new Portfolio("", "JPMorgan Chase & Co. Capital Stock", new BigDecimal(104.75), new BigDecimal(213.109), 2, 943842911);
		});
	}
	
	@Test
	void verifyEmptyInstrumentDescription() {
		assertThrows(IllegalArgumentException.class, ()->{
			portfolio = new Portfolio("N123456", "", new BigDecimal(104.75), new BigDecimal(213.109), 2, 943842911);
		});
	}
	
	@Test
	void verifyImproperBoughtPrice1(){
		assertThrows(IllegalArgumentException.class, ()->{
			portfolio = new Portfolio("T67890", "USA, Note 3.125 15nov2028 10Y", new BigDecimal(0), new BigDecimal(104.40875), 100, 943842911);
		});
	}
	
	@Test
	void verifyImproperBoughtPrice2() {
		assertThrows(IllegalArgumentException.class, ()->{
			portfolio = new Portfolio("T67890", "USA, Note 3.125 15nov2028 10Y", new BigDecimal(-20.03), new BigDecimal(104.40875), 100, 943842911);
		});
	}
	
	@Test
	void verifyImproperTotalInvestment1() {
		assertThrows(IllegalArgumentException.class, ()->{
			portfolio = new Portfolio("N123456", "JPMorgan Chase & Co. Capital Stock", new BigDecimal(104.75), new BigDecimal(0.00), 2, 943842911);
		});
	}
	
	@Test
	void verifyImproperTotalInvestment2() {
		assertThrows(IllegalArgumentException.class, ()->{
			portfolio = new Portfolio("N123456", "JPMorgan Chase & Co. Capital Stock", new BigDecimal(104.75), new BigDecimal(-1.22), 2, 943842911);
		});
	}
	
	@Test
	void verifyImproperQuantity1() {
		assertThrows(IllegalArgumentException.class, ()->{
			portfolio = new Portfolio("T67890", "USA, Note 3.125 15nov2028 10Y", new BigDecimal(1.03375), new BigDecimal(104.40875), 0, 943842911);
		});
	}
	
	@Test
	void verifyImproperQuantity2() {
		assertThrows(IllegalArgumentException.class, ()->{
			portfolio = new Portfolio("T67890", "USA, Note 3.125 15nov2028 10Y", new BigDecimal(1.03375), new BigDecimal(104.40875), -1, 943842911);
		});
	}
	
	@Test
	void verifyImproperClientIdFormat(){
		assertThrows(IllegalArgumentException.class, ()->{
			portfolio = new Portfolio("N123456", "JPMorgan Chase & Co. Capital Stock", new BigDecimal(104.75), new BigDecimal(213.109), 2, 943842911);
		});
	}
	
	@Test
	void verifyImproperClientIdLength() {
		assertThrows(IllegalArgumentException.class, ()->{
			portfolio = new Portfolio("N123456", "JPMorgan Chase & Co. Capital Stock", new BigDecimal(104.75), new BigDecimal(213.109), 2, 943842911);
		});
	}
	

}
