package exercise05;

import javafx.geometry.Pos;

/**
 * Created by ast on 30.03.17.
 */
public class Position {
	protected int x;
	protected int y;

	public Position(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public boolean equals(Position otherPosition) {
		return this.x == otherPosition.x && this.y == otherPosition.y;
	}
}
