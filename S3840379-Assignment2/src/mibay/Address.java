package mibay;

import mibay.exceptions.MiBayException;

public class Address {

	private String street;
	private String suburb;
	private int postCode;

	public Address(String street, String suburb, int postCode) throws MiBayException {

		if (street.isEmpty() || street.equals("")) {
			throw new MiBayException("Empty street entered.");
		} else {
			this.street = street;
		}

		if (suburb.isEmpty() || suburb.equals("")) {
			throw new MiBayException("Empty suburb entered.");
		} else {
			this.suburb = suburb;
		}

		if (postCode < 1) {
			throw new MiBayException("Postcode entered is too small.");
		} else if (postCode > 9999) {
			throw new MiBayException("Postcode entered is too large.");
		} else {
			this.postCode = postCode;
		}

	}

	public String toString() {

		String address = ("" + street + " " + suburb + " " + Integer.toString(postCode));

		return address;

	}

}
