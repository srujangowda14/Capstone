package com.fidelity.portfolio.services;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.Comparator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fidelity.portfolio.Stock;
import com.fidelity.portfolio.TradePrice;
import com.fidelity.portfolio.dao.ClientDao;
import com.fidelity.portfolio.dao.InstrumentHistoryDao;
import com.fidelity.portfolio.InstrumentHistory;
import com.fidelity.portfolio.InstrumentSuggestion;
import com.fidelity.portfolio.InvestmentPreference;
import com.fidelity.portfolio.Portfolio;

@Service
public class Roboadvisor {

	@Autowired
	InstrumentHistoryDao dao;
	
	
	@Autowired
	FmtsService fmtsService;
	
	@Autowired
	PreferenceService preferenceService;
	
	@Autowired
	ClientService clientService;
	
	@Autowired
	PortfolioService portfolioService;
	
	
	
	long clientId;
	BigDecimal balance;
	
	

	public ArrayList<InstrumentSuggestion> suggestStocksToBuy(long id) {
		
		InvestmentPreference preference =  preferenceService.getClientPreference(id);
		String tolerance = preference.getRiskTolerance();

		int aggressive=0;
		if(tolerance.equals("AGGRESSIVE") || tolerance.equals("ABOVE AVERAGE") || tolerance.equals("AVERAGE")) {
			aggressive=1;
		}
		else {
		 aggressive = 0;
		}
		// preference
		
		String loi = preference.getLengthOfInvestments();
//		String loi = "5-7 years";
		System.out.println("Years for investment ....................................."+loi);
		List<InstrumentHistory> history;
		if(loi.equals("0-5 years") || loi.equals("5-7 years")) {
			history = dao.getInstrumentHistoryByStock();
		}
		else if(loi.equals("7-10 years")) {
			history = dao.getInstrumentHistoryByCD();
		}
		else{
			System.out.println("GOVTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTT");
			history = dao.getInstrumentHistoryByGovt();
		}
		if(aggressive==1) {
		history = dao.getInstrumentHistoryByVolume();
		}// preference
		Collections.sort(history, new InstrumentSortingComparator());
		Map<String, List<DateValue>> instrument_history_map = new HashMap();
		
		
		
		for (InstrumentHistory i : history) {

			if (instrument_history_map.containsKey(i.getInstrument_id())) {
				if (aggressive == 1) {
					DateValue temp = new DateValue(i.getPrice_date(),
							i.getVolume_purchased());

					instrument_history_map.get(i.getInstrument_id()).add(temp);
				} else {
					DateValue temp = new DateValue(i.getPrice_date(),
							(i.getClosing_price() + i.getOpening_price()) / 2);

					instrument_history_map.get(i.getInstrument_id()).add(temp);
				}

			} else {
				instrument_history_map.put(i.getInstrument_id(), new ArrayList<>());
			}

		}

		Map<Object, List<DateValue>> yearToDateValuesMap;
		List<DateValue> firstAndLastDateValues;
		firstAndLastDateValues = new ArrayList<>();
		Map<String, List<Float>> end_values_map = new HashMap();
		Map<String, List<Float>> cagr_map = new HashMap();
		int z = 0;
		for (Map.Entry<String, List<DateValue>> entry : instrument_history_map.entrySet()) {
			String key = entry.getKey();
			List<DateValue> value = entry.getValue();

//            Print the stock and its value for all dates
//			System.out.print("Key: " + key + ", Values: ");
			for (DateValue i : value) {
//				System.out.print(i.date + " and " + i.value + ", ");

			}

			// Create a map to group DateValue objects by year
			yearToDateValuesMap = value.stream().collect(Collectors.groupingBy(dateValue -> dateValue.date.getYear()));

			// Create a list of DateValue objects for the first and last dates of each year

			for (List<DateValue> dateValuesOfYear : yearToDateValuesMap.values()) {
				if (!dateValuesOfYear.isEmpty()) {
					LocalDate firstDate = dateValuesOfYear.get(0).date;
					LocalDate lastDate = dateValuesOfYear.get(dateValuesOfYear.size() - 1).date;
					firstAndLastDateValues.add(new DateValue(firstDate, dateValuesOfYear.get(0).value));
					firstAndLastDateValues
							.add(new DateValue(lastDate, dateValuesOfYear.get(dateValuesOfYear.size() - 1).value));
					if (end_values_map.containsKey(key)) {

						end_values_map.get(key).add(dateValuesOfYear.get(0).value);
						end_values_map.get(key).add(dateValuesOfYear.get(dateValuesOfYear.size() - 1).value);

					} else {
						end_values_map.put(key, new ArrayList<>());
						end_values_map.get(key).add(dateValuesOfYear.get(0).value);
						end_values_map.get(key).add(dateValuesOfYear.get(dateValuesOfYear.size() - 1).value);
					}
				}
			}

			// Print the stock name and first and last DateValues for each year

			for (DateValue dateValue : firstAndLastDateValues) {

				if (z == 2) {
					break;
				}
				System.out.println("Stock : " + key + ", Date: " + dateValue.date + ", Value: " + dateValue.value);
				z += 1;
			}

		}

		for (Map.Entry<String, List<Float>> entry : end_values_map.entrySet()) {
			String key = entry.getKey();
			List<Float> values = entry.getValue();
			List<Float> cagr = new ArrayList<>();
			for (int i = 0; i < values.size() - 1; i += 2) {
				if (aggressive == 1) {
					cagr.add((values.get(i + 1) + values.get(i)) / 2);
				} else {
					cagr.add(((values.get(i + 1)) / values.get(i)) - 1);
				}
			}

			cagr_map.put(key, cagr);
		}

		int p = 0;
		for (Map.Entry<String, List<Float>> entry : cagr_map.entrySet()) {
			if (p == 2) {
				break;
			}
			String key = entry.getKey();
			List<Float> values = entry.getValue();
			System.out.print("Stock : " + key + " :");
			for (int i = 0; i < values.size(); i += 1) {

				System.out.print(values.get(i) + ", ");
				p += 1;
			}
			System.out.println();
		}

		ArrayList<InstrumentCagr> cagr_mean_map = new ArrayList<>();
		for (Map.Entry<String, List<Float>> entry : cagr_map.entrySet()) {
			String key = entry.getKey();
			List<Float> values = entry.getValue();

			Float mean = (float) values.stream().mapToDouble(Float::doubleValue).average().orElse(0.0f);
			cagr_mean_map.add(new InstrumentCagr(key, mean));

		}

		Collections.sort(cagr_mean_map, new CagrSortingComparator());

		for (int i = 0; i < cagr_mean_map.size(); i++) {
			System.out.println(
					"Name of stock : " + cagr_mean_map.get(i).name + ", mean CAGR : " + cagr_mean_map.get(i).cagr);

		}
		
		System.out.println("Stocks to buy");
		ArrayList<InstrumentSuggestion> toBuy = new ArrayList<>();
		int msize=0;
		for (int i = 0; i <cagr_mean_map.size(); i++) {
			if(msize==5) {
				break;
			}
			
			balance = clientService.getBalance(id);
			List<TradePrice> currentTradePrice = fmtsService.getTradesPrice();
			
			for(TradePrice t : currentTradePrice) {
				System.out.println(cagr_mean_map.get(i).name+", + "+ t.getInstrument().getInstrumentId()+", + "+t.getAskPrice());
				if(cagr_mean_map.get(i).name.equals(t.getInstrument().getInstrumentId()) && balance.compareTo(t.getAskPrice())==1) {
					
					toBuy.add(new InstrumentSuggestion(t.getInstrument().getInstrumentDescription(), t.getInstrument().getInstrumentId(), t.getAskPrice()));
					msize+=1;
				}
			}
			
			}

		for (int i = 0; i < toBuy.size(); i++) {
			System.out.println(toBuy.get(i));
		}
		return toBuy;
		
		
//		suggestStocksToBuy(cagr_mean_map);
//		suggestStocksToSell(cagr_mean_map);
//		return null;

	}
	
	
	
	
	
	public ArrayList<InstrumentSuggestion> suggestStocksToSell(long id) {

		InvestmentPreference preference =  preferenceService.getClientPreference(id);
		String tolerance = preference.getRiskTolerance();
		List<Portfolio> currentPortfolio = portfolioService.getPortfolio(id);
		for(Portfolio check : currentPortfolio) {
			System.out.println("Please workkkkkkkk...... "+ check.getInstrumentId());
		}
		
		
		int aggressive=0;
		if(tolerance.equals("AGGRESSIVE") || tolerance.equals("ABOVE AVERAGE") || tolerance.equals("AVERAGE")) {
			aggressive=1;
		}
		else {
		 aggressive = 0;
		}
		// preference
		
		String loi = preference.getLengthOfInvestments();
//		String loi = "5-7 years";
		
		List<InstrumentHistory> history;
		if(loi.equals("0-5 years") || loi.equals("5-7 years")) {
			history = dao.getInstrumentHistoryByStock();
		}
		else if(loi.equals("7-10 years")) {
			history = dao.getInstrumentHistoryByCD();
		}
		else {
			history = dao.getInstrumentHistoryByGovt();
		}
		history = dao.getInstrumentHistoryByVolume();     // preference
		
		Collections.sort(history, new InstrumentSortingComparator());
		Map<String, List<DateValue>> instrument_history_map = new HashMap();
		
		
		
		for (InstrumentHistory i : history) {

			if (instrument_history_map.containsKey(i.getInstrument_id())) {
				if (aggressive == 1) {
					DateValue temp = new DateValue(i.getPrice_date(),
							i.getVolume_purchased());

					instrument_history_map.get(i.getInstrument_id()).add(temp);
				} else {
					DateValue temp = new DateValue(i.getPrice_date(),
							(i.getClosing_price() + i.getOpening_price()) / 2);

					instrument_history_map.get(i.getInstrument_id()).add(temp);
				}

			} else {
				instrument_history_map.put(i.getInstrument_id(), new ArrayList<>());
			}

		}

		Map<Object, List<DateValue>> yearToDateValuesMap;
		List<DateValue> firstAndLastDateValues;
		firstAndLastDateValues = new ArrayList<>();
		Map<String, List<Float>> end_values_map = new HashMap();
		Map<String, List<Float>> cagr_map = new HashMap();
		int z = 0;
		for (Map.Entry<String, List<DateValue>> entry : instrument_history_map.entrySet()) {
			String key = entry.getKey();
			List<DateValue> value = entry.getValue();

//            Print the stock and its value for all dates
//			System.out.print("Key: " + key + ", Values: ");
			for (DateValue i : value) {
//				System.out.print(i.date + " and " + i.value + ", ");

			}

			// Create a map to group DateValue objects by year
			yearToDateValuesMap = value.stream().collect(Collectors.groupingBy(dateValue -> dateValue.date.getYear()));

			// Create a list of DateValue objects for the first and last dates of each year

			for (List<DateValue> dateValuesOfYear : yearToDateValuesMap.values()) {
				if (!dateValuesOfYear.isEmpty()) {
					LocalDate firstDate = dateValuesOfYear.get(0).date;
					LocalDate lastDate = dateValuesOfYear.get(dateValuesOfYear.size() - 1).date;
					firstAndLastDateValues.add(new DateValue(firstDate, dateValuesOfYear.get(0).value));
					firstAndLastDateValues
							.add(new DateValue(lastDate, dateValuesOfYear.get(dateValuesOfYear.size() - 1).value));
					if (end_values_map.containsKey(key)) {

						end_values_map.get(key).add(dateValuesOfYear.get(0).value);
						end_values_map.get(key).add(dateValuesOfYear.get(dateValuesOfYear.size() - 1).value);

					} else {
						end_values_map.put(key, new ArrayList<>());
						end_values_map.get(key).add(dateValuesOfYear.get(0).value);
						end_values_map.get(key).add(dateValuesOfYear.get(dateValuesOfYear.size() - 1).value);
					}
				}
			}

			// Print the stock name and first and last DateValues for each year

			for (DateValue dateValue : firstAndLastDateValues) {

				if (z == 2) {
					break;
				}
				System.out.println("Stock : " + key + ", Date: " + dateValue.date + ", Value: " + dateValue.value);
				z += 1;
			}

		}

		for (Map.Entry<String, List<Float>> entry : end_values_map.entrySet()) {
			String key = entry.getKey();
			List<Float> values = entry.getValue();
			List<Float> cagr = new ArrayList<>();
			for (int i = 0; i < values.size() - 1; i += 2) {
				if (aggressive == 1) {
					cagr.add((values.get(i + 1) + values.get(i)) / 2);
				} else {
					cagr.add(((values.get(i + 1)) / values.get(i)) - 1);
				}
			}

			cagr_map.put(key, cagr);
		}

		int p = 0;
		for (Map.Entry<String, List<Float>> entry : cagr_map.entrySet()) {
			if (p == 2) {
				break;
			}
			String key = entry.getKey();
			List<Float> values = entry.getValue();
			System.out.print("Stock : " + key + " :");
			for (int i = 0; i < values.size(); i += 1) {

				System.out.print(values.get(i) + ", ");
				p += 1;
			}
			System.out.println();
		}

		ArrayList<InstrumentCagr> cagr_mean_map = new ArrayList<>();
		for (Map.Entry<String, List<Float>> entry : cagr_map.entrySet()) {
			String key = entry.getKey();
			List<Float> values = entry.getValue();

			Float mean = (float) values.stream().mapToDouble(Float::doubleValue).average().orElse(0.0f);
			cagr_mean_map.add(new InstrumentCagr(key, mean));

		}

		Collections.sort(cagr_mean_map, new CagrSortingComparator());

		for (int i = 0; i < cagr_mean_map.size(); i++) {
			System.out.println(
					"Name of stock : " + cagr_mean_map.get(i).name + ", mean CAGR : " + cagr_mean_map.get(i).cagr);

		}
		
		Collections.reverse(cagr_mean_map);
		System.out.println("Stocks to sell");
		ArrayList<InstrumentSuggestion> toSell = new ArrayList<>();
		int msize=0;
		for (int i = 0; i <cagr_mean_map.size(); i++) {
			if(msize==5) {
				break;
			}
			for(Portfolio portfolio : currentPortfolio) {
				if(portfolio.getInstrumentId().equals(cagr_mean_map.get(i).name)) {
					List<TradePrice> currentTradePrice = fmtsService.getTradesPrice();
					for(TradePrice temp : currentTradePrice) {
						if(temp.getInstrument().getInstrumentId().equals(cagr_mean_map.get(i).name)) {
							toSell.add(new InstrumentSuggestion(temp.getInstrument().getInstrumentDescription(),temp.getInstrument().getInstrumentId(),temp.getBidPrice()));
							msize+=1;
						}
					}
				}
			}
			}

		for (int i = 0; i < toSell.size(); i++) {
			System.out.println(toSell.get(i));
		}
		return toSell;

	}
	

	class DateValue {
		LocalDate date;
		Float value;

		DateValue(LocalDate date, Float value) {
			this.date = date;
			this.value = value;
		}

	}

	class InstrumentCagr {
		String name;
		Float cagr;

		InstrumentCagr(String name, float cagr) {
			this.name = name;
			this.cagr = cagr;

		}
	}

	class CagrSortingComparator implements Comparator<InstrumentCagr> {
		@Override
		public int compare(InstrumentCagr prod1, InstrumentCagr prod2) {
			return (prod2.cagr).compareTo(prod1.cagr);
		}
	}

	class InstrumentSortingComparator implements Comparator<InstrumentHistory> {

		@Override
		public int compare(InstrumentHistory prod1, InstrumentHistory prod2) {
			return (prod2.getPrice_date()).compareTo(prod1.getPrice_date());
		}
	}


	private boolean checkPresence(String name, ArrayList<Portfolio> holdings) {
		// TODO Auto-generated method stub
		for (Portfolio i : holdings) {
			if (i.getInstrumentDescription().trim().equals(name.trim())) {
				return true;

			}
		}
		return false;
	}



}
