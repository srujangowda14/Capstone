package com.fidelity.portfolio.mapper;
import java.util.List;

import org.apache.ibatis.annotations.Param;


import com.fidelity.portfolio.InvestmentPreference;

public interface PreferenceMapper {
	List<InvestmentPreference> getAllPreferences();

	
	
	
	InvestmentPreference getPreference(@Param("clientId") long clientId);
	
	int updatePreference(InvestmentPreference pref);
	int insertPreference(InvestmentPreference pref);

}
