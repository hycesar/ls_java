package pkg;

import java.util.List;

public class Methods {
	public float priceMean (List<Product> productList) {
		float sum = 0;
		int listSize = productList.size();
		if(listSize > 0) {
			for(Product i : productList) {
				sum += i.price;
			}
			sum /= listSize;
		}
		return sum;
	}
	
	public int qntAll (List<Product> productList) {
		int sum = 0;
		if(productList.size() > 0) {
			for(Product i : productList) {
				sum += i.qnt;
			}
		}
		return sum;
	}
	
	public String toHTML(Product product) {
		return "            <tr>\n                <td>"
				+ product.name + "</td>\n                <td>" 
				+ product.brand + "</td>\n                <td>"
				+ product.price + "</td>\n                <td>"
				+ product.qnt + "</td>\n            </tr>";
	}
}
