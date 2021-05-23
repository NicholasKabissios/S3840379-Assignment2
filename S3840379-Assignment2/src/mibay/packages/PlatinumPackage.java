package mibay.packages;

import java.time.LocalDate;

import mibay.Customer;
import mibay.Product;
import mibay.exceptions.MiBayException;

public class PlatinumPackage extends RegularPackage{

	private String memberNumber;
	
	public PlatinumPackage(LocalDate date, Customer customer, Product product, String memberNumber) throws MiBayException {
		super(date, customer, product);
		
		if (!isValidMember(memberNumber)) {
			throw new MiBayException("Incorrect member number.");
		} else {
			this.memberNumber = memberNumber;
		}
		
		
	}

	public static boolean isValidMember(String memberNumber) {
		
		if (!Character.isUpperCase(memberNumber.charAt(0))) {
			return false;
		} 
		
		for (int i = 1; i < memberNumber.length(); i++) {
			if (!Character.isDigit(memberNumber.charAt(i))) {
				return false;
			}
		}
		
		return true;
	}
	
	@Override
	public double getCost() {
	 //10% discount  
		double total = 0;
		
		for (int i = 0; i < getProducts().length; i++) {
			total += getProducts()[i].getCost();
		}
		
		return (90.00 * total) / 100.00;
	}
	
	public String getMemberNumber() {
		return memberNumber;
	}
}
