package exercise05;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

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

	/**
	 * Reads input from a text file and sets up a {@link Game} object based on the input.
	 * @param path A valid file path
	 * @return The created Game object
	 * @throws ParseException If the file does not exist or the input can not be read.
	 */
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

	/**
	 * Creates a game object from a multiline input string
	 * @param s The string to parse and generate the game from
	 * @return The created Game object
	 * @throws ParseException If the input can not be read.
	 */
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

	/**
	 * Parses the first line of an input String, to obtain the board size
	 * @param l A single line String
	 * @return A {@link Position} object with the desired position of the bottom right tile.
	 * @throws ParseException If the input can not be read
	 */
	public Position parseBoardSizeFromLine(String l) throws ParseException {

		Matcher m = Pattern.compile("([0-9]+) ([0-9]+)").matcher(l);
		if (! m.matches()) {
			throw new ParseException("Board Size must contain exactly two numeric values", 0);
		}

		assert(m.groupCount() == 2);

		return new Position(Integer.parseInt(m.group(1)), Integer.parseInt(m.group(2)));
	}

	/**
	 * Parses a player from an input String
	 * @param l A single line String
	 * @return A {@link Player} representing the state that was read from the input string.
	 * @throws ParseException If the input can not be read
	 */
	public Player parsePlayerFromLine(String l) throws ParseException {
		Matcher m = Pattern.compile("(.+) (.) (\\d+) (\\d+) (.)").matcher(l);

		if (! m.matches()) {
			throw new ParseException("Player did not match the required pattern. Invalid line:\n" + l, 1);
		}

		String name = m.group(1);
		String symbol = m.group(2);
		int startX = Integer.parseInt(m.group(3));
		int startY = Integer.parseInt(m.group(4));
		char goalPosition = m.group(5).charAt(0);

		return new Player(name, symbol, new Position(startX, startY), goalPosition);
	}

	public IMove parseMoveFromLine(String l) throws ParseException {
		Matcher matcher;

		// Match PlayerMove
		matcher = Pattern.compile("(L|R|U|D)").matcher(l);
		if (matcher.matches()) {
			return new PlayerMove(matcher.group(1).charAt(0));
		}

		// Match PlaceWallMove
		matcher = Pattern.compile("(\\d) (\\d) (\\d) (\\d)").matcher(l);
		if (matcher.matches()) {
			Position pos1 = new Position(Integer.parseInt(matcher.group(1)), Integer.parseInt(matcher.group(2)));
			Position pos2 = new Position(Integer.parseInt(matcher.group(3)), Integer.parseInt(matcher.group(4)));

			Position[] positions = {pos1, pos2};

			return new PlaceWallMove(positions);
		}

		throw new ParseException("Invalid move", 2);
	}
}
