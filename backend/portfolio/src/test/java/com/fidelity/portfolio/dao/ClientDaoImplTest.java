package com.fidelity.portfolio.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import com.fidelity.portfolio.Client;
import com.fidelity.portfolio.ClientIdentification;

@SpringBootTest
@Transactional
class ClientDaoImplTest {

	@Autowired
	private ClientDao fixture;

//	@Test
//	void testRegister_Failure_InvalidEmail() {
//
//		Set<ClientIdentification> identifications = new HashSet<>();
//
//		Client c = new Client("abc.com", "50997", "ABC", LocalDate.now().minusYears(20), "USA", identifications);
//
//		assertThrows(IllegalArgumentException.class, () -> fixture.registerUser(c), "Bad email");
//
//	}
//
//	@Test
//	void testRegister_Failure_ClientDetailsExist() {
//
//		Set<ClientIdentification> identifications = new HashSet<>();
//		ClientIdentification id = new ClientIdentification("SSN", "2222222");
//		identifications.add(id);
//
//		Client c = new Client("abc10@gmail.com", "50997", "ABC", LocalDate.now().minusYears(20), "USA",
//				identifications);
//
//		assertThrows(DatabaseException.class, () -> fixture.registerUser(c), "Client exists with different email");
//
//	}

	@Test
	void testLogin_Success() {
		String email = "abc@example.com";
		long clientId = 943842911;

		assertTrue(fixture.verifyClientLogin(email, clientId));

	}

//
	@Test
	void testLogin_Failure_InvalidEmail() {
		String email = "abcexample.com";
		long clientId = 943842911;

		assertThrows(IllegalArgumentException.class, () -> fixture.verifyClientLogin(email, clientId), "Invalid email");

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
		String email = "abc1@example.com";
		long clientId = 943842911;

		Set<ClientIdentification> identifications = new HashSet<>();
		ClientIdentification id = new ClientIdentification("SSN", "2322222");
		Client c = new Client("abc123@gmail.com", "50997", "ABC", LocalDate.now().minusYears(20), "USA",
				identifications);

		// fixture.registerUser(c);

		assertThrows(IllegalArgumentException.class, () -> fixture.verifyClientLogin(email, clientId),
				"Email does not exist");
	}
	
	
	@Test
	void testLogin_Failure_EmailNull() {
		String email = null;
		long clientId = 943842911;

		assertThrows(NullPointerException.class, () -> fixture.verifyClientLogin(email, clientId),
				"Client Id can't be null");
	}
	
	@Test
	void testLogin_Failure_ClientIdNull() {
		String email = "abc@example.com";
		long clientId = 0;


		assertThrows(NullPointerException.class, () -> fixture.verifyClientLogin(email, clientId),
				"Email can't be null");
	}

	@Test
	void testLogin_Failure_InvalidClientId() {
		String email = "abc@example.com";
		long clientId = 943843911;
		Set<ClientIdentification> identifications = new HashSet<>();
		ClientIdentification id = new ClientIdentification("SSN", "22222222");
		identifications.add(id);

		Client c = new Client("abc@example.com", "50997", "ABC", LocalDate.now().minusYears(20), "USA",
				identifications);

		// fixture.registerUser(c);

		assertFalse(fixture.verifyClientLogin(email, clientId));

	}

	@Test
	public void testAddClient() {
		Set<ClientIdentification> identifications = new HashSet<>();
		ClientIdentification id = new ClientIdentification("SSN", "222223722");
		Client c = new Client("abc10dsdsd1@gmail.com", "50997", "ABC", LocalDate.now().minusYears(20), "USA",
				identifications);
		int rowsBefore = fixture.getClients().size();
		c.setClientId(943842911);
		//fixture.addClient(c);
		int rowsAfter = fixture.getClients().size();
		//assertEquals(rowsBefore+1, rowsAfter);
	}

}
