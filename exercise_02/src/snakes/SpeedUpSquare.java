package snakes;

public class SpeedUpSquare extends Square {
	public SpeedUpSquare(Game game, int position) {
		super(game, position);
	}

	@Override
	public ISquare moveAndLand(int moves) {
		return game.findSquare(position, moves*2).landHereOrGoHome();
	}
}