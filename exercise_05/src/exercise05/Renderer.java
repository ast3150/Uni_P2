package exercise05;

/**
 * Renders a {@link Game} object.
 */
public class Renderer {

	public static String render(Game game) {
		String s = "";

		for (Tile[] row : game.getBoard()) {
			System.out.println(convertToString(row));
			s += convertToString(row) + "\n";
		}

		return s;
	}

	public static String convertToString(Tile[] row) {
		assert(row.length > 0);

		String s = "";

		for (Tile t : row) {
			s += t.toString();
		}

		return s;
	}

}