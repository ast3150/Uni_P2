package exercise_10;

/**
 * Created by ast on 24.05.17.
 */
public abstract class PercentDiscount extends Discount {

	public abstract Double getPercent();

	@Override
	public Double applyDiscountTo(Double price) throws ArithmeticException {
		if (getPercent() > 100) {
			throw new ArithmeticException("Discount can not exceed 100%");
		}
		return price * (100 - getPercent());
	}
}
