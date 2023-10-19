package com.fidelity.portfolio.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.dao.DuplicateKeyException;


import com.fidelity.portfolio.InvestmentPreference;
import com.fidelity.portfolio.dao.PreferenceDao;
import com.fidelity.portfolio.exceptions.DatabaseException;

class PreferenceServicePojoTest {

	@Mock
	private PreferenceDao mockDao;

	
	@InjectMocks
	private PreferenceService service;

	
	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}
	
	
	
	
	@Test
	void testFindAllPreferences() {
		List<InvestmentPreference> widgets = Arrays.asList(new InvestmentPreference(123,"for education","average","0-20000","0-5 years"),
				new InvestmentPreference(126,"for marriage","average","60001-80000","7-10 years"));

		when(mockDao.getAllPrefs()).thenReturn(widgets);

		List<InvestmentPreference> allWidgets = service.getAll();

		assertEquals(widgets, allWidgets);
	}
	@Test
	void testFindAllPreferences_DaoReturnsEmptyList() {
		when(mockDao.getAllPrefs())
    	.thenReturn(new ArrayList<>());
	
	List<InvestmentPreference> allWidgets = service.getAll();

	
	assertEquals(0, allWidgets.size());}
	
	@Test
	void testFindPreferenceById() {
		long id = 126;
		InvestmentPreference firstWidget =new InvestmentPreference(126,"for marriage","average","60001-80000","7-10 years");

		when(mockDao.getPref(id)).thenReturn(firstWidget);

		InvestmentPreference widget = service.getClientPreference(id);

		
		assertEquals(firstWidget, widget);
	}
	
	@Test
	void testFindAllPreferences_DaoThrowsException() {
		
		when(mockDao.getAllPrefs())
        	.thenThrow(new RuntimeException("mock DAO exception"));
	
        assertThrows(RuntimeException.class, () -> {
			service.getAll();
		});
	}
	
	@Test
	void testAddPreference() {
		InvestmentPreference widget =new InvestmentPreference(126,"for marriage","average","60001-80000","7-10 years");

		when(mockDao.insertPreference(widget)).thenReturn(1);

		int rowsInserted = service.addClientInvestmentPrefernce(widget);

		assertEquals(1, rowsInserted);
	}
	
	@Test
	void testAddPreference_DaoThrowsWarehouseDatabaseException() {
		long id=126;
		InvestmentPreference widget =new InvestmentPreference(id,"for marriage","average","60001-80000","7-10 years");


		when(mockDao.insertPreference(widget)).thenThrow(new DatabaseException("mock DAO exception"));

		assertThrows(DatabaseException.class, () -> {
			service.addClientInvestmentPrefernce(widget);
		});
	}
	
	@Test
	void testAddpreference_DaoThrowsDuplicateKeyException() {
		long id=126;
		InvestmentPreference widget =new InvestmentPreference(id,"for marriage","average","60001-80000","7-10 years");

		when(mockDao.insertPreference(widget)).thenThrow(new DuplicateKeyException("mock DAO exception"));

		assertThrows(DuplicateKeyException.class, () -> {
			service.addClientInvestmentPrefernce(widget);
		});
	}
	

	@Test
	void testAddpreference_Null() {
		assertThrows(IllegalArgumentException.class, () -> {
			service.addClientInvestmentPrefernce(null);
		});
	}
	
	@Test
	void testAddPreference_MissingDescription() {
		InvestmentPreference widget =new InvestmentPreference(126,"","average","60001-80000","7-10 years");

		assertThrows(IllegalArgumentException.class, () -> {
			service.addClientInvestmentPrefernce(widget);
		});
	}
	
	
	@Test 
	void testUpdatePreference() {
		InvestmentPreference widget =new InvestmentPreference(126,"for marriage","average","60001-80000","7-10 years");


		when(mockDao.updatePreference(widget)).thenReturn(1);

		int count = service.updateClientInvestmentPreference(widget);

		assertEquals(1, count);
	}
	


}
