package exercise05;

public class Player {
	protected String name;
	protected String symbol;
	protected int startX;
	protected int startY;
	protected String goalPosition;

	public Player(String name, String symbol, int startX, int startY, String goalPosition) {
		this.name = name;
		this.symbol = symbol;
		this.startX = startX;
		this.startY = startY;
		this.goalPosition = goalPosition;
	}

	public boolean equals(Player otherPlayer) {
		return this.name.equals(otherPlayer.name) &&
				this.symbol.equals(otherPlayer.symbol) &&
				this.startX == otherPlayer.startX &&
				this.startY == otherPlayer.startY &&
				this.goalPosition.equals(otherPlayer.goalPosition);
	}
}
