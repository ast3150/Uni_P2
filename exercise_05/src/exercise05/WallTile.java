package exercise05;

/**
 * Created by ast on 17.04.17.
 */
public class WallTile extends Tile {

	/**
	 *
	 * @return false since a wall can never be moved to
	 */
	@Override
	public boolean canMoveHere() {
		return false;
	}

	// Standard Helpers

	@Override
	public boolean equals(Object otherObject) {
		return (otherObject instanceof WallTile);
	}

	@Override
	public String toString() {
		return "#";
	}
}
