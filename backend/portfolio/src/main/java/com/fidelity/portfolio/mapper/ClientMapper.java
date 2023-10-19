package com.fidelity.portfolio.mapper;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.fidelity.portfolio.Client;

public interface ClientMapper {

	public int addClient(Client client);

	public int addClientIdentification(Map<String, Object> params);

	public List<Client> queryAllClients();

	public int updateClient(Map<String, Object> params);
	
	BigDecimal selectBalanceById(long clientId);

	public String selectNameById(long clientId);
}
