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
	Pattern lowercaseCharPattern = Pattern.compile("[a-z]");
	Pattern uppercaseCharPattern = Pattern.compile("[A-Z]");

	public Player[] players;
	public Tile[][] board;
	public ArrayList<Position> playerPositions = new ArrayList<>();
	public int numberOfWalls = 0;

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
		Scanner scanner = new Scanner(s);
		String line;

		line = scanner.nextLine();
		parseGameSpecificationFromLine(line);

		assert(this.board.length > 0 && this.board[0].length > 0);
		assert(players.length > 0);

		for (int i=0; i < board.length; i++) {
			line = scanner.nextLine();
			parseBoardSpecificationFromLine(line, i);
		}

		for (int i=0; i < players.length; i++) {
			line = scanner.nextLine();
			players[i] = parsePlayerFromLine(line);

			for (Position p : playerPositions) {
				if (board[p.row][p.col].playerSymbol == players[i].getSymbol()) {
					players[i].setPosition(p);
				}
			}
		}

		return new Game(players, board);
	}


	/**
	 * Parses the first line of an input String, to obtain the board size,
	 * number of players and number of walls for each player.
	 * Sets the corresponding variables to be processed in other methods.
	 * @param l A single line String
	 * @throws ParseException If the input can not be read
	 */
	public void parseGameSpecificationFromLine(String l) throws ParseException {
		Matcher m = Pattern.compile("([0-9]+) ([0-9]+) ([0-9]+) ([0-9]+)").matcher(l);
		if (! m.matches()) {
			throw new ParseException("First line must contain four numeric values", 0);
		}

		assert(m.groupCount() == 4);

		this.board = new Tile[Integer.parseInt(m.group(2))][Integer.parseInt(m.group(1))];
		this.players = new Player[Integer.parseInt(m.group(3))];
		this.numberOfWalls = Integer.parseInt(m.group(4));
	}

	private void parseBoardSpecificationFromLine(String l, int row) throws ParseException {
		Matcher m = Pattern.compile("(#| |[a-zA-Z]){" + this.board[row].length + "}").matcher(l);
		if (! m.matches()) {
			throw new ParseException("Board specification could not be parsed", 4);
		}

		for (int col=0; col<l.length(); col++) {
			Character c = l.charAt(col);
			Tile tile = getTileForBoardSpecificationCharacter(c, row, col);
			board[row][col] = tile;
		}
	}

	private Tile getTileForBoardSpecificationCharacter(Character c, int row, int col) throws ParseException {
		if (c == ' ')
			return new Tile();

		if (c == '#')
			return new WallTile();

		if (uppercaseCharPattern.matcher(c.toString()).matches()) {
			playerPositions.add(new Position(row, col));
			return new Tile(c);
		}

		if (lowercaseCharPattern.matcher(c.toString()).matches())
			return new WinningTile(c);

		throw new ParseException("Board specification could not be parsed", 5);
	}

	/**
	 * Parses a player from an input String
	 * @param l A single line String
	 * @return A {@link Player} representing the state that was read from the input string.
	 * @throws ParseException If the input can not be read
	 */
	public Player parsePlayerFromLine(String l) throws ParseException {
		Matcher m = Pattern.compile("([A-Z]) ([a-zA-Z ]+)").matcher(l);

		if (! m.matches()) {
			throw new ParseException("Player did not match the required pattern. Invalid line:\n" + l, 1);
		}

		Character symbol = m.group(1).charAt(0);
		String name = m.group(2);

		return new Player(name, symbol, numberOfWalls);
	}


	// Moves

	public IMove parseMoveFromLine(String l) throws ParseException {
		Matcher matcher;

		// Match PlayerMove
		matcher = Pattern.compile("(L|R|U|D)").matcher(l);
		if (matcher.matches()) {
			return new PlayerMove(matcher.group(1).charAt(0));
		}

		// Match PlaceWallMove
		matcher = Pattern.compile("(\\d+) (\\d+) (\\d+) (\\d+)").matcher(l);
		if (matcher.matches()) {
			return parsePlaceWallMoveFromMatcher(matcher);
		}

		throw new ParseException("Invalid move", 2);
	}

	public PlaceWallMove parsePlaceWallMoveFromMatcher(Matcher matcher) throws ParseException {
		Position pos1 = new Position(Integer.parseInt(matcher.group(1)), Integer.parseInt(matcher.group(2)));
		Position pos2 = new Position(Integer.parseInt(matcher.group(3)), Integer.parseInt(matcher.group(4)));

		Position[] positions = {pos1, pos2};

		return new PlaceWallMove(positions);
	}
}
