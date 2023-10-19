package com.fidelity.portfolio;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ResponseObject {
	
	String message;
	int status;
	public ResponseObject(String message, int status) {
		super();
		this.message = message;
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String toJson() {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.writeValueAsString(Map.of("status", status, "message", message));
        } catch (JsonProcessingException e) {
            e.printStackTrace(); // Handle the exception according to your needs
            return ""; // Return an empty string if there's an error in JSON processing
        }
    }
	
}
