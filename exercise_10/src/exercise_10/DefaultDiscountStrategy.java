package exercise_10;

/**
 * Created by ast on 24.05.17.
 */

public class DefaultDiscountStrategy{
	public Discount buildDiscountStrategy() {
		Discount discount1 = new Buy10BooksGet10PercentDiscount();
		Discount discount2 = new BuyOver1000CHFGet90CHFAbsoluteDiscount();
		Discount discount3 = new Buy5OrMoreSoftwareEngineeringGet5PercentDiscount();
		Discount discount4 = new OrderDuringMayGet5PercentDiscount();

		discount1.setSuccessor(discount2);
		discount2.setSuccessor(discount3);
		discount3.setSuccessor(discount4);

		return discount1;
	}
}

class Buy10BooksGet10PercentDiscount extends PercentDiscount {
	@Override
	public Double getPercent() {
		return 10.0;
	}

	@Override
	public Boolean canApplyDiscountTo(BookOrder order) {
		// TODO: Get quantity from BookOrder
		return false;
	}
}

class BuyOver1000CHFGet90CHFAbsoluteDiscount extends AbsoluteDiscount {
	@Override
	public Double getAmount() {
		return 90.0;
	}

	@Override
	public Boolean canApplyDiscountTo(BookOrder order) {
		// TODO: Get amount from BookOrder
		return false;
	}
}

class Buy5OrMoreSoftwareEngineeringGet5PercentDiscount extends PercentDiscount {
	@Override
	public Double getPercent() {
		return 5.0;
	}
	@Override
	public Boolean canApplyDiscountTo(BookOrder order) {
		// TODO: Get list of books from BookOrder
		return false;
	}
}

class OrderDuringMayGet5PercentDiscount extends PercentDiscount {
	@Override
	public Double getPercent() {
		return 5.0;
	}

	@Override
	public Boolean canApplyDiscountTo(BookOrder order) {
		// TODO: Get order date from BookOrder
		return false;
	}
}