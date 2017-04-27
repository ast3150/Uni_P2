package exercise05;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Represents a Quoridor game.
 */
public class Game {
	private Queue<Player> players;
	private Tile[][] board;
	private IDriver driver;
	private Boolean isOver = false;

	// Constructors

	public Game(Player[] players, Tile[][] board) {
		this.players = createPlayerQueue(players);
		this.board = board;
	}

	public Queue<Player> createPlayerQueue(Player[] players) {
		Queue<Player> q = new LinkedList<Player>();

		for (Player p : players) {
			q.add(p);
		}

		return q;
	}

	// Main Loop
	public void start() {
		driver.renderGame();

		while(!this.isOver()) {
			takeNextTurn();
		}
	}

	public void end(Player winner) {
		System.out.println("\n" + winner.toString() + " has won the game!");
		System.exit(0);
	}

	public void takeNextTurn() {
		IMove move = readNextMove(currentPlayer(), false);
		execute(move, currentPlayer());

		passTurnToNextPlayer();
	}

	public IMove readNextMove(Player currentPlayer, Boolean wasPreviousMoveInvalid) {
		IMove move;

		System.out.print(currentPlayer.toString() + ": ");

		do {
			move = driver.readNextMove(wasPreviousMoveInvalid);
			wasPreviousMoveInvalid = true; // If loop runs more than once, this should be true
		} while (!move.isValidFor(board, currentPlayer, getPlayers()));

		return move;
	}

	public void execute(IMove move, Player currentPlayer) {
		try {
			this.isOver = move.execute(board, currentPlayer, getPlayers());
		} catch (InvalidMoveException e) {
			readNextMove(currentPlayer, true);
		}

		driver.renderGame();

		if (isOver) {
			end(currentPlayer);
		}
	}

	public Player currentPlayer() {
		return players.peek();
	}

	public void passTurnToNextPlayer() { players.add(players.remove()); }

	// Getters

	public LinkedList<Player> getPlayers() {
		return (LinkedList<Player>) this.players;
	}

	public Tile[][] getBoard() {
		return this.board;
	}

	public Boolean isOver() {
		return this.isOver;
	}

	// Setters

	public void setDriver(IDriver driver) {
		this.driver = driver;
	}
}
