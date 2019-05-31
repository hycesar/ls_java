package pkg;

import java.text.NumberFormat;
import java.util.Comparator;
import java.util.Locale;

public class Product implements Comparable<Product>{
	String name = null;
	String brand = null;
	float price = 0;
	int qnt = 0;
	
	public Product(String[] product) {
		this.name = product[0];
		this.brand = product[1];
		this.price = Float.parseFloat(product[2]);
		this.qnt = Integer.parseInt(product[3]);
	}
	
	public Product toArray() {
		return this;
	}
	
	public String toString() {
		return this.name + ", " + NumberFormat.getCurrencyInstance(Locale.getDefault()).format(this.price) + "\n";
	}
	
	@Override
	public int compareTo(Product o) {
		return Comparators.PRICE.compare(this, o);
	};
	
	public static class Comparators {
		public static Comparator<Product> PRICE = new Comparator<Product>() {
            @Override
            public int compare(Product p1, Product p2) {
            	return Float.compare(p2.price, p1.price);
            }
        };
	}
}
