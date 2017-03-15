package snakes;

/*
 * from here a player moves the double diced amount
 *
 * @author Samuel Schwegler 16-119-695
 * @author Alain Stulz 16-119-414
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