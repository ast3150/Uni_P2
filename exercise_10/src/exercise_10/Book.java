package exercise_10;

import java.util.ArrayList;
import java.util.List;

/*
* yeah a Book
*
 */
public class Book implements IOrderItem {
	String title;
	String authors;
	double price;
	int quantity;

	public Book(String title, String authors, double price, int quantity) {
		this.title = title;
		this.authors = authors;
		this.price = price;
		this.quantity = quantity;
	}

	@Override
	public Double getPrice() {
		return this.price * this.quantity;
	}

	@Override
	public List<String> getDescription() {
		ArrayList description = new ArrayList<String>();

		String descriptionString = "";
		descriptionString += "\tTitle: " + this.title + "\n";
		descriptionString += "\tAuthor(s): " + this.authors + "\n";
		descriptionString += "\tPrice: " + this.price + " CHF\n";
		descriptionString += "\tQuantity: " + this.getQuantity();

		description.add(descriptionString);
		return description;
	}

	@Override
	public String getDetailDescription() {
		String detailDescription = "";
		detailDescription += "Title: " + this.title + "\n";
		detailDescription += "Author(s): " + this.authors + "\n";
		detailDescription += "Publisher: " + this.authors + "\n";
		detailDescription += "Publication Date: " + this.authors + "\n";
		detailDescription += "Number of pages: " + this.authors + "\n";
		detailDescription += "Category: " + this.authors + "\n";
		detailDescription += "Price: " + this.price + " CHF\n";
		detailDescription += "Quantity: " + this.getQuantity();

		return detailDescription;
	}

	public int getQuantity() {
		return quantity;
	}
}
