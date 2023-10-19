package com.fidelity.portfolio.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.transaction.TransactionManager;
import org.springframework.transaction.annotation.Transactional;

import com.fidelity.portfolio.Client;
import com.fidelity.portfolio.ClientIdentification;
import com.fidelity.portfolio.LoginRequest;
import com.fidelity.portfolio.LoginResponse;
import com.fidelity.portfolio.dao.ClientDao;

@SpringBootTest
@Transactional
class ClientServiceTest {

	@Mock
	ClientDao mockDao;
	
	@Mock

	private FmtsService mockFmts;


	@InjectMocks
	ClientService fixture;
	

	@BeforeEach
	void setUp() throws Exception {

		MockitoAnnotations.openMocks(this);

	}

	@Test
	void testRegister_Failure_InvalidEmail() {

		Set<ClientIdentification> identifications = new HashSet<>();

		Client c = new Client("abc.com", "50997", "ABC", LocalDate.now().minusYears(20), "USA", identifications);

		assertThrows(IllegalArgumentException.class, () -> fixture.registerUser(c), "Bad email");

	}

	@Test
	void testRegister_Failure_ClientDetailsExist() {

		Set<ClientIdentification> identifications = new HashSet<>();
		ClientIdentification id = new ClientIdentification("SSN", "2222222");
		identifications.add(id);

		Client c = new Client("abc10@gmail.com", "50997", "ABC", LocalDate.now().minusYears(20), "USA",
				identifications);

		assertThrows(IllegalArgumentException.class, () -> fixture.registerUser(c), "Client exists with different email");

	}

	@Test
	void testLogin_Success() throws Exception {
		String email = "abc@example.com";
		long clientId = 943842911;
		LoginResponse res = new LoginResponse(clientId,  email, 943719455);
		LoginRequest req = new LoginRequest(clientId, email);
		Mockito.when(mockDao.verifyClientLogin(email, clientId)).thenReturn(true);
		Mockito.when(mockFmts.login(req)).thenReturn(res);
		
		assertEquals(res, fixture.verifyclientLogin(email, clientId));

	}

//
	@Test
	void testLogin_Failure_InvalidEmail() {
		String email = "abccom";
		long clientId = 123;

		Mockito.when(mockDao.verifyClientLogin(email, clientId)).thenThrow(new IllegalArgumentException());

		assertThrows(IllegalArgumentException.class, () -> fixture.verifyclientLogin(email, clientId), "Invalid email");

	}

//
////
//	@Test
//	void testExistingEmail() {
//		String email = "xyz@example.com";
//		assertThrows(DatabaseException.class, () -> fixture.verifyEmailAddress(dao, email), "email already present");
//	}
//
	@Test
	void testLogin_Failure_EmailNotExist() {
		String email = "abc1@gmail.com";
		long clientId = 943842911;

		Set<ClientIdentification> identifications = new HashSet<>();
		ClientIdentification id = new ClientIdentification("SSN", "2322222");
		Client c = new Client("abc123@gmail.com", "50997", "ABC", LocalDate.now().minusYears(20), "USA",
				identifications);
		Mockito.when(mockDao.verifyClientLogin(email, clientId)).thenThrow(new IllegalArgumentException());

		assertThrows(IllegalArgumentException.class, () -> fixture.verifyclientLogin(email, clientId),
				"Email does not exist");
	}

	@Test
	void testLogin_Failure_InvalidClientId() {
		String email = "abc@example.com";
		long clientId = 123;
		Set<ClientIdentification> identifications = new HashSet<>();
		ClientIdentification id = new ClientIdentification("SSN", "22222222");
		identifications.add(id);

		Client c = new Client("abc@example.com", "50997", "ABC", LocalDate.now().minusYears(20), "USA",
				identifications);

		// fixture.registerUser(c);
		Mockito.when(mockDao.verifyClientLogin(email, clientId)).thenReturn(false);

		//assertFalse(fixture.verifyclientLogin(email, clientId));

	}

	@Test
	public void testRegisterSuccess() {
		Set<ClientIdentification> identifications = new HashSet<>();
		ClientIdentification id = new ClientIdentification("SSN", "222223722");
		identifications.add(id);
		Client c = new Client("mnop@gmail.com", "50997", "MNOP", LocalDate.now().minusYears(20), "USA",
				identifications);
		Client c1 = new Client("abc@gmail.com", "50556", "ABC", LocalDate.now().minusYears(20), "USA",
				identifications);
		List<Client> arrayListForGetClients = new ArrayList<Client>();
		arrayListForGetClients.add(c1);

		Mockito.when(mockDao.getClients()).thenReturn(arrayListForGetClients);
		Mockito.when(mockDao.verifyEmail("mnop@gmail.com")).thenReturn(true);
		Mockito.when(mockDao.addClient(c)).thenReturn(true);
		c.setClientId(222223722);
//		Mockito.when(mockFmts.getClientIdFromFmts(c)).thenReturn();
	
		//assertEquals("MNO222223722",fixture.registerUser(c));
	}
	
	

}
