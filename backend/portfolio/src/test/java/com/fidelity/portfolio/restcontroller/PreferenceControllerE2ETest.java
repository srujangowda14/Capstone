package com.fidelity.portfolio.restcontroller;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.jdbc.JdbcTestUtils.countRowsInTable;
import static org.springframework.test.jdbc.JdbcTestUtils.countRowsInTableWhere;

import java.net.URI;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;


import com.fidelity.portfolio.InvestmentPreference;


@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)

@Transactional
class PreferenceControllerE2ETest {

	@Autowired
	private TestRestTemplate restTemplate;

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Test
	public void testQueryForAll() {
		
		int shipCount = countRowsInTable(jdbcTemplate, "preferences");
		
		String request = "/preferences/prefs";

		ResponseEntity<InvestmentPreference[]> response = 
				restTemplate.getForEntity(request, InvestmentPreference[].class);
	
		
		assertEquals(HttpStatus.OK, response.getStatusCode());
		
		
		InvestmentPreference[] responseShip = response.getBody();
		assertEquals(shipCount, responseShip.length); 
		
		
	}
	
	@Test
	public void testQueryForWidgetById() {
		InvestmentPreference widget =new InvestmentPreference(100,"for marriage"," above average","60001-80000","7-10 years");
		String requestUrl = "/preferences/100";

		ResponseEntity<InvestmentPreference> response = 
			restTemplate.getForEntity(requestUrl, InvestmentPreference.class);
		
		// verify the response HTTP status
		assertEquals(HttpStatus.OK, response.getStatusCode());
		
		// verify that the service returned the correct Widget
		InvestmentPreference responseBody = response.getBody();
		assertEquals(widget, responseBody);
	}
	
	@Test
	public void testQueryForWidgetById_NotPresent() {
		String requestUrl = "/preferences/90";

		ResponseEntity<InvestmentPreference> response = 
				restTemplate.getForEntity(requestUrl, InvestmentPreference.class);
		
		assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
	}
	
	@Test
	public void testAddWidgetToWarehouse() throws Exception {
		int oldRowCount = countRowsInTable(jdbcTemplate, "preferences");
		long id = 127;
		InvestmentPreference newWidget =new InvestmentPreference(id,"for house"," above average","60001-80000","7-10 years");

		// ACT
		String requestUrl = "/preferences/prefs";
		
		ResponseEntity<DatabaseRequestResult> response = 
			restTemplate.postForEntity(requestUrl, newWidget, 
									   DatabaseRequestResult.class);
	
		assertEquals(HttpStatus.OK, response.getStatusCode());
		DatabaseRequestResult responseBody = response.getBody(); // {"rowCount": 1}
		assertEquals(1, responseBody.getRowCount());

		int newRowCount = countRowsInTable(jdbcTemplate, "preferences");
		assertEquals(oldRowCount + 1, newRowCount);

	
	}
	
	@Test
	public void testUpdateWidgetInWarehouse() throws Exception {
		// ARRANGE
		int oldWidgetCount = countRowsInTable(jdbcTemplate, "preferences");
		long id = 100;
		InvestmentPreference updatedWidget =new InvestmentPreference(id,"for marriage"," above average","60001-80000","7-10 years");
		String requestUrl = "/preferences/prefs";
		RequestEntity<InvestmentPreference> requestEntity = 
			RequestEntity.put(new URI(requestUrl)) // returns RequestEntity.BodyBuilder
						 .contentType(MediaType.APPLICATION_JSON) 
						 .accept(MediaType.APPLICATION_JSON)
						 .body(updatedWidget);

		ResponseEntity<DatabaseRequestResult> response = 
			restTemplate.exchange(requestEntity, DatabaseRequestResult.class);

		// ASSERT
		// verify the response HTTP status and response body
		assertEquals(HttpStatus.OK, response.getStatusCode());
		DatabaseRequestResult responseDto = response.getBody(); // {"rowCount": 1}
		assertEquals(1, responseDto.getRowCount());

		// verify that no rows were added to the Widgets table
		int newWidgetCount = countRowsInTable(jdbcTemplate, "preferences");
		assertEquals(oldWidgetCount, newWidgetCount);

	
	}

}
