package snakes;

/*
 * This is the RollBackSquare class
 * The methods are inherited from Square and overridden to specify this sub-class
 * If a player is on a RollBackSquare-field he moves backwards
 *
 * @author Samuel Schwegler 16-119-695
 * @author Alain Stulz
 *
 * @param game							Game instance of the Game class
 * @param position						int value of actual position
 *
 * @see Square							The mother-class
 * @see Game							Game class
 * @see squareLabel()					Method changes the look of WormholeExits on the board.
 * @see isWormholeExit()				Method sets the type of this square to WormholeExit
 */

public class WormholeExit extends Square {

	public WormholeExit(Game game, int position) {
		super(game, position);
	}

	@Override
	protected String squareLabel() {
		return position + " (Exit)";
	}

	@Override
	public boolean isWormholeExit() {
		return true;
	}
}
