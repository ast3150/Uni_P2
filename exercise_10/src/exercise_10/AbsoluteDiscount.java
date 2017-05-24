package exercise_10;

/**
 * Created by ast on 24.05.17.
 */
public abstract class AbsoluteDiscount extends Discount {

	public abstract Double getAmount();

	@Override
	public Double applyDiscountTo(Double price) throws ArithmeticException {
		if (price < getAmount()) {
			throw new ArithmeticException("Discount cannot be greater than price");
		}
		return price - getAmount();
	}
}
