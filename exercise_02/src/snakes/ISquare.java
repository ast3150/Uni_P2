package snakes;

/**
 * the methods wich were implemented in other classes
 *
 * @author Samuel Schwegler 16-119-695
 * @author Alain Stulz 16-119-414
 */

public interface ISquare {
	/**
	 * @return the position of the square on the game board
	 */
	public int position();

	/**
	 * Moves the player by the number of moves passed.
	 *
	 * @param moves the number of squares the player should be moved
	 * @return the square on which the player should land
	 */
	public ISquare moveAndLand(int moves);

	/**
	 * @return true iff the square is the first square on the game board
	 */
	public boolean isFirstSquare();

	/**
	 * @return true iff the square is the last square on the game board
	 */
	public boolean isLastSquare();

	/**
	 * Sets the player value of the square, indicating that the player is currently
	 * on this square. Assumes that the square has no player currently on it.
	 *
	 * @param player the player that is moving to this square
	 */
	public void enter(Player player);

	/**
	 * Removes the player from this square, indicating that the player has moved
	 * to another square. Assumes that there is a player currently on this square.
	 *
	 * @param player
	 */
	public void leave(Player player);

	/**
	 * @return true iff there is a player currently on this square
	 */
	public boolean isOccupied();

	/**
	 * Returns self, indicating that the player can be moved to this square
	 * If occupied, returns the first square in the game, according to ruleset.
	 * Can be customized, e.g. to allow the player an extra roll on landing.
	 *
	 * @return the square that the player should land on
	 */
	public ISquare landHereOrGoHome();

	/**
	 * @return true iff the square is registered as a wormhole exit
	 */
	boolean isWormholeExit();
}
