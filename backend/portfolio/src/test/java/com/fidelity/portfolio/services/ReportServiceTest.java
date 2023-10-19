//package com.fidelity.portfolio.services;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertNotNull;
//
//import java.math.BigDecimal;
//import java.math.RoundingMode;
//import java.util.ArrayList;
//import java.util.List;
//
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import com.fidelity.portfolio.Report;
//import com.fidelity.portfolio.Trade;
//import com.fidelity.portfolio.TradeHistory;
//
//class ReportServiceTest {
//
//	private ReportService reportService;
//	private TradeHistory trade1;
//	private Trade trade2;
//	private Trade trade3;
//	private Trade trade4;
//	List<Report> reportList;
//	List<Trade> trades;
//	
//	@BeforeEach
//	void setUp() throws Exception {
//		reportService = new ReportService();
//		reportList = new ArrayList<Report>();
//		trade1 = new TradeHistory(new BigDecimal(309.90), 1, "B", "N123", "JP Morgan Chase Capital Stock",943842911, "XYZ", new BigDecimal(104.25).setScale(2, RoundingMode.HALF_DOWN));
//		trade2 =new Trade(new BigDecimal(309.90), 1, "B", "T12367", "JP Morgan Chase Capital Stock",943842911, "XYZ", new BigDecimal(107.25).setScale(2, RoundingMode.HALF_DOWN));
//		trade3 = new Trade(new BigDecimal(309.90), 1, "S", "K19823", "JP Morgan Chase Capital Stock",943842911, "XYZ", new BigDecimal(204.25).setScale(2, RoundingMode.HALF_DOWN));
//		trade4 = new Trade(new BigDecimal(309.90), 1, "S", "S12983","JP Morgan Chase Capital Stock",943842911, "XYZ", new BigDecimal(104.25).setScale(2, RoundingMode.HALF_DOWN));
//		trades = new ArrayList<Trade>(List.of(trade1,trade2,trade3,trade4));
//		
//	}
//
//	@AfterEach
//	void tearDown() throws Exception {
//	}
//
//	@Test
//	void verifyObjectInstantiation() {
//		assertNotNull(reportService);
//	}
//	
//	@Test
//	void verifyGetBuyReport() {
//		Report reportElement1 = reportService.createReportWithTradeData(trade1);
//		Report reportElement2 = reportService.createReportWithTradeData(trade2);
//		
//		reportList.add(reportElement1);
//		reportList.add(reportElement2);
//		
//		assertEquals(reportList, reportService.getBuyReport(trades, 943842911));	
//	}
//	
//	@Test
//	void verifyGetSellReport(){
//		Report reportElement1 = reportService.createReportWithTradeData(trade3);
//		Report reportElement2 = reportService.createReportWithTradeData(trade4);
//		
//		reportList.add(reportElement1);
//		reportList.add(reportElement2);
//		
//		assertEquals(reportList, reportService.getSellReport(trades, 943842916));	
//	}
//	
//
//}
