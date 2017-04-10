package exercise05;

import java.text.ParseException;
import java.util.Scanner;

/**
 * Created by ast on 05.04.17.
 */
public class Driver implements IDriver{
	static Parser parser = new Parser();
	static Renderer renderer = new Renderer();

	Game game;

	public static void main(String[] args) throws Exception {
		Game game;
		String configurationPath = "games/config.txt";

		if (args.length > 0) {
			configurationPath = args[0];
		}

		try {
			game = parser.parseGameFromFile(configurationPath);
		} catch (Exception e) {
			System.err.println("Configuration error: " + e.getMessage());
			throw e;
		}

		assert (game != null);

		Driver driver = new Driver(game);
		System.exit(1);
	}

	public Driver(Game game) throws InvalidMoveException {
		this.game = game;
		game.setDriver(this);
		game.start();
	}

	public IMove readNextMove(Boolean wasPreviousMoveInvalid) {
		IMove m;

		try {
			if (wasPreviousMoveInvalid) {
				System.out.print("Invalid move. Try again: ");
			}

			String input = readNextLine();

			m = parser.parseMoveFromLine(input);
		} catch (ParseException e) {
			return readNextMove(true);
		}

		return m;
	}

	public String readNextLine() {
		return new Scanner(System.in).nextLine();
	}

	public void renderGame(){
		renderer.render(game);
	}
}
