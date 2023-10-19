//package com.fidelity.portfolio.services;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//import java.time.LocalDateTime;
//import java.time.format.DateTimeFormatter;
//import java.util.ArrayList;
//import java.util.Collections;
//import java.util.Comparator;
//
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import com.fidelity.portfolio.Orders;
//import com.fidelity.portfolio.TradeHistory;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertFalse;
//import static org.junit.jupiter.api.Assertions.assertThrows;
//import static org.junit.jupiter.api.Assertions.assertTrue;
//
//import java.time.LocalDate;
//import java.util.HashSet;
//import java.util.List;
//import java.util.Set;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.mockito.MockitoAnnotations;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
////import org.springframework.transaction.TransactionManager;
//import org.springframework.transaction.annotation.Transactional;
//
//import com.fidelity.portfolio.Client;
//import com.fidelity.portfolio.ClientIdentification;
//import com.fidelity.portfolio.dao.ClientDao;
//import com.fidelity.portfolio.dao.TradeHistoryDao;
//import com.fidelity.portfolio.exceptions.DatabaseException;
//
//
//
//class TransactionServicePojoTest {
//	
//	
//	@Mock
//	TradeHistoryDao mockDao;
////
//	@InjectMocks
//	Transaction fixture;
//
//	@BeforeEach
//	void setUp() throws Exception {
//
//		MockitoAnnotations.openMocks(this);
//		
//
//	}
//
//	@AfterEach
//	void tearDown() throws Exception {
//	}
//
//	@Test
//	void testInvalidClient() {
//		assertThrows(IllegalArgumentException.class,()-> fixture.getTransactions(943942911),"Client id should be 9 digit");
//	}
//
//	@Test
//	void testIfTransactionsAreReturned() {
//		Mockito.when(mockDao.getTradeHistory(943842911)).thenReturn(List.of(new TradeHistory()));
//		assertNotNull(fixture.getTransactions(943842911));
//	}
//
//	@Test
//	void testForEmptyClientRecord() {
//		Mockito.when(mockDao.getTradeHistory(943842911)).thenReturn(null);
//		assertTrue(fixture.getTransactions(943842911).size()==0);
//	}
//	
//	@Test
//	void testForLessThan100Records() {
//		Mockito.when(mockDao.getTradeHistory(943842911)).thenReturn(List.of(new TradeHistory()));
//		assertTrue(fixture.getTransactions(943842911).size()<100);
//	}
//	
//	@Test
//	void testForExact1Records() {
//		Mockito.when(mockDao.getTradeHistory(943842911)).thenReturn(List.of(new TradeHistory()));
//		assertTrue(fixture.getTransactions(943842911).size()==1);
//	}
//	
//	@Test
//	void testForMoreThan0Records() {
//		Mockito.when(mockDao.getTradeHistory(943842911)).thenReturn(List.of(new TradeHistory()));
//		assertTrue(fixture.getTransactions(943842911).size()>0);
//	}
//	
////	@Test
////	void testIfCorrectlySorted() {
////		List<TradeHistory> temp = fixture.getTransactions("513275530");
////		
////		Collections.sort(temp, new Comparator<Orders>() {
////      	  public int compare(Orders o1, Orders o2) {
////      		  DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
////
////      	        LocalDateTime localDateTime1 = LocalDateTime.parse(o1.date, inputFormatter);
////      	        LocalDateTime localDateTime2 = LocalDateTime.parse(o2.date, inputFormatter);
////      	        return localDateTime2.compareTo(localDateTime1);
////      	        
////      	
////      	  }
////      	});
////		List<TradeHistory> temp2 = fixture.getTransactions("513275530");
////		
////		for (int i = 0; i < temp.size(); i++) {
////		    String date1 = temp.get(i).
////		    String date2 = temp2.get(i).date;
////		    assertEquals(date1.trim(),date2.trim());
////		    
////		}
////
////	
////	}
//
//}
