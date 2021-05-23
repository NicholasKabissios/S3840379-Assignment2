package mibay.packages;

import java.time.LocalDate;

import mibay.Customer;
import mibay.Product;
import mibay.exceptions.MiBayException;

public class RegularPackage implements MiBayPackage {

	private LocalDate date;
	private Customer customer;
	private Product[] products;
	
	public RegularPackage(LocalDate date, Customer customer, Product product) throws MiBayException {
		
		if (date == null || date.isBefore(LocalDate.now())) {
			throw new MiBayException("Invalid date.");
		}
		
		products = new Product[] {product};
		
	}

	@Override
	public LocalDate getDate() {
		// TODO Auto-generated method stub
		return date;
	}

	@Override
	public Customer getCustomer() {
		return customer;
	}

	@Override
	public Product[] getProducts() {
		return products;
	}

	@Override
	public boolean addProduct(Product product) {
	    Product[] newProducts = new Product[this.products.length + 1];
	    
	    // Clones the product array to the new array
	    for (int i = 0; i < this.products.length; i++) {
	        // Ensure that the product has not already been added to the package
	        if (this.products[i].getName().equals(product.getName())) {
	            return false;
	        }
	        newProducts[i] = this.products[i];
	    }
	    
	    // Adds the new product to the end of the new array
	    newProducts[newProducts.length - 1] = product;
	    
	    // Sets the products array to the newProducts array
	    this.products = newProducts;

	    // The product was successfully added to the package
	    return true;
	}

	@Override
	public double getCost() {
		double total = 0;
		
		for (int i = 0; i < products.length; i++) {
			total += products[i].getCost();
		}
		
		return total;
	}
	
}
