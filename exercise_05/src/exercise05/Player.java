package exercise05;

public class Player {
	private String name;
	private String symbol;
	private Position position;
	private char goalPosition;
	private int numberOfWalls = 5;

	// Constructors

	public Player(String name, String symbol, Position startPosition, char goalPosition) {
		this.name = name;
		this.symbol = symbol;
		this.position = startPosition;
		this.goalPosition = goalPosition;
	}

	// Getters
	public String getSymbol() {
		return symbol;
	}

	public Position getPosition() {
		return position;
	}

	public char getGoalPosition() { return goalPosition; }

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
		return this.name.equals(otherPlayer.name) &&
				this.symbol.equals(otherPlayer.symbol) &&
				this.position.equals(otherPlayer.position) &&
				this.goalPosition == otherPlayer.goalPosition;
	}

}
