package exercise05;

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

	public Position move(char direction ) {
		switch (direction) {
			case 'L': return moveLeft();
			case 'R': return moveRight();
			case 'U': return moveUp();
			case 'D': return moveDown();
			default:
				System.out.println("Invalid move on Position");
				return this;
		}
	}

	public Position moveRight() {
		return new Position(x, y + 1);
	}

	public Position moveLeft() {
		return new Position(x, y - 1);
	}

	public Position moveUp() {
		return new Position(x - 1, y);
	}

	public Position moveDown() {
		return new Position(x + 1, y);
	}

	// Standard Helpers

	@Override
	public boolean equals(Object otherObject) {
		Position otherPosition = (Position) otherObject;
		return this.x == otherPosition.x && this.y == otherPosition.y;
	}

	@Override
	public int hashCode() {
		int result = 1;
		result = 31 * result + x;
		result = 31 * result + y;
		return result;
	}
}
