package snakes;

/**
 * from here a player moves the half diced amount
 *
 * @author Samuel Schwegler 16-119-695
 * @author Alain Stulz 16-119-414
 */

public class SlowDownSquare extends Square {
	public SlowDownSquare(Game game, int position) {
		super(game, position);
	}

	@Override
	public String squareLabel() {
		return String.format("%d (SlowDown)", position);
	}

	@Override
	public ISquare moveAndLand(int moves) {
		return game.findSquare(position, moves/2 >= 1 ? moves/2 : 1).landHereOrGoHome();
	}
}
