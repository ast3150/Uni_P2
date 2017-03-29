package exercise05;

import java.text.ParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Parses a Quoridor file specification and creates a {@link Game} instance.
 */
public class Parser {

	// TODO: Parse full game string and return game
	// TODO: read from file
	// TODO: generate game

	public void parseGameFromString(String s) throws ParseException {

	}

	public int[] parseBoardSizeFromLine(String l) throws ParseException {
		Matcher m = Pattern.compile("([0-9]+) ([0-9]+)").matcher(l);

		if (m.matches()) {
			throw new ParseException("Board Size must contain exactly two numeric values", 0);
		}

		assert(m.groupCount() == 2);

		int[] boardSize = {
				Integer.parseInt(m.group(0)),
				Integer.parseInt(m.group(1))
		};

		assert(boardSize.length == 2);
		return boardSize;
	}

	public Player parsePlayerFromLine(String l) throws ParseException {
		Matcher m = Pattern.compile("(.+) (.) (\\d+) (\\d+) (.)").matcher(l);

		if (! m.matches()) {
			throw new ParseException("Player did not match the required pattern. Invalid line:\n" + l, 1);
		}

		String name = m.group(1);
		String symbol = m.group(2);
		int startX = Integer.parseInt(m.group(3));
		int startY = Integer.parseInt(m.group(4));
		String goalPosition = m.group(5);

		return new Player(name, symbol, startX, startY, goalPosition);
	}
}
