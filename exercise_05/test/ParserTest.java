import exercise05.Game;
import exercise05.Parser;
import exercise05.Player;
import exercise05.Position;
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
		try {
			int[] boardSize = parser.parseBoardSizeFromLine(inputString);

			// then
			assertEquals(boardSize.length, 2);

			int[] expectedBoardSize = {8, 12};
			assertArrayEquals(expectedBoardSize, boardSize);

		} catch (Exception e) {
			throw e;
		}
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
		Player expectedPlayer = new Player("Jimi Hendrix", "H", new Position(1, 2), "R");
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
				"Solomon Burke S 7 12 L\n";

		// when
		Game game = parser.parseGameFromString(inputString);

		// then
		assertEquals(game.getPlayers().length, 2);

		Player expectedPlayer1 = new Player("Otis Redding", "O", new Position(1, 1), "R");
		Player expectedPlayer2 = new Player("Solomon Burke", "S", new Position(7, 12), "L");
		assertTrue("Player 1 did not match definition", game.getPlayers()[0].equals(expectedPlayer1));
		assertTrue("Player 2 did not match definition", game.getPlayers()[1].equals(expectedPlayer2));

		assertEquals(game.getBoard().length, 7);
		assertEquals(game.getBoard()[0].length, 12);
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
		assertEquals(game.getPlayers().length, 2);

		Player expectedPlayer1 = new Player("Kris Kristofferson", "K", new Position(1, 1), "R");
		Player expectedPlayer2 = new Player("Janis Joplin", "J", new Position(1, 3), "R");
		assertTrue("Player 1 did not match definition", game.getPlayers()[0].equals(expectedPlayer1));
		assertTrue("Player 2 did not match definition", game.getPlayers()[1].equals(expectedPlayer2));

		assertEquals(game.getBoard().length, 3);
		assertEquals(game.getBoard()[0].length, 3);
	}
}
