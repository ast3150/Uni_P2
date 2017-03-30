package exercise05;

/**
 * A single element of a {@link Game} board.
 */
public class Tile {
	private boolean isWall = false;
	private Player player;

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
	 */
	public void moveHere(Player player) {
		assert canMoveHere();

		this.player = player;
	}

	public void removePlayer() {
		this.player = null;
	}

	// Getters

	public Player getPlayer() {
		return player;
	}

	// Setters

	public void setIsWall(Boolean isWall) {
		this.isWall = isWall;
	}

	// Standard Helpers

	public boolean equals(Tile otherTile) {
		boolean equals = true;
		equals &= this.isWall == otherTile.isWall;
		if (this.player != null && otherTile.player != null) {
			equals &= this.player.equals(otherTile.player);
		}

		return equals;
	}

	public String toString() {
		if (player != null) {
			return player.toString();
		}

		if (isWall) {
			return "#";
		}

		return " ";
	}
}
