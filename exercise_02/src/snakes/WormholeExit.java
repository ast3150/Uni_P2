package snakes;

/**
 * Possible exit of a wormhole. To recognise it isWormholeExit has to be overridden to true
 *
 * @see WormholeEntrance
 *
 * @author Samuel Schwegler 16-119-695
 * @author Alain Stulz 16-119-414
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
