import exercise05.*;
import org.junit.Test;

import java.text.ParseException;

import static org.mockito.Mockito.mock;
import static org.junit.Assert.*;

public class ParserTest {

  	@Test
	public void testParsesBoardSizeFromLine() throws ParseException {
		// given
		Parser parser = new Parser();
		String inputString = "8 12";

		// when
		Position boardSize = parser.parseBoardSizeFromLine(inputString);

		// then
		Position expectedBoardSize = new Position(8, 12);
		assert(boardSize.equals(expectedBoardSize));
	}

  	@Test(expected = ParseException.class)
	public void testDoesNotParseBoardSizeFromLineWithOnlyOneValue() throws ParseException {
		// given
		Parser parser = new Parser();
		String inputString = "8";

		// when
		parser.parseBoardSizeFromLine(inputString);
	}

	@Test(expected = ParseException.class)
	public void testDoesNotParseBoardSizeFromLineWithMoreThanTwoValues() throws ParseException {
		// given
		Parser parser = new Parser();
		String inputString = "8 2 10";

		// when
		parser.parseBoardSizeFromLine(inputString);
	}

	@Test(expected = ParseException.class)
	public void testDoesNotParseBoardSizeFromLineWithNonNumericalCharacters() throws ParseException {
		// given
		Parser parser = new Parser();
		String inputString = "A 27";

		// when
		parser.parseBoardSizeFromLine(inputString);
	}

	@Test
	public void testParsesPlayerFromLine() throws ParseException {
  		// given
		Parser parser = new Parser();
		String inputString = "Jimi Hendrix H 1 2 R";

		// when
		Player parsedPlayer = parser.parsePlayerFromLine(inputString);

		// then
		Player expectedPlayer = new Player("Jimi Hendrix", "H", new Position(1, 2), 'R');
		assert(parsedPlayer.equals(expectedPlayer));
	}

	@Test(expected = ParseException.class)
	public void testDoesNotParsePlayerWithoutStartPosition() throws ParseException {
		// given
		Parser parser = new Parser();
		String inputString = "Jimi Hendrix H R";

		// when
		Player parsedPlayer = parser.parsePlayerFromLine(inputString);
	}

	@Test
	public void testParseGameFromString() throws ParseException {
  		// given
		Parser parser = new Parser();
		String inputString = "7 12\n" +
				"Otis Redding O 1 1 R\n" +
				"Solomon Burke S 6 11 L\n";

		// when
		Game game = parser.parseGameFromString(inputString);

		// then
		assertEquals(game.getPlayers().size(), 2);

		Player expectedPlayer1 = new Player("Otis Redding", "O", new Position(1, 1), 'R');
		Player expectedPlayer2 = new Player("Solomon Burke", "S", new Position(6, 11), 'L');
		assertTrue("Player 1 did not match definition", game.getPlayers().get(0).equals(expectedPlayer1));
		assertTrue("Player 2 did not match definition", game.getPlayers().get(1).equals(expectedPlayer2));

		// Board automatically creates walls so we expect the size to be the given size + 2
		assertEquals(game.getBoard().length, 9);
		assertEquals(game.getBoard()[0].length, 14);
	}

	@Test
	/// WARNING: Make sure your Run/Debug configuration has the project root set as working directory
	public void testParseGameFromFile() throws ParseException {
		// given
		Parser parser = new Parser();
		String filePath = "games/game2.txt";

		// when
		Game game = parser.parseGameFromFile(filePath);

		// then
		assertEquals(game.getPlayers().size(), 2);

		Player expectedPlayer1 = new Player("Kris Kristofferson", "K", new Position(1, 1), 'R');
		Player expectedPlayer2 = new Player("Janis Joplin", "J", new Position(1, 3), 'R');
		assertTrue("Player 1 did not match definition", game.getPlayers().get(0).equals(expectedPlayer1));
		assertTrue("Player 2 did not match definition", game.getPlayers().get(1).equals(expectedPlayer2));

		// Board automatically creates walls so we expect the size to be the given size + 2
		assertEquals(game.getBoard().length, 5);
		assertEquals(game.getBoard()[0].length, 5);
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
