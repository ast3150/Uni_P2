package exercise05;

import java.util.LinkedList;

public class PlayerMove implements IMove {
	private char direction;

	public PlayerMove(char direction) {
		this.direction = direction;
	}

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
		board[to.x][to.y].moveHere(currentPlayer);

		return board[to.x][to.y].isInWinningPosition();
	}

	public char getDirection() {
		return direction;
	}
}