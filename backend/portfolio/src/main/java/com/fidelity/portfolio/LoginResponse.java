package com.fidelity.portfolio;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Map;
import java.util.Objects;

public class LoginResponse {
	
	private long clientId;
	private String email;
	private long token;
	
	
	public LoginResponse() {
		
	}
	
	public LoginResponse(long clientId, String email, long token) {
		super();
		this.clientId = clientId;
		this.email = email;
		this.token = token;
		
	}
	public long getClientId() {
		return clientId;
	}
	public void setClientId(long clientId) {
		this.clientId = clientId;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public long getToken() {
		return token;
	}
	public void setToken(long token) {
		this.token = token;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(clientId, email, token);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LoginResponse other = (LoginResponse) obj;
		return clientId == other.clientId &&
				Objects.equals(email, other.email) && token == other.token;
	}
	@Override
	public String toString() {
		return "LoginResponse [clientId=" + clientId + ", email=" + email + ", token=" + token + "]";
	}
	public String toJson() {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.writeValueAsString(Map.of("clientId", clientId, "email", email, "token", token));
        } catch (JsonProcessingException e) {
            e.printStackTrace(); // Handle the exception according to your needs
            return ""; // Return an empty string if there's an error in JSON processing
        }
    }

}
