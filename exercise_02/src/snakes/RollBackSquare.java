package snakes;

/*
 * player moves the diced count backwards
 *
 * @author Samuel Schwegler 16-119-695
 * @author Alain Stulz  16-119-414
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
