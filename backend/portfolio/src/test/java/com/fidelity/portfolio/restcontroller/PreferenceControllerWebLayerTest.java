package com.fidelity.portfolio.restcontroller;

import static org.hamcrest.Matchers.emptyOrNullString;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.fidelity.portfolio.InvestmentPreference;
import com.fidelity.portfolio.services.PreferenceService;



@WebMvcTest(controllers=PreferenceController.class)
class PreferenceControllerWebLayerTest {

	
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private PreferenceService mockBusinessService;
	
  private List<InvestmentPreference> widgets;
	
	@BeforeEach
	public void init() {
	  widgets = Arrays.asList(new InvestmentPreference(123,"for education","average","0-20000","0-5 years"),
				new InvestmentPreference(126,"for marriage","average","60001-80000","7-10 years"));
	}
	
	@Test
	public void testQueryForWidgetById() throws Exception {
		long id=126;
		InvestmentPreference firstWidget =new InvestmentPreference(126,"for marriage","average","60001-80000","7-10 years");
		
		when(mockBusinessService.getClientPreference(id))
			.thenReturn(firstWidget);
		
		

		mockMvc.perform(get("/preferences/126"))
			   .andDo(print())
			   .andExpect(status().isOk())
			   
			  ;
	}
	
	@Test
	public void testQueryForAllWidgets() throws Exception {
		when(mockBusinessService.getAll())
			.thenReturn(widgets);
		
	

		mockMvc.perform(get("/preferences/prefs"))
			   //.andDo(print())
			   .andExpect(status().isOk())
			   .andExpect(jsonPath("$.length()").value(2))
			;
	}
	
	@Test
	public void testQueryForAllWidgets_DaoReturnsEmptyList() throws Exception {
		when(mockBusinessService.getAll())
			.thenReturn(new ArrayList<InvestmentPreference>());
		
		mockMvc.perform(get("/preferences/prefs"))
			   //.andDo(print())
			   .andExpect(status().isNoContent())
			   .andExpect(content().string(is(emptyOrNullString())));
	}
	
	@Test
	public void testUpdateWidgetInWarehouse() throws Exception {
		InvestmentPreference widget =new InvestmentPreference(126,"for marriage","average","60001-80000","7-10 years");
		
		when(mockBusinessService.updateClientInvestmentPreference(widget))
			.thenReturn(1);
		
		// Creating the ObjectMapper object
		ObjectMapper mapper = new ObjectMapper();
		
		// Converting the Object to JSONString
		String jsonString = mapper.writeValueAsString(widget);
		
		mockMvc.perform(put("/preferences/prefs")
							.contentType(MediaType.APPLICATION_JSON)
							.content(jsonString))
			   //.andDo(print())
			   .andExpect(status().isOk())
			   .andExpect(jsonPath("$.rowCount").value(1));
	}
	
	@Test
	public void testAddWidgetToWarehouse() throws Exception {
		InvestmentPreference widget =new InvestmentPreference(126,"for marriage","average","60001-80000","7-10 years");
		
		when(mockBusinessService.addClientInvestmentPrefernce(widget))
			.thenReturn(1);
		
		
		ObjectMapper mapper = new ObjectMapper();
		
		String jsonString = mapper.writeValueAsString(widget);
		
		

		mockMvc.perform(post("/preferences/prefs")
						   .contentType(MediaType.APPLICATION_JSON)
						   .content(jsonString))
			   //.andDo(print())
			   .andExpect(status().isOk())
			   .andExpect(jsonPath("$.rowCount").value(1));
	}

}
