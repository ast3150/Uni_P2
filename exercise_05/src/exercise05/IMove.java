package exercise05;

import java.util.LinkedList;

/**
 * Created by ast on 05.04.17.
 */
public interface IMove {

	/**
	 * Checks whether a move is valid or not
	 *
	 * @param board         The board to check
	 * @param currentPlayer The player to check
	 * @param players All players in the game
	 * @return true if the position is on the board, and the tile can be moved to
	 */
	public Boolean isValidFor(Tile[][] board, Player currentPlayer, LinkedList<Player> players);

	// TODO: Doc
	public Boolean execute(Tile[][] board, Player currentPlayer, LinkedList<Player> players) throws InvalidMoveException;

}
