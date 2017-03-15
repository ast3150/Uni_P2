package snakes;


/**
 * Here starts the game. If a player has to go home he lands here again.
 *
 * @author Samuel Schwegler 16-119-695
 * @author Alain Stulz 16-119-414
 */


import java.util.ArrayList;
import java.util.List;

public class FirstSquare extends Square {

	private List<Player> players;

	public FirstSquare(Game game, int position) {
		super(game, position);
		players = new ArrayList<Player>();
	}

	public ISquare landHereOrGoHome() {
		return this;
	}

	@Override
	public boolean isOccupied() {
		return !players.isEmpty();
	}

	@Override
	public void enter(Player player) {
		assert !players.contains(player);
		players.add(player);
	}

	@Override
	public void leave(Player player) {
		assert players.contains(player);
		players.remove(player);
	}

	@Override
	public boolean isFirstSquare() {
		return true;
	}

	@Override
	protected String player() {
		StringBuffer buffer = new StringBuffer();
		for (Player player : players) {
			buffer.append("<" + player + ">");
		}
		return buffer.toString();
	}
}
