package snakes;


/*
 * this is the firtsSquare class
 * the methods are inherited from Square and overridden to specify this sub-class
 * here starts the game
 *
 * @author Samuel Schwegler 16-119-695
 * @author Alain Stulz
 *
 * @param players						represents the list of players
 * @param position						int value of actual position
 *
 * @see Square							The mother-class
 * @see isOccupied						This field cant be occupied because its the start
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
