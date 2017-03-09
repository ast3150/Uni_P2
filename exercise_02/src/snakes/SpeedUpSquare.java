package snakes;

/*
 * This is the SpeedUpSquare class
 * The methods are inherited from Square and overridden to specify this sub-class
 * If a player is on a SpeedUpSquare his diced number geds doubled
 *
 * @author Samuel Schwegler 16-119-695
 * @author Alain Stulz
 *
 * @param game							Game instance of the Game class
 * @param position						int value of actual position
 *
 * @see Square							The mother-class
 * @see Game							Game class
 * @see squareLabel()					Method changes the look of SpeedUpSquare on the board.
 * @see moveAndLand()					Method sends the player to the double of the diced count.
 */

public class SpeedUpSquare extends Square {
	public SpeedUpSquare(Game game, int position) {
		super(game, position);
	}

	@Override
	public String squareLabel() {
		return String.format("%d (SpeedUp)", position);
	}

	@Override
	public ISquare moveAndLand(int moves) {
		return game.findSquare(position, moves*2).landHereOrGoHome();
	}
}