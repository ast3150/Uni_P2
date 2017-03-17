package snakes;

/**
 * Defines the target of the game, if a player lands here, he winns
 *
 * @author Samuel Schwegler 16-119-695
 * @author Alain Stulz 16-119-414
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
