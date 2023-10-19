package com.fidelity.portfolio;

import java.util.Objects;

public class InvestmentPreference {
	
	private long clientId;
	private  String investmentPurpose;
	private String riskTolerance;
	private String incomeCategory;
	private String  lengthOfInvestments;
	
	public InvestmentPreference() {}
	
	public InvestmentPreference(long i,String investmentPurpose, String riskTolerance, String incomeCategory,
			String lengthOfInvestments) {
		super();
		this.clientId=i;
		this.investmentPurpose = investmentPurpose;
		this.riskTolerance = riskTolerance;
		this.incomeCategory = incomeCategory;
		this.lengthOfInvestments = lengthOfInvestments;
	}
	
	
	public Long getClientId() {
		return clientId;
	}


	public String getInvestmentPurpose() {
		return investmentPurpose;
	}
	public void setInvestmentPurpose(String investmentPurpose) {
		this.investmentPurpose = investmentPurpose;
	}
	public String getRiskTolerance() {
		return riskTolerance;
	}
	public void setRiskTolerance(String riskTolerance) {
		this.riskTolerance = riskTolerance;
	}
	public String getIncomeCategory() {
		return incomeCategory;
	}
	public void setIncomeCategory(String incomeCategory) {
		this.incomeCategory = incomeCategory;
	}
	public String getLengthOfInvestments() {
		return lengthOfInvestments;
	}
	public void setLengthOfInvestments(String lengthOfInvestments) {
		this.lengthOfInvestments = lengthOfInvestments;
	}
	@Override
	public int hashCode() {
		return Objects.hash(incomeCategory, investmentPurpose, lengthOfInvestments, riskTolerance);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		InvestmentPreference other = (InvestmentPreference) obj;
		return Objects.equals(incomeCategory, other.incomeCategory)
				&& Objects.equals(investmentPurpose, other.investmentPurpose)
				&& Objects.equals(lengthOfInvestments, other.lengthOfInvestments)
				&& Objects.equals(riskTolerance, other.riskTolerance);
	}
	
	

	
	

}
