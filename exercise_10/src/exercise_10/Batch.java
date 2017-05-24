package exercise_10;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ast on 24.05.17.
 */
public class Batch implements IOrderItem {
	private String name = "";
	private List<IOrderItem> childItems = new ArrayList<IOrderItem>();

	public Batch(String name) {
		this.name = name;
	}

	public void addBook(Book book) {
		childItems.add(book);
	}

	/* Nested batches are also possible! */
	public void addBatch(Batch batch) {
		childItems.add(batch);
	}

	@Override
	public int getQuantity() {
		return 1;
	}

	@Override
	public Double getPrice() {
		Double price = 0.0;

		for (IOrderItem item : childItems) {
			price += item.getPrice();
		}

		return price;
	}

	@Override
	public List<String> getDescription() {
		List description = new ArrayList<String>();

		for (IOrderItem item : childItems) {
			description.add(item.getDescription());
		}

		return description;
	}

	@Override
	public String getDetailDescription() {
		String description = "Batch '" + this.name + "'\n\n";

		StringBuilder sb = new StringBuilder(description);

		for (IOrderItem item : childItems) {
			sb.append(item.getDetailDescription().replaceAll("(?m)^", "\t") + "\n\n");
		}

		return sb.toString();
	}
}
