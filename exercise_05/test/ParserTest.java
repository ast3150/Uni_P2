import exercise05.*;
import org.junit.Test;

import java.text.ParseException;

import static org.junit.Assert.*;

public class ParserTest {

  	@Test
	public void testParsesBoardSizeFromLine() throws ParseException {
		// given
		Parser parser = new Parser();
		String inputString = "6 5 2 5";

		// when
		parser.parseGameSpecificationFromLine(inputString);

		// then
		assertEquals(6, parser.board.length);
		assertEquals(5, parser.board[0].length);

		assertEquals(2, parser.players.length);
		assertEquals(5, parser.numberOfWalls);
	}

  	@Test(expected = ParseException.class)
	public void testDoesNotParseBoardSizeFromLineWithOnlyOneValue() throws ParseException {
		// given
		Parser parser = new Parser();
		String inputString = "8";

		// when
		parser.parseGameSpecificationFromLine(inputString);
	}

	@Test(expected = ParseException.class)
	public void testDoesNotParseBoardSizeFromLineWithMoreThanFourValues() throws ParseException {
		// given
		Parser parser = new Parser();
		String inputString = "8 2 10 5 81";

		// when
		parser.parseGameSpecificationFromLine(inputString);
	}

	@Test(expected = ParseException.class)
	public void testDoesNotParseBoardSizeFromLineWithNonNumericalCharacters() throws ParseException {
		// given
		Parser parser = new Parser();
		String inputString = "A 27";

		// when
		parser.parseGameSpecificationFromLine(inputString);
	}

	@Test
	public void testParsesPlayerFromLine() throws ParseException {
  		// given
		Parser parser = new Parser();
		String inputString = "H Jimi Hendrix";

		// when
		Player parsedPlayer = parser.parsePlayerFromLine(inputString);

		// then
		Player expectedPlayer = new Player("Jimi Hendrix", 'H', parser.numberOfWalls);
		assert(parsedPlayer.equals(expectedPlayer));
	}

	@Test(expected = ParseException.class)
	public void testDoesNotParsePlayerWithoutSymbol() throws ParseException {
		// given
		Parser parser = new Parser();
		String inputString = "Jimi Hendrix";

		// when
		Player parsedPlayer = parser.parsePlayerFromLine(inputString);
	}

	@Test
	public void testParseGameFromString() throws ParseException {
  		// given
		Parser parser = new Parser();
		String inputString = "3 3 2 1\n" +
				"#O#\n" +
				"o #\n" +
				"S s\n" +
				"O Otis Redding\n" +
				"S Solomon Burke\n";

		// when
		Game game = parser.parseGameFromString(inputString);

		// then
		assertEquals(game.getPlayers().size(), 2);

		Player expectedPlayer1 = new Player("Otis Redding", 'O', 1);
		Player expectedPlayer2 = new Player("Solomon Burke", 'S', 1);
		assertTrue("Player 1 did not match definition", game.getPlayers().get(0).equals(expectedPlayer1));
		assertTrue("Player 2 did not match definition", game.getPlayers().get(1).equals(expectedPlayer2));

		assertEquals(game.getBoard().length, 3);
		assertEquals(game.getBoard()[0].length, 3);
	}

	@Test
	/// WARNING: Make sure your Run/Debug configuration has the project root set as working directory
	public void testParseGameFromFile() throws ParseException {
		// given
		Parser parser = new Parser();
		String filePath = "games/game3.txt";

		// when
		Game game = parser.parseGameFromFile(filePath);

		// then
		assertEquals(game.getPlayers().size(), 2);

		Player expectedPlayer1 = new Player("Otis Redding", 'O', 3);
		Player expectedPlayer2 = new Player("Solomon Burke", 'S', 3);
		assertTrue("Player 1 did not match definition", game.getPlayers().get(0).equals(expectedPlayer1));
		assertTrue("Player 2 did not match definition", game.getPlayers().get(1).equals(expectedPlayer2));

		assertEquals(8, game.getBoard().length);
		assertEquals(7, game.getBoard()[0].length);

		Tile e = new Tile();
		Tile s = new WinningTile('s');
		Tile o = new WinningTile('o');
		Tile O = new Tile('O');
		Tile S = new Tile('S');
		Tile H = new WallTile();
		Tile[][] expectedBoard = {
				{H, s, s, s, s, s, H},
				{H, e, e, e, e, e, H},
				{H, e, e, O, e, e, H},
				{H, H, H, e, e, e, H},
				{H, e, e, e, e, e, H},
				{H, e, e, e, H, H, H},
				{H, e, e, S, H, H, H},
				{H, o, o, o, H, H, H}
		};

		assertArrayEquals(expectedBoard, game.getBoard());
	}

	@Test
	public void testParsePlayerMoveFromLine() throws ParseException {
  		// given
		Parser parser = new Parser();

		// when
		IMove move = parser.parseMoveFromLine("D");

		// then
		PlayerMove playerMove = (PlayerMove) move;
		assertEquals('D', playerMove.getDirection());
	}

	@Test
	public void testParsePlaceWallMoveFromLine() throws ParseException {
		// given
		Parser parser = new Parser();
		Position expectedPos1 = new Position(1, 1);
		Position expectedPos2 = new Position(2, 1);
		Position[] expectedPositions = {expectedPos1, expectedPos2};
		// when
		IMove move = parser.parseMoveFromLine("1 1 2 1");

		// then
		PlaceWallMove wallMove = (PlaceWallMove) move;
		assertArrayEquals(expectedPositions, wallMove.getWallPositions());
	}

}
