package com.fidelity.portfolio.restcontroller;


import java.math.BigDecimal;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.expression.spel.CodeFlow.ClinitAdder;
import org.springframework.http.HttpStatus;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerErrorException;

import com.fidelity.portfolio.Client;
import com.fidelity.portfolio.LoginResponse;
import com.fidelity.portfolio.ResponseObject;

import com.fidelity.portfolio.services.ClientService;


/**
 * WarehouseController is a RESTful web service.
 * It provides web methods to manage Widgets and Gadgets 
 * in the Warehouse database.
 * 
 * @author ROI Instructor
 *
 */
@CrossOrigin
@RestController
@RequestMapping("/client")

public class ClientController {
	private static final String DB_ERROR_MSG = 
			"Error communicating with the database";
	
	@Autowired
	private ClientService service;


	@GetMapping(value="/ping",
				produces=MediaType.ALL_VALUE)
	public String ping() {
		return "Client web service is alive and awaits your command";
	}
	
	
	
	@GetMapping(value="/balance",
			produces=MediaType.ALL_VALUE)
	public String getBalance(@RequestParam long clientId) {
		return service.getBalance(clientId).toString();
	}
	
	
	@GetMapping(value="/username",
			produces=MediaType.ALL_VALUE)
	public String getUsername(@RequestParam long clientId) throws JsonProcessingException {
		
		
		ResponseObject responseBody = new ResponseObject(service.getUsername(clientId), 200);
       
        // Create ObjectMapper
        ObjectMapper mapper = new ObjectMapper();

        // Convert Java object to JSON
        String jsonString = mapper.writeValueAsString(responseBody);
		return jsonString ;
	}
	

	@PostMapping(value="/register",
			produces= {MediaType.APPLICATION_JSON_VALUE,
			          MediaType.APPLICATION_XML_VALUE
			},
			consumes= {MediaType.APPLICATION_JSON_VALUE,
					   MediaType.APPLICATION_XML_VALUE
			}
			)
	public ResponseEntity<LoginResponse> registerClient(@RequestBody Client client) {
		ResponseEntity<LoginResponse> response=null;
		LoginResponse registeredClient;
		try {
			registeredClient= service.registerUser(client);
			System.out.println("ClientId "+registeredClient.getClientId());
		}catch(Exception e) {
			throw new ServerErrorException(e.getMessage(),e);
		}
		if(registeredClient==null) {
			response = new ResponseEntity("Error in mapping "+client, HttpStatus.BAD_REQUEST);
		}else {
			response = ResponseEntity.ok(registeredClient);
		}
		return response;
	}
	
	@PostMapping(value="/login",
			produces={ MediaType.APPLICATION_JSON_VALUE, 
			MediaType.APPLICATION_XML_VALUE },
			consumes = {MediaType.APPLICATION_JSON_VALUE, 
					MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<LoginResponse> loginClient(@RequestBody Client client) {
		
		try {
			
			ResponseObject resUnauthorized = new ResponseObject("unauthorized invalid password", 401);
			
			LoginResponse res = service.verifyclientLogin(client.getEmail(), client.getClientId());
			if(res.getToken() != 0)
				return ResponseEntity.ok(res);
			return ResponseEntity.status(401).body(res);
		}
		catch(Exception e) {
			//ResponseObject res = new ResponseObject(e.getMessage(), 500);
			return ResponseEntity.status(500).body(new LoginResponse(0, e.getMessage(), 0));
		}
		
		
	}
	
	
}


