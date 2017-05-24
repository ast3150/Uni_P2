package exercise_10;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class BookOrder {
	private String clientName;
	private Calendar orderDate;
	private List<IOrderItem> orderItems = new ArrayList<IOrderItem>();

	public BookOrder(String clientName, Calendar orderDate) {
		this.clientName = clientName;
		this.orderDate = orderDate;
	}

	public void addBook(Book book) {
		orderItems.add(book);
	}

	/**
	 * Create a new batch with the given name as part of this order.
	 */
	public Batch newBatch(String name) {
		Batch batch = new Batch(name);
		orderItems.add(batch);
		return batch;
	}

	private String summaryHeader(Boolean includeTotalPrice) {
		String summaryHeader = "";
		SimpleDateFormat format1 = new SimpleDateFormat("dd MMM, yyyy");
		summaryHeader += "Date: " + format1.format(orderDate.getTime()) + "\n";
		summaryHeader += "Client: " + clientName + "\n";

		int numberOfBooks = 0;
		Double totalPrice = 0.0;
		for (IOrderItem orderItem : orderItems) {
			numberOfBooks += orderItem.getQuantity();
			totalPrice += orderItem.getPrice();
		}

		// TODO: Calculate discounts here

		summaryHeader += "Number of books: " + numberOfBooks + "\n";

		if (includeTotalPrice) {
			summaryHeader += "Total Price: " + totalPrice + " CHF\n";
			// FIXME: Display discount
			summaryHeader += "Discount: " + 20000 + " CHF\n";
		}

		// FIXME: Use final price
		summaryHeader += "Final Price: " + totalPrice + " CHF\n\n";


		return summaryHeader;
	}

	public String summary() {
		String summary = "";
		summary += summaryHeader(false);

		summary += "Books\n";

		int index = 1;
		for (IOrderItem orderItem : orderItems) {
			for (String description : orderItem.getDescription()) {
				summary += index++ + "." + description;
			}
		}

		return summary;
	}

	public String details() {
		String summary = "";
		summary += summaryHeader(true);

//				"Batch 'Empty batch'\n" +
//				"\n" +
//				"Batch 'Design Patterns'\n" +
//				"\n" +
//				"\tTitle: Design patterns : elements of reusable object-oriented software\n" +
//				"    Author(s): Erich Gamma, Richard Helm, Ralph Johnson, John Vlissides\n" +
//				"    Publisher:  Addison Wesley\n" +
//				"    Publication Date: 31 October 1994\n" +
//				"    Number of pages: 416\n" +
//				"    Category: Software Engineering\n" +
//				"    Price: 40.2 CHF\n" +
//				"    Quantity: 1";

		for (IOrderItem orderItem : orderItems) {
			summary += orderItem.getDetailDescription();
		}

		return summary;
	}
}
