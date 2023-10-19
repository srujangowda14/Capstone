package com.fidelity.portfolio;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;





class PersonTest {

	 private Client fixture;
	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	
//	
	@Test
	void verifyConstructorForPerson() {
		
		fixture=new Client("poorvi@gmail.com", "Ab120", "poorvi", LocalDate.of(1995, 1 ,1), "india", null );
		assertNotNull(this.fixture);
	}
	
//	
//		
	}


