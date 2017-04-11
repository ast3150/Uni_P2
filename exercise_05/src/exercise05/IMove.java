package exercise05;

import java.util.LinkedList;

/**
 * Created by ast on 05.04.17.
 */
public interface IMove {

	public Boolean isValidFor(Tile[][] board, Player currentPlayer, LinkedList<Player> players);
	public Boolean execute(Tile[][] board, Player currentPlayer, LinkedList<Player> players) throws InvalidMoveException;

}
