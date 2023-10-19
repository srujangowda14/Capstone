package com.fidelity.portfolio.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fidelity.portfolio.InvestmentPreference;
import com.fidelity.portfolio.dao.PreferenceDao;

@Service
@Transactional
public class PreferenceService {
	@Autowired
	private PreferenceDao dao;
	
	
	public List<InvestmentPreference> getAll() {
		return this.dao.getAllPrefs();
	}
	
	public InvestmentPreference getClientPreference(long clientId) {
		if (clientId <0) {
			throw new IllegalArgumentException("clientId passed is not correct");
		}
		return this.dao.getPref(clientId);
	}

 

	public int addClientInvestmentPrefernce( InvestmentPreference investmentPreference) {
		

       int count=0;

		if (isValidInvestmentPreference(investmentPreference)) {
			 count=this.dao.insertPreference(investmentPreference);
		} else {
			throw new IllegalArgumentException("Invalid investment preference data");

 

		}
		return count;

 

	}

 

	public int updateClientInvestmentPreference( InvestmentPreference investmentPreference) {
		
		int count=0;
		if (isValidInvestmentPreference(investmentPreference)) {
			count=this.dao.updatePreference(investmentPreference);
		} else {
			throw new IllegalArgumentException("Invalid investment preference data");
		}
		return count;
	}

 

	public boolean isValidInvestmentPreference(InvestmentPreference preference) {
		if (preference != null && preference.getInvestmentPurpose() != null
&& !preference.getInvestmentPurpose().isEmpty() && preference.getIncomeCategory() != null
&& !preference.getIncomeCategory().isEmpty() 
&& !preference.getRiskTolerance().isEmpty() && preference.getRiskTolerance() != null
&& !preference.getLengthOfInvestments().isEmpty() && preference.getLengthOfInvestments() != null
) {
			return true;
		}
		return false;
	}

}
