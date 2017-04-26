package exercise05;

import java.text.ParseException;
import java.util.ArrayList;

/**
 * A single element of a {@link Game} board.
 */
public class Tile {
	protected Character playerSymbol;

	public Tile() {}

	public Tile(Character playerSymbol) {
		this.playerSymbol = playerSymbol;
	}

	// Moves

	/**
	 *
	 * @return true if the square is not a wall and currently has no player
	 */
	public boolean canMoveHere() {
		return this.playerSymbol == null;
	}

	/**
	 * Sets the value of player in the tile.
	 * @invariant Move should be a valid move
	 * @param player The player to be moved to this Tile
	 */
	public void moveHere(Player player) {
		assert canMoveHere();
		this.playerSymbol = player.getSymbol();
	}

	public void removePlayer() {
		this.playerSymbol = null;
	}

	/**
	 *
	 * @return A boolean indicating whether the player currently on this tile (if existing) has won the game
	 */
	public Boolean isInWinningPosition() {
		if (this.playerSymbol == null) { return false; }
		return isWinningTileFor(this.playerSymbol);
	}

	/**
	 *
	 * @param playerSymbol The symbol of the player to check whether he has won or would win by moving to this tile
	 * @return True if this tile is a winning tile for the player
	 */
	public Boolean isWinningTileFor(Character playerSymbol) {
		return false;
	}

	// Getters

//	public Player getPlayer() {
//		return player;
//	}

	// Standard Helpers

	@Override
	public boolean equals(Object otherObject) {
		if (!(otherObject instanceof Tile)) {
			return false;
		}

		if (this.playerSymbol != null && ((Tile)otherObject).playerSymbol != null) {
			return this.playerSymbol.equals(((Tile)otherObject).playerSymbol);
		}

		return true;
	}

	@Override
	public String toString() {
		return playerSymbol != null ? playerSymbol.toString() : " ";
	}
}
