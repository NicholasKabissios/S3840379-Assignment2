package mibay;

import mibay.exceptions.MiBayException;

public class Product {

	private String name;
	private int weight;
	private double cost;

	public Product(String name, int weight, double cost) throws MiBayException {

		if (name.isEmpty() || name.equals("")) {
			throw new MiBayException("Empty name entered.");
		} else {
			this.name = name;
		}
		
		if (weight <= 0) {
			throw new MiBayException("Entered weight is too small.");
		} else {
			this.weight = weight;
		}
		
		if (cost <= 0) {
			throw new MiBayException("Entered cost is too small.");
		} else {
			this.cost = cost;
		}

	}

	public double getCost() {
		return cost;
	}

	public String getName() {
		return name;
	}

	public String toString() {

		String product = (name + "      " + weight + "g   $" + cost);

		return product;
	}

}
