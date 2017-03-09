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
 * @see moveAndLand()					Method sends the player to the negative diced count.
 */


public class RollBackSquare extends Square {
	public RollBackSquare(Game game, int position) {
		super(game, position);
	}

	@Override
	public String squareLabel() {
		return String.format("%d (RollBack)", position);
	}

	@Override
	public ISquare moveAndLand(int moves) {
		return super.moveAndLand(moves * -1);
	}
}
