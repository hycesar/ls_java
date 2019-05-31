package pkg;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

public class CsvReader {	
	public static void main(String[] args) {
		String csvFilename = System.getProperty("java.class.path").split(";")[0] + "\\pkg\\estoque.csv";
		BufferedReader content = null;
		String line = "";
		String csvSep = ";";
		
		try {
			content = new BufferedReader(new FileReader(csvFilename));
			List<Product> productList = new ArrayList<Product>();

			line = content.readLine();
			while((line = content.readLine()) != null) {
				String[] productStr = line.split(csvSep);
				
				Product product = new Product(productStr);
				productList.add(product);
			}
			
			
			Methods m = new Methods();
			
			Collections.sort(productList);
			
			System.out.println("Produtos mais caros:");

			//String formattedString = productList.toString().replace(",", "").replace("[", "").replace("]", "").trim();
			//Object formattedString = productList.toArray();
			//System.out.println(productList.toArray());

			System.out.println(productList.toString().split("\n")[0].replace("[", ""));
			System.out.println(productList.toString().split("\n")[1].replaceFirst(", ", ""));
			
			System.out.println("\n");
			
			System.out.println("Preço médio de produtos: " + NumberFormat.getCurrencyInstance(Locale.getDefault()).format(m.priceMean(productList)));

			System.out.println("Quantidade de items em estoque: " + m.qntAll(productList));
			
			PrintWriter writer = new PrintWriter(System.getProperty("java.class.path").split(";")[0] + "\\pkg\\extra.html", "UTF-8");
			writer.println("<!DOCTYPE html>\n<html>\n<head>\n    <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n    <title>Produtos</title>\n    <style>\n        html {\n            font-family: Georgia, Cambria, serif;\n            font-size: .875em;\n            background: #fff;\n            color: #373D49;\n        }\n        body { padding: 15px; }\n        table {\n            width: 100%;\n            border: 1px solid #ddd;\n            border-collapse: collapse;\n            border-spacing: 0;\n        }\n            table>thead>tr>th {\n            text-align: left;\n            border-bottom: 2px solid #ddd\n        }\n        th, td { padding: 8px; line-height: 1.4285714; }\n        table>tbody>tr:nth-child(odd)>td { background-color: #f9f9f9 }\n    </style>\n</head>\n\n<body>\n    <h1>Lista de Produtos</h1>\n    <hr>");
			
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd - HH:mm:ss");
			writer.println("    <p>Lista gerada em " + dtf.format(LocalDateTime.now()).replace("-", "às") + "</p>");
			
			writer.println("    <table>\n        <thead>\n            <tr>\n                <th>Produto</th>\n                <th>Marca</th>\n                <th>Preço</th>\n                <th>Estoque</th>\n            </tr>\n        </thead>\n        <tbody>\n            <!-- Lista de produtos -->");
			
			int sizeProd = productList.size();
			int i = 0;
			while(i < sizeProd) {
				writer.println(m.toHTML(productList.get(i)));
				i++;
			}
			
			writer.println("        </tbody>\n    </table>\n\n</body>\n</html>");
			
			writer.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			e.getMessage();
		} catch (ArrayIndexOutOfBoundsException e) {
			e.printStackTrace();
			e.getMessage();
		} catch (IOException e){
			e.printStackTrace();
			e.getMessage();
		} finally {
			if(content != null) {
				try {
					content.close();
				} catch (IOException e) {
					e.printStackTrace();
					e.getMessage();
				}
			}
		}

	}

}