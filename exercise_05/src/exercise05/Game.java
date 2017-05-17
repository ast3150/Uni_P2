package exercise05;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Represents a Quoridor game. Holds the game board, assigns turns to players and coordinates game logic.
 *
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
		/* TODO
		this.board = ServiceLocator.instance().getBoard();
		*/
	}

	/**
	 * Creates a queue from the passed players, used to assign turns to players
	 * @param players The players to add to the queue
	 * @return A queue with all the players
	 */
	public Queue<Player> createPlayerQueue(Player[] players) {
		Queue<Player> q = new LinkedList<Player>();

		for (Player p : players) {
			q.add(p);
		}

		return q;
	}

	/**
	 * The main loop that assigns turns to the players
	 */
	public void start() {
		driver.renderGame();

		while(!this.isOver()) {
			takeNextTurn();
		}
	}

	/**
	 * Ends a game after a player has won, and informs the players that the game is over
	 * @param winner The winner, will be passed to output to inform the players
	 */
	public void end(Player winner) {
		System.out.println("\n" + winner.toString() + " has won the game!");
		System.exit(0);
	}

	/**
	 * Takes a turn for the current player, then passes the turn to next player.
	 */
	public void takeNextTurn() {
		IMove move = readNextMove(currentPlayer(), false);
		execute(move, currentPlayer());

		passTurnToNextPlayer();
	}

	/**
	 * Prompts the player to enter a move until player enters a valid move.
	 * @param currentPlayer The player that is currently allowed to take the turn
	 * @param wasPreviousMoveInvalid Whether or not there was a previous move that was not valid.
	 *                                  Used to output additional info to player that the move failed.
	 * @return The move, ready to be executed
	 */
	public IMove readNextMove(Player currentPlayer, Boolean wasPreviousMoveInvalid) {
		IMove move;

		System.out.print(currentPlayer.toString() + ": ");

		do {
			move = driver.readNextMove(wasPreviousMoveInvalid);
			wasPreviousMoveInvalid = true; // If loop runs more than once, this should be true
		} while (!move.isValidFor(board, currentPlayer, getPlayers()));

		return move;
	}

	/**
	 * Executes the move on the player and game board, then renders the new status.
	 * Ends the game if necessary, or reads the next move if the move is invalid
	 * @param move A valid move
	 * @param currentPlayer The player that currently has the turn
	 */
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
