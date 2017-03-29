package snakes;

/**
 * A die for rolling random numbers
 */
public interface IDie {
	/**
	 * @return the diced amount
	 */
	int roll();
	int getFaces();
}
