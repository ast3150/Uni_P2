package snakes;

/*
 * this is the LastSquare class
 * the methods are inherited from Square and overridden to specify this sub-class
 *
 * @author Samuel Schwegler 16-119-695
 * @author Alain Stulz
 *
 * @param game							Game instance of the Game class
 * @param position						int value of actual position
 *
 * @see Square							The mother-class
 * @see Game							Game class
 * @see isLastSquare()					Method to defines the target of the game, to land on the last square
 */


public class LastSquare extends Square {

	public LastSquare(Game game, int position) {
		super(game, position);
	}

	@Override
	public boolean isLastSquare() {
		return true;
	}
}
