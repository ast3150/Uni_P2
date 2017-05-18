package exercise05;

/**
 * Created by samuel on 16.05.17.
 */
public class SilentRenderer implements IRenderer {
    /**
     * Prints and returns the current state of a {@link Game}
     * @param game the game to be rendered
     * @return a string representation of the game, that was also printed to command line
     */
    @Override
    public String render(Game game) {
        String s = "";

        for (Tile[] row : game.getBoard()) {
            s += convertToString(row) + "\n";
        }

        return s;
    }

    /**
     * Creates a string representation of a {@link Game} board row.
     * @param row A row of a game board
     * @return a string representatino of the row
     */
    public String convertToString(Tile[] row) {
        assert(row.length > 0);

        String s = "";

        for (Tile t : row) {
            s += t.toString();
        }

        return s;
    }
}
