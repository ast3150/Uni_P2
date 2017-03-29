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

	// TODO: Parse full game string and return game
	// TODO: read from file
	// TODO: generate game

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
		Scanner scanner = new Scanner(s);
		boolean isFirstLine = true;
		int[] boardSize = new int[2];
		ArrayList<Player> players = new ArrayList<Player>();

		while (scanner.hasNextLine()) {
			String line = scanner.nextLine();
			if (isFirstLine) {
				boardSize = parseBoardSizeFromLine(line);
			} else {
				players.add(parsePlayerFromLine(line));
			}
			isFirstLine = false;
		}

		Player[] playersArray = new Player[players.size()];
		players.toArray(playersArray);

		Tile[][] board = new Tile[boardSize[0]][boardSize[1]];
		return new Game(playersArray, board);
	}

	public int[] parseBoardSizeFromLine(String l) throws ParseException {
		Matcher m = Pattern.compile("([0-9]+) ([0-9]+)").matcher(l);

		if (! m.matches()) {
			throw new ParseException("Board Size must contain exactly two numeric values", 0);
		}

		assert(m.groupCount() == 2);

		int[] boardSize = {
				Integer.parseInt(m.group(1)),
				Integer.parseInt(m.group(2))
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

		return new Player(name, symbol, new Position(startX, startY), goalPosition);
	}
}
