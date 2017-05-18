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
public class Parser implements IParser {
	Pattern lowercaseCharPattern = Pattern.compile("[a-z]");
	Pattern uppercaseCharPattern = Pattern.compile("[A-Z]");

	public Player[] players;
	public Tile[][] board;
	public ArrayList<Position> playerPositions = new ArrayList<>();
	public int numberOfWalls = 0;

	@Override
	public Game parseGameFromFile(String path) throws ParseException {
		String stringFromFile = "";
		try {
			stringFromFile = new String(readAllBytes(get(path)));
		}
		catch(FileNotFoundException e) {
			ServiceLocator.instance().getPrintStream().println("Input file was not found: '" + path + "'");
		}
		catch(IOException e) {
			e.printStackTrace();
		}

		assert(stringFromFile.length() > 0);
		return parseGameFromString(stringFromFile);
	}

	@Override
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


	@Override
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

	/**
	 * Parses a board specification from a line of input, to obtain the position and type
	 * of tiles and players. Applies the read values to the board.
	 * @param l A single line String
	 * @param row The index of the row currently being parsed, used to write to the correct board row
	 * @throws ParseException If the input can not be read
	 */
	private void parseBoardSpecificationFromLine(String l, int row) throws ParseException {
		assert(board.length >= row);

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

	/**
	 * Matches the input character to the specified type of Tile. Adds a player to the tile if specified.
	 * @param c A single game board specification character
	 * @param row The index of the row currently being parsed, used to set the correct position to the player
	 * @param col The index of the column currently being parsed, used to set the correct position to the player
	 * @return The tile matching the specified input
	 * @throws ParseException If the input can not be read
	 */
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

	@Override
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

	@Override
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

	@Override
	public PlaceWallMove parsePlaceWallMoveFromMatcher(Matcher matcher) throws ParseException {
		Position pos1 = new Position(Integer.parseInt(matcher.group(1)), Integer.parseInt(matcher.group(2)));
		Position pos2 = new Position(Integer.parseInt(matcher.group(3)), Integer.parseInt(matcher.group(4)));

		Position[] positions = {pos1, pos2};

		return new PlaceWallMove(positions);
	}
}
