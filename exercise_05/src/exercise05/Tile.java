package exercise05;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * A single element of a {@link Game} board.
 */
public class Tile {
	private boolean isWall = false;
	private Player player;

	/**
	 * A character indicating the position of the tile
	 * "R" for a tile located on the right edge, similarly "L", "U", "D"
 	 */
	private ArrayList<Character> winningPositions = new ArrayList<Character>();

	// Moves

	/**
	 *
	 * @return true if the square is not a wall and currently has no player
	 */
	public boolean canMoveHere() {
		return !this.isWall && this.player == null;
	}

	/**
	 * Sets the value of player in the tile.
	 * @invariant Move should be a valid move
	 * @param player The player to be moved to this Tile
	 * @return A boolean indicating whether the player has won the game
	 */
	public Boolean moveHere(Player player) {
		assert canMoveHere();
		this.player = player;

		return isWinningTileFor(player);
	}

	public void removePlayer() {
		this.player = null;
	}

	public Boolean isWinningTileFor(Player player) {
		return winningPositions.contains(player.getGoalPosition());
	}

	// Getters

	public Player getPlayer() {
		return player;
	}

	public boolean isWall() {
		return isWall;
	}

	// Setters

	public void setIsWall(Boolean isWall) {
		this.isWall = isWall;
	}

	public void addWinningPosition(char pos) {
		winningPositions.add(pos);
	}

	// Standard Helpers

	@Override
	public boolean equals(Object otherObject) {
		Tile otherTile = (Tile) otherObject;
		boolean equals = true;
		equals &= this.isWall == otherTile.isWall;
		if (this.player != null && otherTile.player != null) {
			equals &= this.player.equals(otherTile.player);
		}

		return equals;
	}

	@Override
	public String toString() {
		if (player != null) {
			return player.getSymbol();
		}

		if (isWall) {
			return "#";
		}

		return " ";
	}
}
