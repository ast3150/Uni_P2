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

	public Game(Player[] players, Position boardSize) {
		this.players = createPlayerQueue(players);
		this.board = generateEmptyBoard(boardSize);

		setPlayersToInitialPosition((LinkedList<Player>) this.players);
	}

	public Queue<Player> createPlayerQueue(Player[] players) {
		Queue<Player> q = new LinkedList<Player>();

		for (Player p : players) {
			q.add(p);
		}

		return q;
	}
	/**
	 * Generates an empty board that has a wall around it
	 * @param size the inner size of the board, without the outer wall
	 * @return An empty board of type Tile[][]
	 */
	public Tile[][] generateEmptyBoard(Position size) {
		assert(size.x > 0);
		assert(size.y > 0);

		Tile[][] board = new Tile[size.x+2][size.y+2];
		for (int row = 0; row < board.length; row++) {
			for (int col = 0; col<board[row].length; col++) {
				board[row][col] = new Tile();
			}
		}

		// Border walls

		// Set up top wall
		for (Tile t : board[0]) {
			t.setIsWall(true);
		}

		// Set up bottom wall
		for (Tile t : board[board.length-1]) {
			t.setIsWall(true);
		}

		// Set up left and right walls, left and right goal squares
		for (Tile[] row : board) {
			row[0].setIsWall(true);
			row[1].addWinningPosition('L');
			row[row.length-2].addWinningPosition('R');
			row[row.length-1].setIsWall(true);
		}

		// Set up top winning squares
		for (Tile t : board[1]) {
			t.addWinningPosition('U');
		}

		// Set up bottom winning squares
		for (Tile t : board[board.length-2]) {
			t.addWinningPosition('D');
		}

		return board;
	}

	/**
	 * Moves the players to their initial position. Should only be used for setting up a new Game.
	 * @param players The players to be placed on the board. Need to have currentPosition attribute set.
	 */
	public void setPlayersToInitialPosition(LinkedList<Player> players) {
		for (Player p : players) {
			Position startPosition = p.getPosition();
			board[startPosition.x][startPosition.y].moveHere(p);
		}
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
