package exercise05;

import java.text.ParseException;
import java.util.Scanner;

/**
 * Created by ast on 05.04.17.
 */
public class Driver implements IDriver{
	static String CONFIGURATION_PATH = "games/config.txt";
	static Parser parser = new Parser();
	static Renderer renderer = new Renderer();

	Game game;

	public static void main(String[] args) throws Exception {
		Game game;

		try {
			game = parser.parseGameFromFile(CONFIGURATION_PATH);
		} catch (Exception e) {
			System.err.println("Configuration error: " + e.getMessage());
			throw e;
		}

		assert (game != null);

		Driver driver = new Driver(game);
		System.exit(0);
	}

	public Driver(Game game) {
		this.game = game;
		game.setDriver(this);
		game.start();
	}

	// IDriver
	public IMove readNextMove() {
		IMove m;

		while (true) {
			String input = readNextLine();

			try {
				m = parser.parseMoveFromLine(input);
				break;
			} catch (ParseException e) {
				System.out.print("Invalid move. Try again: ");
				continue;
			}

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

