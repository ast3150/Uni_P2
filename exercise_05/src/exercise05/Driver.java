package exercise05;

import java.text.ParseException;
import java.util.Scanner;

public class Driver implements IDriver {
	static Parser parser = new Parser();
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
		System.exit(1); // Normally exits from Game.end
	}

	public Driver(Game game) throws InvalidMoveException {
		this.game = game;
		game.setDriver(this);
		game.start();
	}

	public boolean invariant() {
		return game != null;
	}

	public IMove readNextMove(Boolean wasPreviousMoveInvalid) {
		IMove m;

		assert(invariant());

		try {
			if (wasPreviousMoveInvalid) {
				ServiceLocator.instance().getPrintStream().print("Invalid move. Try again: ");
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
		IRenderer renderer;
		renderer = ServiceLocator.instance().getRenderer();
		assert(invariant());
		renderer.render(game);
	}
}

