package com.fidelity.portfolio.dao;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.fidelity.portfolio.Client;
import com.fidelity.portfolio.Portfolio;

public interface ClientDao {

	List<Client> getClients();

	boolean verifyClientLogin(String email, long clientId);
	
	public boolean addClient(Client client);
	
	boolean verifyEmail(String email);
	
	BigDecimal getBalanceById(long clientId);
	
	int updateBalance(Map<String, Object> params);

	String getUsernameById(long clientId);

}
