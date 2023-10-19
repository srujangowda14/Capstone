//package com.fidelity.portfolio.dao;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.springframework.test.jdbc.JdbcTestUtils.countRowsInTable;
//import static org.springframework.test.jdbc.JdbcTestUtils.countRowsInTableWhere;
//
//import java.sql.SQLException;
//import java.util.List;
//
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.jdbc.core.JdbcTemplate;
//
//import org.springframework.test.context.ContextConfiguration;
//
//import org.springframework.transaction.annotation.Transactional;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//
//import com.fidelity.portfolio.InvestmentPreference;
//import com.fidelity.portfolio.exceptions.DatabaseException;
//import com.fidelity.portfolio.services.PreferenceService;
//
//@SpringBootTest
//@Transactional
//class PreferenceDaoOracleTest {
//
//	@Autowired
//	private PreferenceService dao;
//
//	@Test
//	void testGetAllPreferences() {
//       
//		assertNotNull(dao.getAll());
//
//	}
//
//	@Test
//	void insertPreferenceTest() {
//		String id = "345";
//		InvestmentPreference newClient = new InvestmentPreference(id, "for house", "aggresive", "150000+", "5-7 years");
//
//		 dao.addClientInvestmentPrefernce(newClient);
//		
//
//	}
//
//	@Test
//	void updatePreferenceTest() throws SQLException {
//
//		InvestmentPreference newClient = new InvestmentPreference("123", "for house", "aggresive", "150000+",
//				"5-7 years");
//
//	 dao.updateClientInvestmentPreference(newClient);
//		
//
//	}
//
//	@Test
//	void insertTestThrowsException() {
//		InvestmentPreference newClient = new InvestmentPreference("123", "for house", "aggresive", "150000+", null);
//		assertThrows(NullPointerException.class, () -> dao.addClientInvestmentPrefernce(newClient), "empty field");
//	}
//
//	@Test
//	void updateTestThrowsException() {
//		InvestmentPreference newClient = new InvestmentPreference("123", "for house", "aggresive", "150000+", null);
//		assertThrows(NullPointerException.class, () -> dao.updateClientInvestmentPreference(newClient), "empty field");
//	}
//	
//
//
//}
