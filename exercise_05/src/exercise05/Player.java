package exercise05;

public class Player {
	private String name;
	private String symbol;
	private Position position;
	private String goalPosition;

	// Constructors

	public Player(String name, String symbol, Position startPosition, String goalPosition) {
		this.name = name;
		this.symbol = symbol;
		this.position = startPosition;
		this.goalPosition = goalPosition;
	}

	// Moves
	public Position moveRight() {
		return new Position(position.x, position.y + 1);
	}

	public Position moveLeft() {
		return new Position(position.x, position.y - 1);
	}

	public Position moveUp() {
		return new Position(position.x - 1, position.y);
	}

	public Position moveDown() {
		return new Position(position.x + 1, position.y);
	}

	// Getters

	public Position getPosition() {
		return position;
	}

	// Setters

	public void setPosition(Position position) {
		this.position = position;
	}

	// Standard Helpers

	public boolean equals(Player otherPlayer) {
		return this.name.equals(otherPlayer.name) &&
				this.symbol.equals(otherPlayer.symbol) &&
				this.position.equals(otherPlayer.position) &&
				this.goalPosition.equals(otherPlayer.goalPosition);
	}

	public String toString() {
		return symbol;
	}

}
