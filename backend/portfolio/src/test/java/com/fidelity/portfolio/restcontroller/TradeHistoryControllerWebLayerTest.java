package com.fidelity.portfolio.restcontroller;
import com.fidelity.portfolio.services.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import com.fidelity.portfolio.*;

@WebMvcTest(controllers=TradeHistoryController.class)
class TradeHistoryControllerWebLayerTest {
	
	
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private Transaction service;
	
	private static List<TradeHistory> history;


	@BeforeAll
	public static void init() {
		history = Arrays.asList(
				new TradeHistory(BigDecimal.valueOf(1234), 2, "B", "N234", 943842911, "111", BigDecimal.valueOf(3), null));
			
			
	}


	@Test
	public void testQueryForHistoryById() throws Exception {
		long client_id = 1;
		when(service.getTransactions(client_id)).thenReturn(history);
		
		mockMvc.perform(get("/history/1"))
			   .andDo(print())
			   .andExpect(status().isOk());
			  		
	}
}
