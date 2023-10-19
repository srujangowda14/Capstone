package com.fidelity.portfolio.dao;

import static org.junit.jupiter.api.Assertions.*;

import com.fidelity.portfolio.exceptions.*;
import com.fidelity.portfolio.services.Transaction;

import java.sql.Connection;
import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.jdbc.JdbcTestUtils;
import org.springframework.transaction.annotation.Transactional;

import com.fidelity.portfolio.Client;
import com.fidelity.portfolio.Trade;
import com.fidelity.portfolio.TradeHistory;
import com.fidelity.portfolio.dao.*;


@Transactional
@SpringBootTest
class TradeHistoryImplTest {
	
	
	
	@Autowired
	TradeHistoryImpl dao;

	

	@AfterEach
	void tearDown() throws Exception {
		

	}

	@Test
    void testqueryTradeHistory() {

		List<TradeHistory> res = dao.getTradeHistory(943842911);
		System.out.println("fvdvdsvfd");
		for(TradeHistory i : res) {
			System.out.println(i.getInstrument_id());
		}
	
		assertEquals(res.size(),1);
    }
	
	
	
	
	
	
	
	
}
