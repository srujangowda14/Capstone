package com.fidelity.portfolio.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerErrorException;
import org.springframework.web.server.ServerWebInputException;


import com.fidelity.portfolio.InvestmentPreference;
import com.fidelity.portfolio.services.PreferenceService;


@CrossOrigin
@RestController
@RequestMapping("/preferences")
public class PreferenceController {
	private static final String DB_ERROR_MSG = "Unable to communicate with the library";
	@Autowired
	private PreferenceService service;
	
	
	@GetMapping(value="/ping",
			produces=MediaType.ALL_VALUE)
    public String ping() {
	return "InvestmentGuru Webservice is alive and awaits your command";
     }
	
	
	@GetMapping(value = "/prefs",
			produces = { MediaType.APPLICATION_JSON_VALUE, 
						 MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<List<InvestmentPreference>> queryForAllPreferences() {
		ResponseEntity<List<InvestmentPreference>> result;
		List<InvestmentPreference> products;
		try {
			products = service.getAll();
		} 
		catch (Exception e) {
			throw new ServerErrorException(DB_ERROR_MSG, e);
		}
		
		if (products.size() > 0) {
			result = ResponseEntity.ok(products);
		}
		else {
			result = ResponseEntity.noContent().build();
		}
		return result;
	}
	
	
	@GetMapping(value = "/{clientId}",
		    produces = { MediaType.APPLICATION_JSON_VALUE, 
			    		 MediaType.APPLICATION_XML_VALUE })
			public InvestmentPreference queryForPreferenceById(@PathVariable long clientId) {
		InvestmentPreference widget = null;
				try {
					widget = service.getClientPreference(clientId);
				} 
				catch (Exception e) {
					throw new ServerErrorException(DB_ERROR_MSG, e);
				}
				if (widget == null) {
					throw new ServerWebInputException(
							"No widget in the warehouse with id = " + clientId);
				}
				return widget;
			}
	
	@PostMapping("/prefs")
	public DatabaseRequestResult insertPreference(@RequestBody InvestmentPreference widget) {
		int count = 0;
		try {
			count = service.addClientInvestmentPrefernce(widget);
		} 
		catch (Exception e) {
			throw new ServerErrorException(DB_ERROR_MSG, e);
		}
		if (count == 0) {
			throw new ServerWebInputException("Can't insert widget " + widget);
		}
		return new DatabaseRequestResult(count);
	}
	
	
	@PutMapping("/prefs")
	
	public DatabaseRequestResult updatePreference(@RequestBody InvestmentPreference widget) {
		int count = 0;
		try {
			count = service.updateClientInvestmentPreference(widget);
		} 
		catch (Exception e) {
			throw new ServerErrorException(DB_ERROR_MSG, e);
		}
		if (count == 0) {
			throw new ServerWebInputException("Can't update widget " + widget);
		}
		return new DatabaseRequestResult(count);
	}
	
	

}
