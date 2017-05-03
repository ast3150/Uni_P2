package exercise05;

import java.util.LinkedList;

/**
 * Represents an action performed by a player that alters the state of the game board or the players.
 *
 * Created by ast on 05.04.17.
 */
public interface IMove {

	/**
	 * Checks whether a move is valid or not.
	 *
	 * @param board         The board to check
	 * @param currentPlayer The player that took the turn
	 * @param players All players in the game
	 * @return true if the position is on the board, and the move does not violate game rules
	 */
	public Boolean isValidFor(Tile[][] board, Player currentPlayer, LinkedList<Player> players);

	/**
	 * Executes the move, altering the board and players to represent the new state
	 *
	 * @precondition The move must be valid, checked with IMove.isValidFor()
	 * @param board The board to execute the move on
	 * @param currentPlayer The player that took the turn
	 * @param players All players in the game
	 * @return Whether currentPlayer has won the game with this move
	 * @throws InvalidMoveException
	 */
	public Boolean execute(Tile[][] board, Player currentPlayer, LinkedList<Player> players) throws InvalidMoveException;

}
