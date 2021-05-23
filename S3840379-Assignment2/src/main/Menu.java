package main;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import mibay.Address;
import mibay.Customer;
import mibay.MiBaySystem;
import mibay.Product;
import mibay.exceptions.MiBayException;
import mibay.packages.PlatinumPackage;
import mibay.packages.RegularPackage;

public class Menu {

	private MiBaySystem system;
	private static Scanner scanner = new Scanner(System.in);

	public Menu() {

	}

	public void run() {
		system = new MiBaySystem();
		MainMenu();

	}

	public void MainMenu() {

		
		/*
		 * try { Address address = new Address("asdasdasd", "asdasda", 0); } catch
		 * (MiBayException e) { System.err.println(e); }
		 */
		 
		
		

		System.out.println("*** MiBaySystem Menu ***");
		System.out.println("Add Customer                       AC");
		System.out.println("Add Product                        AP");
		System.out.println("Prepare Package                    PP");
		System.out.println("Display all Customers              DC");
		System.out.println("Display all Products               DP");
		System.out.println("Display all Packages               DA");
		System.out.println("Seed Data                          SD");
		System.out.println("Exit Program                       EX");
		System.out.println();
		System.out.print("Select an option: ");

		String userInput = scanner.nextLine();

		emptyString(userInput);

		if (userInput.equalsIgnoreCase("AC")) {
			addCustomer();
		} else if (userInput.equalsIgnoreCase("AP")) {
			addProduct();
		} else if (userInput.equalsIgnoreCase("PP")) {
			preparePackage();
		} else if (userInput.equalsIgnoreCase("DC")) {
			displayCustomers();
		} else if (userInput.equalsIgnoreCase("DP")) {
			displayProducts();
		} else if (userInput.equalsIgnoreCase("DA")) {
			displayPackages();
		} else if (userInput.equalsIgnoreCase("SD")) {
			try {
				seedData();
			} catch (MiBayException e) {
				System.err.println(e);
				System.out.println();
				MainMenu();
			}
		} else if (userInput.equalsIgnoreCase("EX")) {
			exit();
		}

		

	}

	public void emptyString(String userInput) {

		if (userInput.equals("")) {

			System.out.println();

			MainMenu();
		}

	}

	public void emptyInt(int userInput) {

		if (userInput <= 0) {

			System.out.println();

			MainMenu();
		}

	}

	public void emptyDouble(double userInput) {

		if (userInput <= 0) {

			System.out.println();

			MainMenu();
		}
	}

	public void addCustomer()  {


		int id = Integer.parseInt(Integer.toString(system.getCustomers().length + 1, 8));

		System.out.println("- Add Customer (press enter to return to the menu) -");
		System.out.println("ID:                   " + id);

		System.out.print("Enter first name: ");
		String firstName = scanner.nextLine();
		emptyString(firstName);

		System.out.print("Enter last name: ");
		String lastName = scanner.nextLine();


		System.out.print("Enter street: ");
		String street = scanner.nextLine();


		System.out.print("Enter suburb: ");
		String suburb = scanner.nextLine();


		System.out.print("Enter postcode: ");
		int postcode = scanner.nextInt();

		scanner.nextLine();
		
		Address customerAddress = null;
		try {
			customerAddress = new Address(street, suburb, postcode);
		} catch (MiBayException e) {
			System.err.println(e);
			System.out.println();
			MainMenu();
			return;
		}
		
		Customer newCustomer = null;
		try {
			newCustomer = new Customer(id, firstName, lastName, customerAddress);
		} catch (MiBayException e) {
			System.err.println(e);
			System.out.println();
			MainMenu();
			return;
		}

		system.addCustomer(newCustomer);

		System.out.println(firstName + " " + lastName + " was successfully added to the system.");

		System.out.println();

		MainMenu();

	}

	public void addProduct() {


		System.out.println("- Add Product (press enter to return to the menu) -");
		System.out.print("Enter name: ");
		String productName = scanner.nextLine();
		emptyString(productName);

		System.out.print("Enter weight: ");
		int weight = scanner.nextInt();


		System.out.print("Enter cost: ");
		double cost = scanner.nextDouble();


		Product newProduct = null;
		try {
			newProduct = new Product(productName, weight, cost);
		} catch (MiBayException e) {
			System.err.println(e);
			System.out.println();
			MainMenu();
			return;
		}

		if (system.addProduct(newProduct)) {
			System.out.println(productName + " was sucessfully added to the system.");
		} else {
			System.err.println("Unable to add " + productName + " to the system.");

		}

		System.out.println();

		MainMenu();

	}

	public void preparePackage() {

		boolean platinum = false;


		System.out.println("- Prepare Package (press enter to return to the menu) -");

		if (system.customersEmpty()) {
			System.err.println("Sorry, there are no customers in the system.");
			MainMenu();
		}

		Customer[] customers = system.getCustomers();

		int customerIndex = 1;

		for (int i = 0; i < customers.length; i++) {
			System.out.println(customerIndex + ". " + customers[i].getFirstName() + " " + customers[i].getLastName());
			customerIndex++;
		}

		System.out.print("Select a customer from the list: ");
		int selectedCustomer = scanner.nextInt();
		emptyInt(selectedCustomer);
		Customer customer = customers[selectedCustomer - 1];
		
		
		
		
		

		if (system.productsEmpty()) {
			System.err.println("Sorry, there are no products in the system.");
			MainMenu();
		}

		Product[] products = system.getProducts();

		int productIndex = 1;

		for (int i = 0; i < products.length; i++) {
			System.out.println(productIndex + ". " + products[i].getName());
			productIndex++;
		}

		System.out.print("Select a product from the list: ");
		int selectedProduct = scanner.nextInt();
		emptyInt(selectedProduct);
		Product product = products[selectedProduct - 1];
		
		
		
		
		

		System.out.println("Enter the delivery date.");
		System.out.print("Enter day: ");
		int day = scanner.nextInt();
		emptyInt(day);

		System.out.print("Enter month: ");
		int month = scanner.nextInt();
		emptyInt(month);
		
		System.out.print("Enter year: ");
		int year = scanner.nextInt();
		emptyInt(year);
		
		LocalDate date = LocalDate.of(year, month, day);
		
		
		
		scanner.nextLine();
		

		System.out.print("Is this a Platinum Package? (Y / N): ");
		String packageAnswer = scanner.nextLine();

		if (packageAnswer.equalsIgnoreCase("y")) {
			platinum = true;
		}

		if (platinum) {
			System.out.print("Enter your member number: ");
			String memberNumber = scanner.nextLine();
			emptyString(memberNumber);

			PlatinumPackage addPlatPackage = null;
			try {
				addPlatPackage = new PlatinumPackage(date, customer, product, memberNumber);
			} catch (MiBayException e) {
				System.err.println(e);
				System.out.println();
				MainMenu();
				return;
			}

			System.out.print("Would you like to add another product to the package? (Y / N): ");
			String productAnswer = scanner.nextLine();

			while (productAnswer.equalsIgnoreCase("y")) {
				int productIndex2 = 1;

				for (int i = 0; i < products.length; i++) {
					System.out.println(productIndex2 + ". " + products[i].getName());
					productIndex2++;
				}
				System.out.print("Select a product from the list: ");
				int additionalProduct = scanner.nextInt();
				emptyInt(additionalProduct);
				Product extraProduct = products[additionalProduct - 1];
				addPlatPackage.addProduct(extraProduct);
				System.out.println("Product " + extraProduct.getName() + " has been added to the package successfully.");

				scanner.nextLine();
				
				System.out.print("Would you like to add another product to the package? (Y / N): ");
				productAnswer = scanner.nextLine();
			}

			system.addPlatPackage(addPlatPackage);

		} else {
			RegularPackage addRegPackage = null;
			try {
				addRegPackage = new RegularPackage(date, customer, product);
			} catch (MiBayException e) {
				System.err.println(e);
				System.out.println();
				MainMenu();
				return;
			}

			System.out.print("Would you like to add another product to the package? (Y / N): ");
			String productAnswer = scanner.nextLine();

			while (productAnswer.equalsIgnoreCase("y")) {
				int productIndex2 = 1;

				for (int i = 0; i < products.length; i++) {
					System.out.println(productIndex2 + ". " + products[i].getName());
					productIndex2++;
				}
				
				System.out.print("Select a product from the list: ");
				int additionalProduct = scanner.nextInt();
				emptyInt(additionalProduct);
				Product extraProduct = products[additionalProduct - 1];
				addRegPackage.addProduct(extraProduct);
				System.out.println("Product " + extraProduct.getName() + " has been added to the package successfully.");

				scanner.nextLine();
				
				System.out.print("Would you like to add another product to the package? (Y / N): ");
				productAnswer = scanner.nextLine();
			}

			system.addRegPackage(addRegPackage);

		}

		System.out.println("Package for " + customer.getFirstName() + " " + customer.getLastName()
				+ " was successfully prepared.");

		System.out.println();
		MainMenu();
	}

	public void displayCustomers() {
		System.out.println("- Displaying all Customers -");
		
		if (system.customersEmpty()) {
			System.err.println("There are no customers in the system.");
			System.out.println();
			MainMenu();
			
		} else {
			
			Customer[] customers = system.getCustomers();
			
			System.out.println("ID     Name       Address");
			
			for (int i = 0; i < customers.length; i++) {
				System.out.println(customers[i].getId() + "    " + customers[i].getFirstName() + " " + customers[i].getLastName() + "      " + customers[i].getAddress().toString());
			}
			
			System.out.println();
			MainMenu();
			
		}
		
		
		
	}

	public void displayProducts() {
		System.out.println("- Displaying all Products -");
		
		if (system.productsEmpty()) {
			System.err.println("There are no products in the system.");
			System.out.println();
			MainMenu();
		} else {
			
			Product[] products = system.getProducts();
			
			System.out.println("Name         Weight   Cost");
			
			for (int i = 0; i < products.length; i++) {
				System.out.println(products[i].toString());
			}
		}
		
		System.out.println();
		MainMenu();
		
	}

	public void displayPackages() {

		if (system.regPackagesEmpty() && system.platPackagesEmpty()) {
			System.err.println("There are no packages in the system.");
			System.out.println();
			MainMenu();
		}
		
		System.out.println("------------------------");
		
		if (!system.regPackagesEmpty()) {
			
			
			RegularPackage[] regPackages = system.getRegularPackages();
			
			for (int i = 0; i < regPackages.length; i++) {
				
				System.out.println("Regular Package");
				
				LocalDate date = regPackages[i].getDate();
				String dateStr = date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
				
				Customer customer = regPackages[i].getCustomer();
				Product[] products = regPackages[i].getProducts();
				
				Address address = customer.getAddress();
				
				System.out.println("Name:     " + customer.getFirstName() + " " + customer.getLastName());
				
				System.out.println("Address:  " + address.toString());
				
				System.out.println();
				
				System.out.println("Delivery Date: " + dateStr);
				
				System.out.println("Total Cost: $" + regPackages[i].getCost());
				
				System.out.println("Products Ordered");
				
				System.out.println("Name         Weight   Cost");
				
				for (int j = 0; j < products.length; j++) {
					System.out.println(products[j].toString());
				}
				
				System.out.println("------------------------");
				
			}
			
		} 
		
		if (!system.platPackagesEmpty()) {
		
			PlatinumPackage[] platPackages = system.getPlatinumPackages();
			
			for (int i = 0; i < platPackages.length; i++) {
				
				System.out.println("Platinum Package");
				
				LocalDate date = platPackages[i].getDate();
				String dateStr = date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
				
				Customer customer = platPackages[i].getCustomer();
				Product[] products = platPackages[i].getProducts();
				
				Address address = customer.getAddress();
				
				System.out.println("Name:     " + customer.getFirstName() + " " + customer.getLastName());
				
				System.out.println("Address:  " + address.toString());
				
				System.out.println();
				
				System.out.println("Member Number: " + platPackages[i].getMemberNumber());
				
				System.out.println("Delivery Date: " + dateStr);
				
				System.out.println("Total Cost: $" + String.format("%.2f", platPackages[i].getCost()));
				
				System.out.println("Name         Weight   Cost");
				
				for (int j = 0; j < products.length; j++) {
					System.out.println(products[j].toString());
				}
				
				System.out.println("------------------------");
			}
			
			
			
		}
		
		System.out.println();
		MainMenu();
		
	}

	public void seedData() throws MiBayException {
		
		if (system.customersEmpty() && system.productsEmpty() && system.regPackagesEmpty() && system.platPackagesEmpty()) {
			
			
			
			Address address1 = new Address("83 Dalgliesh Street", "South Yarra", 3141);
			Customer customer1 = new Customer(1, "Matthew", "Bolger", address1);
			
			Address address2 = new Address("42 Pride Avenue", "Elwood", 3184);
			Customer customer2 = new Customer(2, "Gayan", "Wijesinghe", address2);
			
			system.addCustomer(customer1);
			system.addCustomer(customer2);
			
			Product product1 = new Product("The Lion King", 320, 17.99);
			Product product2 = new Product("Man of Steel", 300, 24.99);
			Product product3 = new Product("Avengers", 500, 30.00);
			Product product4 = new Product("Inception", 300, 20.50);
			
			system.addProduct(product1);
			system.addProduct(product2);
			system.addProduct(product3);
			system.addProduct(product4);
			
			LocalDate date1 = LocalDate.of(2021, 7, 15);
			LocalDate date2 = LocalDate.of(2021, 7, 15);
			
			RegularPackage regPackage = new RegularPackage(date1, customer1, product1);
			
			PlatinumPackage platPackage = new PlatinumPackage(date2, customer2, product2, "M1234");
			platPackage.addProduct(product3);
			
			system.addRegPackage(regPackage);
			system.addPlatPackage(platPackage);
			
			System.out.println("Customer, product and package data have been seeded.");
			
			System.out.println();
			
			MainMenu();
			
		} else {
			System.err.println("Customers, products or packages already exist, seeding aborted.");
		}
		
	}

	public void exit() {
		System.out.println("Program ending.");
		scanner.close();
		System.exit(1);
	}

}
