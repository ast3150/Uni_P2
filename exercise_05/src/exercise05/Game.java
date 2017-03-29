package exercise05;


import java.util.ArrayList;

/**
 * Represents a Quoridor game.
 */
public class Game {
	protected Player[] players;
	protected Tile[][] board;

	public Game(Player[] players, Tile[][] board) {
		this.players = players;
		this.board = board;
	}

	public Player[] getPlayers() {
		return this.players;
	}

	public Tile[][] getBoard() {
		return this.board;
	}
}
