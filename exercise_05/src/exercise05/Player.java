package exercise05;

public class Player {
	protected String name;
	protected String symbol;
	protected Position position;
	protected String goalPosition;

	public Player(String name, String symbol, Position startPosition, String goalPosition) {
		this.name = name;
		this.symbol = symbol;
		this.position = startPosition;
		this.goalPosition = goalPosition;
	}

	public boolean equals(Player otherPlayer) {
		return this.name.equals(otherPlayer.name) &&
				this.symbol.equals(otherPlayer.symbol) &&
				this.position.equals(otherPlayer.position) &&
				this.goalPosition.equals(otherPlayer.goalPosition);
	}
}
