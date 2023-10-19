package com.fidelity.portfolio.restcontroller;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.jdbc.JdbcTestUtils.countRowsInTable;
import static org.springframework.test.jdbc.JdbcTestUtils.countRowsInTableWhere;
import static org.springframework.test.jdbc.JdbcTestUtils.deleteFromTables;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.fidelity.portfolio.*;


@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
class TradeHistoryContollerE2ETest {

	@Autowired
	private TestRestTemplate restTemplate;

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Test
	public void testQueryForHistoryById() {
		String request = "/history/943842911";

		ResponseEntity<TradeHistory[]> response = 
				restTemplate.getForEntity(request, TradeHistory[].class);
		
		// verify the response HTTP status
		assertEquals(HttpStatus.OK, response.getStatusCode());
		
		TradeHistory[] abcd = new TradeHistory[1];
		
		abcd[0]=(new TradeHistory(BigDecimal.valueOf(1234), 2, "B", "N234", 943842911, "111", BigDecimal.valueOf(3), null));
		System.out.println("Aooooooooooooooooooo"+response.getBody()[0].getClient_id());
		assertTrue(abcd[0].getClient_id()==(response.getBody()[0].getClient_id()));
	}
	
	@Test
	public void testQueryForHistory_NoHistoryInDb() {
		// delete all rows from the Widgets table
		
		String request = "/history/943842914";
		ResponseEntity<TradeHistory[]> response = 
				restTemplate.getForEntity(request, TradeHistory[].class);
		
		// verify the response HTTP status is 204 (NO_CONTENT)
		assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
	}

}
