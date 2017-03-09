package snakes;

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
