import exercise05.Game;
import exercise05.Parser;
import exercise05.Player;
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
		Player expectedPlayer = new Player("Jimi Hendrix", "H", 1, 2, "R");
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
}
