import com.sun.javaws.exceptions.InvalidArgumentException;
import exercise05.*;
import org.junit.Test;
import org.junit.Rule;
import org.junit.contrib.java.lang.system.ExpectedSystemExit;
import org.mockito.*;

import java.text.ParseException;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by ast on 30.03.17.
 */
public class GameTest {
	@Rule
	public final ExpectedSystemExit exit = ExpectedSystemExit.none();

	@Test
	public void testCreateBoard() {
		// given
		Player[] players = {};
		Position boardSize = new Position(3, 3);
		Game game = new Game(players, boardSize);

		// when
		Tile[][] board = game.generateEmptyBoard(boardSize);

		// then
		Tile normalTile = new Tile();

		Tile wallTile = new Tile();
		wallTile.setIsWall(true);

		Tile[] expectedRow1 = {wallTile, wallTile, wallTile, wallTile, wallTile};
		Tile[] expectedRow2 = {wallTile, normalTile, normalTile, normalTile, wallTile};

		boolean row0Match = false;
		boolean row1Match = false;
		boolean row2Match = false;
		boolean row3Match = false;
		boolean row4Match = false;

		for (int i = 0; i<board[0].length; i++) { row0Match = board[0][i].equals(expectedRow1[i]); }
		for (int i = 0; i<board[1].length; i++) { row1Match = board[1][i].equals(expectedRow2[i]); }
		for (int i = 0; i<board[2].length; i++) { row2Match = board[2][i].equals(expectedRow2[i]); }
		for (int i = 0; i<board[3].length; i++) { row3Match = board[3][i].equals(expectedRow2[i]); }
		for (int i = 0; i<board[4].length; i++) { row4Match = board[4][i].equals(expectedRow1[i]); }

		assertTrue(row0Match);
		assertTrue(row1Match);
		assertTrue(row2Match);
		assertTrue(row3Match);
		assertTrue(row4Match);
	}

	@Test
	public void testGamePassesTurnAfterValidMove() throws Exception {
		// given
		String gameString = "6 6\n" +
							"Player A A 1 2 D\n" +
							"Player B B 3 2 U" ;
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
		String gameString = "6 6\n" +
				"Player A A 5 6 D\n" +
				"Player B B 3 2 U" ;
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
