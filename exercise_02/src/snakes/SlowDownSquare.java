package snakes;

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
