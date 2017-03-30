package exercise05;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.nio.file.Files.readAllBytes;
import static java.nio.file.Paths.get;

/**
 * Parses a Quoridor file specification and creates a {@link Game} instance.
 */
public class Parser {

	public Game parseGameFromFile(String path) throws ParseException {
		String stringFromFile = "";
		try {
			stringFromFile = new String(readAllBytes(get(path)));
		}
		catch(FileNotFoundException e) {
			System.out.println("Input file was not found: '" + path + "'");
		}
		catch(IOException e) {
			e.printStackTrace();
		}

		assert(stringFromFile.length() > 0);
		return parseGameFromString(stringFromFile);
	}

	public Game parseGameFromString(String s) throws ParseException {
		boolean isFirstLine = true;

		Position boardSize = new Position(0, 0);
		ArrayList<Player> players = new ArrayList<Player>();

		Scanner scanner = new Scanner(s);
		while (scanner.hasNextLine()) {
			String line = scanner.nextLine();
			if (isFirstLine) {
				boardSize = parseBoardSizeFromLine(line);
			} else {
				players.add(parsePlayerFromLine(line));
			}
			isFirstLine = false;
		}

		assert(!boardSize.equals(new Position(0, 0)));

		Player[] playersArray = new Player[players.size()];
		players.toArray(playersArray);

		return new Game(playersArray, boardSize);
	}

	public Position parseBoardSizeFromLine(String l) throws ParseException {

		Matcher m = Pattern.compile("([0-9]+) ([0-9]+)").matcher(l);
		if (! m.matches()) {
			throw new ParseException("Board Size must contain exactly two numeric values", 0);
		}

		assert(m.groupCount() == 2);

		return new Position(Integer.parseInt(m.group(1)), Integer.parseInt(m.group(2)));
	}

	public Player parsePlayerFromLine(String l) throws ParseException {
		Matcher m = Pattern.compile("(.+) (.) (\\d+) (\\d+) (.)").matcher(l);

		if (! m.matches()) {
			throw new ParseException("Player did not match the required pattern. Invalid line:\n" + l, 1);
		}

		String name = m.group(1);
		String symbol = m.group(2);
		int startX = Integer.parseInt(m.group(3)) + 1;
		int startY = Integer.parseInt(m.group(4)) + 1;
		String goalPosition = m.group(5);

		return new Player(name, symbol, new Position(startX, startY), goalPosition);
	}
}
