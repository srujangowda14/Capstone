package com.fidelity.portfolio.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


import com.fidelity.portfolio.InvestmentPreference;
import com.fidelity.portfolio.mapper.PreferenceMapper;

@Repository("preferenceDao")
public class PreferenceDaoimpl implements PreferenceDao {
	
	@Autowired
	private PreferenceMapper mapper;

	@Override
	public List<InvestmentPreference> getAllPrefs() {
		List<InvestmentPreference> products = mapper.getAllPreferences();
		return products;
	}


	@Override
	public int insertPreference(InvestmentPreference pref) {
		int count = mapper.insertPreference(pref);
		return count;
	}

	@Override
	public int updatePreference(InvestmentPreference pref) {
		int count = mapper.updatePreference(pref);
		return count;
	}

	@Override
	public InvestmentPreference getPref(long clientId) {
		InvestmentPreference product = mapper.getPreference(clientId);
		return product;
	}

}
