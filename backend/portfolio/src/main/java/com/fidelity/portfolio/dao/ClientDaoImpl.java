package com.fidelity.portfolio.dao;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.fidelity.portfolio.Client;
import com.fidelity.portfolio.ClientIdentification;
import com.fidelity.portfolio.exceptions.DatabaseException;
import com.fidelity.portfolio.mapper.ClientMapper;

@Repository("clientDao")
public class ClientDaoImpl implements ClientDao {
	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	ClientMapper mapper;

	public ClientDaoImpl() {

	}

	@Override
	public List<Client> getClients() {
		
		System.out.print("inside dao get clients");

		return mapper.queryAllClients();

	}

	public boolean isValidEmail(String email) {
		String emailRegex = "^[A-Za-z0-9+_.-]+@(.+)$";
		if (email.matches(emailRegex) == false) {
			throw new IllegalArgumentException("Invalid email");
		}
		return true;
	}

	@Override
	public boolean verifyEmail(String email) {

		System.out.println("Sent email: " + email);

		if (!isValidEmail(email)) {
			throw new DatabaseException("email is invalid");
		}

		List<Client> clients = getClients();
         System.out.println("length" + clients.size());
		System.out.println("Srujan");
		for (Client i : clients) {
			System.out.println("All Emails" + i.getEmail());
			if (i.getEmail().equals(email)) {
				System.out.println(i.getEmail());
				throw new DatabaseException("email already exists");
			}
		}
		return true;
	}

	@Override
	public boolean verifyClientLogin(String email, long clientId) {
		// TODO Auto-generated method stub
		
		System.out.print("inside dao");
		
		if(email == null) {
			throw new NullPointerException("Email can't be null");
		}
		
		if(clientId <= 0) {
			throw new NullPointerException("ClientId can't be negative or zero");
		}

		List<Client> clients = getClients();

		if (!isValidEmail(email)) {
			throw new IllegalArgumentException("Invalid email");

		}
		for (Client c : clients) {
			if (c.getEmail().equals(email)) {
				if (c.getClientId() == clientId) {
					return true;
				} else {
					// throw new IllegalArgumentException("Incorrect Client Id");
					return false;
				}
			}
		}

		throw new IllegalArgumentException("Email doesn't exist");

	}

	@Override
	public boolean addClient(Client client) {
		boolean clientAdded = mapper.addClient(client) == 1;
		boolean clientIdentificationAdded = false;
		Map<String, Object> params = new HashMap<>();
       
		for (ClientIdentification ci : client.getIdentifications()) {
			 params.put("clientId", client.getClientId());
		     params.put("type", ci.getType());
		     params.put("value", ci.getValue());
			clientIdentificationAdded = mapper.addClientIdentification(params) == 1;
		}
		return clientAdded && clientIdentificationAdded;

	}

	@Override
	public BigDecimal getBalanceById(long clientId) {
		return mapper.selectBalanceById(clientId);
	}

	@Override
	public int updateBalance(Map<String, Object> params) {
		return mapper.updateClient(params);
	}

	@Override
	public String getUsernameById(long clientId) {
		return mapper.selectNameById(clientId);
	}

}
