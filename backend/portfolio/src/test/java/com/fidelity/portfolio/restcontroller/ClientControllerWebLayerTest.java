package com.fidelity.portfolio.restcontroller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fidelity.portfolio.Client;
import com.fidelity.portfolio.ClientIdentification;
import com.fidelity.portfolio.LoginResponse;
import com.fidelity.portfolio.services.ClientService;

@WebMvcTest(controllers=ClientController.class)
class ClientControllerWebLayerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private ClientService service;
	
	public Client c;
	
	
	
	@BeforeAll
	public static void init() {
		
	}
	@Test
	void testRegisterUser() throws Exception {
		Set<ClientIdentification> identifications = new HashSet<>();
		ClientIdentification id = new ClientIdentification("SSN", "222223722");
		identifications.add(id);
		c = new Client("mnop@gmail.com", "50997", "MNOP", LocalDate.of(2023, 12, 1), "USA",
				identifications);
		LoginResponse res = new LoginResponse();
		when(service.registerUser(c)).thenReturn(res);
		System.out.println(service.registerUser(c));
		ObjectMapper mapper = new ObjectMapper();
		mapper.registerModule(new JavaTimeModule());
		mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
		
		String jsonValue = mapper.writeValueAsString(c);
		
		mockMvc.perform(post("/client/register")
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(jsonValue))
		.andDo(print())
		.andExpect(status().is(200))
		.andExpect(jsonPath("$.rowCount").value(123456789));
		
	}

}
