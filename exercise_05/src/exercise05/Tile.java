package exercise05;

public class Tile {
	private boolean isWall = false;
	private Player player;

	// Moves

	public boolean canMoveHere() {
		return !this.isWall && this.player == null;
	}

	public void moveHere(Player player) {
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
