package mibay;

import mibay.packages.PlatinumPackage;
import mibay.packages.RegularPackage;

public class MiBaySystem {

	private Customer[] customers;
	private Product[] products;
	private RegularPackage[] regPackages;
	private PlatinumPackage[] platPackages;

	public MiBaySystem() {
		customers = new Customer[0];
		products = new Product[0];
		regPackages = new RegularPackage[0];
		platPackages = new PlatinumPackage[0];
	}

	public Customer[] getCustomers() {
		return customers;
	}

	public Product[] getProducts() {
		return products;
	}

	public RegularPackage[] getRegularPackages() {
		return regPackages;
	}

	public PlatinumPackage[] getPlatinumPackages() {
		return platPackages;
	}

	public boolean customersEmpty() {

		if (customers.length == 0) {
			return true;
		}

		return false;

	}

	public boolean productsEmpty() {

		if (products.length == 0) {
			return true;
		}

		return false;

	}

	public boolean regPackagesEmpty() {

		if (regPackages.length == 0) {
			return true;
		}

		return false;

	}
	
	public boolean platPackagesEmpty() {

		if (platPackages.length == 0) {
			return true;
		}

		return false;

	}

	public void addCustomer(Customer newCustomer) {
		Customer[] addCustomer = new Customer[this.customers.length + 1];

		// Clones the customers array to the new array
		for (int i = 0; i < this.customers.length; i++) {
			addCustomer[i] = this.customers[i];
		}

		// Adds the new customer to the end of the new array
		addCustomer[addCustomer.length - 1] = newCustomer;

		// Sets the customers array to the addCustomer array
		this.customers = addCustomer;
	}

	public boolean addProduct(Product newProduct) {
		Product[] addProduct = new Product[this.products.length + 1];

		for (int i = 0; i < this.products.length; i++) {

			if (this.products[i].getName().equals(newProduct.getName())) {
				return false;
			}

			addProduct[i] = this.products[i];
		}

		addProduct[addProduct.length - 1] = newProduct;

		this.products = addProduct;

		return true;
	}

	public void addPlatPackage(PlatinumPackage newPlatPackage) {
		PlatinumPackage[] addPlatPackage = new PlatinumPackage[this.platPackages.length + 1];

		for (int i = 0; i < this.platPackages.length; i++) {
			addPlatPackage[i] = this.platPackages[i];
		}


		addPlatPackage[addPlatPackage.length - 1] = newPlatPackage;

	
		this.platPackages = addPlatPackage;
	}
	
	public void addRegPackage(RegularPackage newRegpackage) {
		RegularPackage[] addRegPackage = new RegularPackage[this.regPackages.length + 1];

		for (int i = 0; i < this.regPackages.length; i++) {
			addRegPackage[i] = this.regPackages[i];
		}


		addRegPackage[addRegPackage.length - 1] = newRegpackage;

	
		this.regPackages = addRegPackage;
	}
	
}
