package com.fidelity.portfolio;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.Set;

public class Client extends Person {

	private Set<ClientIdentification> identifications;
	private long clientId;
	
	public Client() {
		
	}

	public InvestmentPreference getInvestmentPreference() {
		return investmentPreference;
	}

	BigDecimal balance;
	private InvestmentPreference investmentPreference;
	private boolean acceptRoboAdvisorTerms;

	public long getClientId() {
		return clientId;
	}

	public boolean isAcceptRoboAdvisorTerms() {
		return acceptRoboAdvisorTerms;
	}

	public void setAcceptRoboAdvisorTerms(boolean acceptRoboAdvisorTerms) {
		this.acceptRoboAdvisorTerms = acceptRoboAdvisorTerms;
	}

	public void setClientId(long clientId) {
		this.clientId = clientId;

	}

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance.setScale(2, RoundingMode.HALF_DOWN);
	}

	public void setIdentifications(Set<ClientIdentification> identifications) {
		this.identifications = identifications;
	}

	public Client(String email, String postalCode, String name, LocalDate dateOfBirth, String country,
			Set<ClientIdentification> identifications) {
		super(email, postalCode, name, dateOfBirth, country);
		this.identifications = identifications;
		this.balance = new BigDecimal(2000).setScale(2, RoundingMode.HALF_DOWN);
		
		// TODO Auto-generated constructor stub
	}

	public Set<ClientIdentification> getIdentifications() {
		return identifications;
	}

	public void setInvestmentPreference(InvestmentPreference investmentPreference2) {
		this.investmentPreference = investmentPreference2;

	}

	@Override
	public String toString() {
		return "Client [identifications=" + identifications + ", clientId=" + clientId + ", balance=" + balance
				+ ", investmentPreference=" + investmentPreference + ", acceptRoboAdvisorTerms="
				+ acceptRoboAdvisorTerms + "]";
	}
	
	

}
