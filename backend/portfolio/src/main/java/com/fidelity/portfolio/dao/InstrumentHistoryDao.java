package com.fidelity.portfolio.dao;

import java.util.List;

import com.fidelity.portfolio.*;

public interface InstrumentHistoryDao {
	
	public List<InstrumentHistory> getInstrumentHistoryByStock();
	
	public List<InstrumentHistory> getInstrumentHistoryByGovt();
	
	public List<InstrumentHistory> getInstrumentHistoryByCD();
	
	public List<InstrumentHistory> getInstrumentHistoryByVolume();


}
