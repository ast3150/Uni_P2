package exercise05;


import com.sun.javaws.exceptions.InvalidArgumentException;

/**
 * Represents a Quoridor game.
 */
public class Game {
	private Player[] players;
	private Tile[][] board;

	// Constructors

	public Game(Player[] players, Position boardSize) {
		this.players = players;
		this.board = generateEmptyBoard(boardSize);

		setPlayersToInitialPosition(this.players);
	}

	public Tile[][] generateEmptyBoard(Position size) {
		assert(size.x > 0);
		assert(size.y > 0);

		Tile[][] board = new Tile[size.x+2][size.y+2];
		for (int row = 0; row < board.length; row++) {
			for (int col = 0; col<board[row].length; col++) {
				board[row][col] = new Tile();
			}
		}

		// Set up top wall
		for (Tile t : board[0]) {
			t.setIsWall(true);
		}

		// Set up bottom wall
		for (Tile t : board[board.length-1]) {
			t.setIsWall(true);
		}

		// Set up left and right walls
		for (Tile[] row : board) {
			for (int col = 0; col < row.length; col += row.length - 1) {
				row[col].setIsWall(true);
			}
		}

		return board;
	}

	public void setPlayersToInitialPosition(Player[] players) {
		for (Player p : players) {
			Position startPosition = p.getPosition();
			board[startPosition.x][startPosition.y].moveHere(p);
		}
	}

	// Moves

	public boolean isValidMove(Position to) {
		boolean valid = true;
		valid = (board.length > to.x) && valid;
		valid = (board[to.x].length > to.y) && valid;

		valid = (board[to.x][to.y].canMoveHere()) && valid;
		return valid;
	}

	public void movePlayer(Player player, Position to) throws InvalidArgumentException {
		if (! isValidMove(to)) {
			String s = "Invalid move for Player " + player.toString();
			String[] strArr = { s };
			throw new InvalidArgumentException(strArr);
		}

		board[player.getPosition().x][player.getPosition().y].removePlayer();
		board[to.x][to.y].moveHere(player);
		player.setPosition(to);
	}

	// Getters

	public Player[] getPlayers() {
		return this.players;
	}

	public Tile[][] getBoard() {
		return this.board;
	}
}
