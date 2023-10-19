package com.fidelity.portfolio.dao;

import java.util.List;


import com.fidelity.portfolio.InvestmentPreference;



public interface PreferenceDao {
	
	
	List<InvestmentPreference> getAllPrefs();

	InvestmentPreference getPref(long id);

	

	int insertPreference(InvestmentPreference widget);

	int updatePreference(InvestmentPreference widget);

}
