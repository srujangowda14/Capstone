package com.fidelity.portfolio.services;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.fidelity.portfolio.Report;

class ReportTest {

	private Report report;
	@BeforeEach
	void setUp() throws Exception {
		report = new Report();
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void verifyObjectInstantiation() {
		assertNotNull(report);
	}
	
	@Test
	void verifyInstrumentId() {
		assertThrows(IllegalArgumentException.class,()->{
			report.setInstrumentId("");
		});	
	}
	
	@Test
	void verifyTradeId() {
		assertThrows(IllegalArgumentException.class,()->{
			report.setTradeId("");
		});	
	}
	
	@Test
	void verifyQuantityWithZero() {
		assertThrows(IllegalArgumentException.class,()->{
			report.setQuantity(0);
		});
	}
	
	@Test
	void verifyQuantityWithNegative() {
		assertThrows(IllegalArgumentException.class,()->{
			report.setQuantity(-1);
		});
	}
	
	@Test
	void verifyPriceWithZero() {
		assertThrows(IllegalArgumentException.class,()->{
			report.setPrice(new BigDecimal(0));
		});
	}
	
	@Test
	void verifyPriceWithNegative() {
		assertThrows(IllegalArgumentException.class,()->{
			report.setPrice(new BigDecimal(-1));
		});
	}
	
	@Test
	void verifyCashValueWithZero() {
		assertThrows(IllegalArgumentException.class,()->{
			report.setCashValue(new BigDecimal(0));
		});
	}
	
	@Test
	void verifyCashValueWithNegative() {
		assertThrows(IllegalArgumentException.class,()->{
			report.setCashValue(new BigDecimal(-1));
		});
	}

}
