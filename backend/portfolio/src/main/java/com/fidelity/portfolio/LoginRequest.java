package com.fidelity.portfolio;

import java.util.Objects;

public class LoginRequest {
	
	private long clientId;
	private String email;
	public LoginRequest(long clientId, String email) {
		super();
		this.clientId = clientId;
		this.email = email;
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
	@Override
	public int hashCode() {
		return Objects.hash(clientId, email);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LoginRequest other = (LoginRequest) obj;
		return clientId == other.clientId && Objects.equals(email, other.email);
	}
	@Override
	public String toString() {
		return "LoginRequest [clientId=" + clientId + ", email=" + email + "]";
	}
	

}
