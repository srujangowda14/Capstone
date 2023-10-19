package com.fidelity.portfolio.services;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fidelity.portfolio.Client;
import com.fidelity.portfolio.ClientIdentification;
import com.fidelity.portfolio.LoginRequest;
import com.fidelity.portfolio.LoginResponse;
import com.fidelity.portfolio.Trade;
import com.fidelity.portfolio.dao.ClientDao;

@Service
public class ClientService {

	@Autowired
	private ClientDao dao;
	
	@Autowired
	private FmtsService fmtsService;

	List<Client> clients;

	public ClientService() {
		// TODO Auto-generated constructor stub
		clients = new ArrayList<Client>();

	}

	public boolean verifyEmailAddressFormat(String email) {

		// if invalid email throw illegalargumentexception

		// if email is valid check if it exists in client, if yes return false, else
		// true
		if (email.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
			for (Client c : clients) {
				if (c.getEmail().equals(email)) {
					return false;
				}
			}
			return true;
		} else {
			throw new IllegalArgumentException("Invalid email");
		}
	}

	public boolean verifyEmailAddress(String email) {

		return dao.verifyEmail(email);

	}

	public boolean verifyClientDetails(Client c) {

		// check if client identification exists in any of the client objects.
		Set<ClientIdentification> cIds = c.getIdentifications();
		for (Client client : clients) {
			System.out.println("Hiiii");
			Set<ClientIdentification> ids = client.getIdentifications();
			for (ClientIdentification id : ids) {
				if (cIds.contains(id)) {
					return false;
				}
			}
		}
		System.out.println("True entered");
		return true;
	}

	public List<Client> getClients() {
		return clients;
	}

	public LoginResponse registerUser(Client client) throws Exception {
		String email = client.getEmail();
		LoginResponse res;

		if (verifyEmailAddress(email)) {
			if (verifyClientDetails(client)) {
				long clientId = 0;
				LoginRequest req = new LoginRequest(clientId, client.getEmail());
				res  = fmtsService.getClientIdFromFmts(req);
				clientId = res.getClientId();
				client.setClientId(clientId);
				client.setBalance( new BigDecimal(2000).setScale(2, RoundingMode.HALF_DOWN));
				dao.addClient(client);	
				System.out.println("I am fmts" + res);
                System.out.println("Hello" + clientId);
				return res;

			} else {
				throw new IllegalArgumentException("Client already exists with another email");
			}

		} else {

			throw new IllegalArgumentException("Invalid email");
		}

	}

	public LoginResponse verifyclientLogin(String email, long clientId) throws Exception {
		
		System.out.print("inside service");
		
		
		if(dao.verifyClientLogin(email, clientId)) {
			try {
				LoginResponse res = fmtsService.login(new LoginRequest(clientId, email));
				return res;
			} catch (Exception e) {
				throw new Exception(e.getMessage());
			}
		}
		else {
			return new LoginResponse(clientId, email, 0);
		}

		

	}

	public boolean verifyClientIdExists(long clientId) {

		// verify if clientId exists
		for (Client c : clients) {
			if (c.getClientId() == clientId) {
				return true;
			}
		}
		// return false
		return true;
	}
	
	public int updateBalance(Trade trade) {
		
		BigDecimal currentBalance = dao.getBalanceById(trade.getClientId());
		
		Map<String, Object> params = new HashMap<>();
		
		if(trade.getDirection().equals("S")) {
			params.put("balance", currentBalance.add(trade.getCashValue()));
		}
		else {
			params.put("balance", currentBalance.subtract(trade.getCashValue()));
		}
		
		
		params.put("clientId", trade.getClientId());
		return dao.updateBalance(params);
		
	}
	
	public BigDecimal getBalance(long clientId) {
		return dao.getBalanceById(clientId);
	}
	
	public String getUsername(long clientId) {
		return dao.getUsernameById(clientId);
	}

}
