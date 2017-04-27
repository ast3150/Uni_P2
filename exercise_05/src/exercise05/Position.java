package exercise05;

/**
 * Represents a 2D coordinate
 *
 * Created by ast on 30.03.17.
 */
public class Position {
	protected int row;
	protected int col;

	public Position(int row, int col) {
		this.row = row;
		this.col = col;
	}

	/**
	 * Moves the position in the indicated direction, if valid
	 * @param direction A char indicating the direction to move in
	 * @param maxRow The maximum row, normally limited by the bounds of the game board
	 * @param maxCol The maximum column, normally limited by the bounds of the game board
	 * @return The new position
	 * @throws InvalidMoveException If the move would exceed the maxRow or maxCol bounds
	 */
	public Position move(char direction, int maxRow, int maxCol) throws InvalidMoveException {
		switch (direction) {
			case 'L': return moveLeft(maxRow, maxCol);
			case 'R': return moveRight(maxRow, maxCol);
			case 'U': return moveUp(maxRow, maxCol);
			case 'D': return moveDown(maxRow, maxCol);
			default:
				throw new InvalidMoveException();
		}
	}

	public Position moveRight(int maxRow, int maxCol) throws InvalidMoveException {
		if (row < 0 || row > maxRow || col < 0 || col >= maxCol)
			throw new InvalidMoveException();
		return new Position(row, col + 1);
	}

	public Position moveLeft(int maxRow, int maxCol) throws InvalidMoveException {
		if (row < 0 || row > maxRow || col <= 0 || col > maxCol)
			throw new InvalidMoveException();
		return new Position(row, col - 1);
	}

	public Position moveUp(int maxRow, int maxCol) throws InvalidMoveException {
		if (row <= 0 || row > maxRow || col < 0 || col > maxCol)
			throw new InvalidMoveException();
		return new Position(row - 1, col);
	}

	public Position moveDown(int maxRow, int maxCol) throws InvalidMoveException {
		if (row < 0 || row >= maxRow || col < 0 || col > maxCol)
			throw new InvalidMoveException();
		return new Position(row + 1, col);
	}

	// Standard Helpers

	@Override
	public boolean equals(Object otherObject) {
		Position otherPosition = (Position) otherObject;
		return this.row == otherPosition.row && this.col == otherPosition.col;
	}

	@Override
	public int hashCode() {
		int result = 1;
		result = 31 * result + row;
		result = 31 * result + col;
		return result;
	}
}
