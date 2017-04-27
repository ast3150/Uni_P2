package exercise05;

/**
 * Represents a single player; holds the player's name, symbol. current position on the board and number of remaining wall moves.
 */
public class Player {
	private String name;
	private Character symbol;
	private Position position;

	private int numberOfWalls;

	// Constructors

	public Player(String name, Character symbol, int numberOfWalls) {
		this.name = name;
		this.symbol = symbol;
		this.numberOfWalls = numberOfWalls;
	}

	// Getters
	public Character getSymbol() {
		return symbol;
	}

	public Position getPosition() { return position; }

	public int getNumberOfWalls() { return numberOfWalls; }

	// Setters

	public void setPosition(Position position) {
		this.position = position;
	}

	public void decrementNumberOfWalls() { numberOfWalls--; }

	// Standard Helpers
	@Override
	public String toString() {
		return this.name;
	}

	@Override
	public boolean equals(Object otherObject) {
		Player otherPlayer = (Player) otherObject;
		boolean equals = true;

		equals &= this.name.equals(otherPlayer.name);
		equals &= this.symbol.equals(otherPlayer.symbol);

		if (this.position != null && otherPlayer.position != null) {
			equals &= this.position.equals(otherPlayer.position);
		}

		return equals;
	}

}
