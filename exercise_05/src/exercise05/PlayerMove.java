package exercise05;

import java.util.LinkedList;

public class PlayerMove implements IMove {
	private char direction;

	public PlayerMove(char direction) {
		this.direction = direction;
	}

	public Boolean isValidFor(Tile[][] board, Player currentPlayer, LinkedList<Player> players) {
		try {
			return isValidFor(board, currentPlayer.getPosition().move(direction, board.length-1, board[0].length-1));
		} catch (InvalidMoveException e) {
			return false;
		}
	}

	public Boolean isValidFor(Tile[][] board, Position to) {
		boolean valid = true;
		valid &= (board.length > to.row);
		valid &= (board[to.row].length > to.col);

		valid &= (board[to.row][to.col].canMoveHere());
		return valid;
	}

	public Boolean execute(Tile[][] board, Player currentPlayer, LinkedList<Player> players) throws InvalidMoveException {
		Position to = currentPlayer.getPosition().move(direction, board.length-1, board[0].length-1);

		if (!isValidFor(board, currentPlayer, players)) {
			throw new InvalidMoveException();
		}

		Position from = currentPlayer.getPosition();
		currentPlayer.setPosition(to);
		board[from.row][from.col].removePlayer();
		board[to.row][to.col].moveHere(currentPlayer);

		return board[to.row][to.col].isInWinningPosition();
	}

	public char getDirection() {
		return direction;
	}
}