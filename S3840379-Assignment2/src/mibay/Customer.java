package mibay;

import mibay.exceptions.MiBayException;

public class Customer {

	private int id;
	private String firstName;
	private String lastName;
	private Address address;
	
	public Customer(int id, String firstName, String lastName, Address address) throws MiBayException {
		
		if (id <= 0) {
			throw new MiBayException("Invalid id");
		} else {
			this.id = id;
		}
		
		if (firstName.isEmpty() || firstName.equals("")) {
			throw new MiBayException("Empty first name entered.");
		} else {
			this.firstName = firstName;
		}
		
		if (lastName.isEmpty() || lastName.equals("")) {
			throw new MiBayException("Empty last name entered.");
		} else {
			this.lastName = lastName;
		}
		
		this.address = address;
		
	}
	
	public int getId() {
		return id;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public Address getAddress() {
		return address;
	}
	
}
