package exercise_10;

import java.util.List;

public interface IOrderItem {
	public int getQuantity();

	public Double getPrice();

	public List<String> getDescription();

	public String getDetailDescription();
}
