//package com.fidelity.portfolio;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//import java.time.LocalDate;
//
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//class InvestmentPreferenceServiceTest {
//	
//
//	private InvestmentPreferenceService investmentPrefernceService;
//
//	 
//
//	@BeforeEach
//    public void setUp() {
//		investmentPrefernceService = new InvestmentPreferenceService();
//    }
//
//	@AfterEach
//	public void tearDown() {
//		investmentPrefernceService = null;
//	}
//
//	@Test
//    public void testAddClientInvestmentPreference_Success() {
//        Client client = new Client("poorvi@gmail.com", "Ab120", "poorvi", LocalDate.of(2001, 1 ,1), "india", null );
//        
//        client.setAcceptRoboAdvisorTerms(true);
//
// 
//        
//        InvestmentPreference preference = new InvestmentPreference(null, null, null, null, null);
//        preference.setInvestmentPurpose("Retirement");
//        preference.setRiskTolerance("Moderate");
//        preference.setIncomeCategory("High");
//        preference.setLengthOfInvestments("Long-term");
//
// 
//
//        investmentPrefernceService.addClient(client);
//        investmentPrefernceService.addClientInvestmentPreference(100, preference);
//
// 
//
//        assertEquals(preference, client.getInvestmentPreference());
//    }
//
//	@Test
//    public void testUpdateClientInvestmentPreference_Success() {
//		Client client = new Client("poorvi@gmail.com", "Ab120", "poorvi", LocalDate.of(2001, 1 ,1), "india", null );
//        
//        client.setAcceptRoboAdvisorTerms(true);
//
// 
//
//        InvestmentPreference oldPreference = new InvestmentPreference(null, null, null, null, null);
//        oldPreference.setInvestmentPurpose("Retirement");
//        oldPreference.setRiskTolerance("Moderate");
//        oldPreference.setIncomeCategory("High");
//        oldPreference.setLengthOfInvestments("Long-term");
//        investmentPrefernceService.addClient(client);
//
// 
//
//        InvestmentPreference newPreference = new InvestmentPreference(null, null, null, null, null);
//        newPreference.setInvestmentPurpose("Education");
//        newPreference.setRiskTolerance("Moderate");
//        newPreference.setIncomeCategory("High");
//        newPreference.setLengthOfInvestments("Long-term");
//
// 
//
//        investmentPrefernceService.addClient(client);
//        investmentPrefernceService.addClientInvestmentPreference(100, oldPreference);
//        investmentPrefernceService.updateClientInvestmentPreference(100, newPreference);
//
// 
//
//        assertEquals(newPreference, client.getInvestmentPreference());
//    }
//	
//	@Test
//    public void testAddClientInvestmentPreferenceWhenClientNotFound_Failure() {
//        Client client = new Client("akash@gmail.com", "509209", "Akash", LocalDate.now(), "USA", null);
//        InvestmentPreference preference = new InvestmentPreference(null, null, null, null, null);
//        preference.setInvestmentPurpose("Retirement");
//        preference.setLengthOfInvestments("Long-term");
//
// 
//
//        investmentPrefernceService.addClient(client);
//        assertThrows(IllegalArgumentException.class, ()->investmentPrefernceService.addClientInvestmentPreference(2, preference));
//    }
//	
//	@Test
//	public void testUpdateClientInvestmentPreferenceWhenClientNotFound_Failure() {
//		Client client = new Client("akash@gmail.com", "509209", "Akash", LocalDate.now(), "USA", null);
//
//
//		InvestmentPreference oldPreference = new InvestmentPreference(null, null, null, null, null);
//		oldPreference.setInvestmentPurpose("Retirement");
//		oldPreference.setRiskTolerance("Moderate");
//		oldPreference.setIncomeCategory("High");
//		oldPreference.setLengthOfInvestments("Long-term");
//		investmentPrefernceService.addClient(client);
//		 client.setAcceptRoboAdvisorTerms(true);
//		investmentPrefernceService.addClientInvestmentPreference(1, oldPreference);
//
//		InvestmentPreference newPreference = new InvestmentPreference(null, null, null, null, null);
//		newPreference.setInvestmentPurpose("Education");
//		newPreference.setRiskTolerance(null);
//		newPreference.setIncomeCategory(null);
//		newPreference.setLengthOfInvestments("Long-term");
//
//		assertThrows(IllegalArgumentException.class, ()->investmentPrefernceService.updateClientInvestmentPreference(2, newPreference));
//	}
//
// 
//
//}
