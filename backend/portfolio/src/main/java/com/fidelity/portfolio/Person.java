package com.fidelity.portfolio;

import java.time.LocalDate;
import java.time.Period;

public class Person {
	
	private String email;
	private String postalCode;
	private String name;
	private LocalDate dateOfBirth;
	private String country;
	
	public Person() {
		
	}
	public String getEmail() {
		return email;
	}
	public Person(String email,String postalCode, String name, LocalDate dateOfBirth, String country) {
		super();
//		
		this.email = email;
		this.postalCode = postalCode;
		this.name = name;
		this.dateOfBirth = dateOfBirth;
		this.country = country;
	}
	
	public String getPostalCode() {
		return postalCode;
	}
	public String getName() {
		return name;
	}
	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}
	public String getCountry() {
		return country;
	}
	
	
	
	
}
