package exercise05;

/**
 * Coordinates {@link Game} creation and I/O operations.
 *
 * Created by ast on 05.04.17.
 */
public interface IDriver {

	/**
	 * Reads next line from System input
	 * @return A String with the input
	 */
	public String readNextLine();

	/**
	 * Reads and parses the next move from the system input
	 * @param wasPreviousMoveInvalid Whether or not there was a previous move that was not valid.
	 *                                  Used to output additional info to player that the move failed.
	 * @return The parsed move that can be executed in the game
	 */
	public IMove readNextMove(Boolean wasPreviousMoveInvalid);

	/**
	 * Prints the game board to the console
	 */
	public void renderGame();
}
