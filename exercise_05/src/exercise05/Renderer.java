package exercise05;

/**
 * Renders a {@link Game} object.
 */
public class Renderer {

	/**
	 * Prints and returns the current state of a {@link Game}
	 * @param game the game to be rendered
	 * @return a string representation of the game, that was also printed to command line
	 */
	public static String render(Game game) {
		String s = "";
		System.out.println();

		for (Tile[] row : game.getBoard()) {
			System.out.println(convertToString(row));
			s += convertToString(row) + "\n";
		}

		return s;
	}

	/**
	 * Creates a string representation of a {@link Game} board row.
	 * @param row A row of a game board
	 * @return a string representatino of the row
	 */
	public static String convertToString(Tile[] row) {
		assert(row.length > 0);

		String s = "";

		for (Tile t : row) {
			s += t.toString();
		}

		return s;
	}

}