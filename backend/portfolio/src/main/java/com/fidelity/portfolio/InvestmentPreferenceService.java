package com.fidelity.portfolio;

import java.util.List;
import java.util.Objects;
import java.util.ArrayList;

public class InvestmentPreferenceService {
	private List<Client> clients;

	 

    public InvestmentPreferenceService() {
        clients = new ArrayList<Client>();
    }


    public void addClient(Client client) {
        clients.add(client);
    }

    public void addClientInvestmentPreference(int i, InvestmentPreference investmentPreference)
            throws IllegalArgumentException {
        Client client = findClientById(i);
        if (client == null) {
            throw new IllegalArgumentException("Client not found");
        }

 

        if (!client.isAcceptRoboAdvisorTerms()) {
            throw new IllegalArgumentException("Client has not accepted Robo-Advisor terms and conditions");
        }

       client.setInvestmentPreference(investmentPreference);
    }
    public Client findClientById(int i) {
        for (Client client : clients) {
            if (client.getClientId() == i) {
                return client;
            }
        }
        return null;
    }
    
    public void updateClientInvestmentPreference(int i, InvestmentPreference newPreference)
            throws IllegalArgumentException {
        Client client = findClientById(i);
        if (client == null) {
            throw new IllegalArgumentException("Client not found");
        }

 

        if (isValidInvestmentPreference(newPreference)) {
            client.setInvestmentPreference(newPreference);
        } else {
            throw new IllegalArgumentException("Invalid investment preference data");
        }
    }

    private boolean isValidInvestmentPreference(InvestmentPreference preference) {
    	if (preference != null &&
    	        preference.getInvestmentPurpose() != null && !preference.getInvestmentPurpose().isEmpty() &&
    	        preference.getRiskTolerance() != null && !preference.getRiskTolerance().isEmpty() &&
    	        preference.getIncomeCategory() != null && !preference.getIncomeCategory().isEmpty() &&
    	        preference.getLengthOfInvestments() != null && !preference.getLengthOfInvestments().isEmpty()) {
    	        return true;
    	    }
    	    return false;
    }


	@Override
	public int hashCode() {
		return Objects.hash(clients);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		InvestmentPreferenceService other = (InvestmentPreferenceService) obj;
		return Objects.equals(clients, other.clients);
	}
    
    

}
