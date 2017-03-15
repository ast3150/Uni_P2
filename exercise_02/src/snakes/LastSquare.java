package snakes;

/*
 * the target of the game is to land here
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
