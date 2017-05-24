package exercise_10;

/**
 * Created by ast on 24.05.17.
 */
public abstract class Discount {
	private Discount successor = null;

	public Boolean hasSuccessor() {
		return this.successor == null;
	}

	public void setSuccessor(Discount successor) {
		this.successor = successor;
	}

	public Discount getSuccessor() {
		return this.successor;
	}

	public abstract Boolean canApplyDiscountTo(BookOrder order);

	public abstract Double applyDiscountTo(Double price) throws ArithmeticException;
}
