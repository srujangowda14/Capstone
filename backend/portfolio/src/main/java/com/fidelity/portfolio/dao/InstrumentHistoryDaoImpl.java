package com.fidelity.portfolio.dao;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.fidelity.portfolio.InstrumentHistory;
import com.fidelity.portfolio.mapper.InstrumentHistoryMapper;


@Repository("instrumentHistoryDao")
public class InstrumentHistoryDaoImpl implements InstrumentHistoryDao {
	
	@Autowired
	InstrumentHistoryMapper mapper;

	@Override
	public List<InstrumentHistory> getInstrumentHistoryByStock() {
		// TODO Auto-generated method stub
		List<InstrumentHistory> history = mapper.getInstrumentHistoryByStock();
		Collections.sort(history, new InstrumentSortingComparator());
		return history;
	}

	@Override
	public List<InstrumentHistory> getInstrumentHistoryByGovt() {
		// TODO Auto-generated method stub
		List<InstrumentHistory> history = mapper.getInstrumentHistoryByGovt();
		Collections.sort(history, new InstrumentSortingComparator());
		return history;
	}

	@Override
	public List<InstrumentHistory> getInstrumentHistoryByCD() {
		// TODO Auto-generated method stub
		List<InstrumentHistory> history = mapper.getInstrumentHistoryByCD();
		Collections.sort(history, new InstrumentSortingComparator());
		return history;
	}

	@Override
	public List<InstrumentHistory> getInstrumentHistoryByVolume() {
		// TODO Auto-generated method stub
		List<InstrumentHistory> history = mapper.getInstrumentHistoryByVolume();
		Collections.sort(history, new InstrumentSortingComparator());
		return history;

	}
	
	

}
class InstrumentSortingComparator implements Comparator<InstrumentHistory> {
	 
    @Override
    public int compare(InstrumentHistory prod1, InstrumentHistory prod2) {
        return (prod2.getPrice_date())
                .compareTo(prod1.getPrice_date());
    }
}
