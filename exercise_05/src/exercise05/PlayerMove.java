package exercise05;

import org.junit.Assert;

import java.util.LinkedList;

public class PlayerMove implements IMove {
	private char direction;

	public PlayerMove(char direction) {
		this.direction = direction;
	}

	/**
	 * Checks whether a move is valid or not
	 *
	 * @param board         The board to check
	 * @param currentPlayer The player to check
	 * @param players
	 * @return true if the position is on the board, and the tile can be moved to
	 */
	public Boolean isValidFor(Tile[][] board, Player currentPlayer, LinkedList<Player> players) {
		return isValidFor(board, currentPlayer.getPosition().move(direction));
	}

	public Boolean isValidFor(Tile[][] board, Position to) {
		boolean valid = true;
		valid &= (board.length > to.x);
		valid &= (board[to.x].length > to.y);

		valid &= (board[to.x][to.y].canMoveHere());
		return valid;
	}

	public Boolean execute(Tile[][] board, Player currentPlayer, LinkedList<Player> players) throws InvalidMoveException {
		Position to = currentPlayer.getPosition().move(direction);

		if (!isValidFor(board, currentPlayer, players)) {
			throw new InvalidMoveException();
		}

		Position from = currentPlayer.getPosition();
		currentPlayer.setPosition(to);
		board[from.x][from.y].removePlayer();
		return board[to.x][to.y].moveHere(currentPlayer);
	}
}