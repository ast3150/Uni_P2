import exercise05.*;
import org.junit.Rule;
import org.junit.Test;
import org.junit.Before;
import org.junit.contrib.java.lang.system.ExpectedSystemExit;

import java.text.ParseException;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by ast on 30.03.17.
 */
public class GameTest {
	@Rule
	public final ExpectedSystemExit exit = ExpectedSystemExit.none();

	@Before
	public void setup() {
		ServiceLocator test = new TestServiceLocator();
		ServiceLocator.setServiceLocator(test);
	}

	@Test
	public void testGamePassesTurnAfterValidMove() throws Exception {
		// given
		String gameString = "3 3 2 3\n" +
							"#A \n" +
							"a #\n" +
							"B b\n" +
							"A Player A\n" +
							"B Player B";
		Parser parser = new Parser();

		IDriver driver = mock(Driver.class);
		when(driver.readNextMove(false)).thenReturn(new PlayerMove('D'));

		Game game = parser.parseGameFromString(gameString);
		game.setDriver(driver);

		// when
		Player movingPlayer = game.currentPlayer();
		game.takeNextTurn();

		// then
		assertNotEquals(game.currentPlayer(), movingPlayer);
	}

	@Test
	public void testGameDoesEndOnWinningMove() throws ParseException {
		// expect
		exit.expectSystemExitWithStatus(0);

		// given
		String gameString = "3 3 2 3\n" +
				"#A \n" +
				" a#\n" +
				"B b\n" +
				"A Player A\n" +
				"B Player B";
		Parser parser = new Parser();

		IDriver driver = mock(Driver.class);
		when(driver.readNextMove(false)).thenReturn(new PlayerMove('D'));

		Game game = parser.parseGameFromString(gameString);
		game.setDriver(driver);

		// when
		game.start();

		// then expectation should hold
	}
}
