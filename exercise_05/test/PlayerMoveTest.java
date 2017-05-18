import exercise05.*;
import org.junit.Before;
import org.junit.Test;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.Set;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.*;

/**
 * Created by ast on 06.04.17.
 */
public class PlayerMoveTest {
	@Before
	public void setup() {
		ServiceLocator test = new TestServiceLocator();
		ServiceLocator.setServiceLocator(test);
	}


	@Test
	public void testMoveToEmptyTileIsValid() {
		// given
		Game game = Setup.setupGame();

		PlayerMove move = new PlayerMove('L');

		// when
		Boolean isValid = move.isValidFor(game.getBoard(), game.currentPlayer(), game.getPlayers());

		// then
		assertTrue(isValid);
	}

	@Test
	public void testMoveToOccupiedTileIsInvalid() {
		// given
		Player player = new Player("Joe Jackson", 'J', 4);
		Player player2 = new Player("Billie Kid", 'B', 4);
		Player[] players = { player, player2 };

		player.setPosition(new Position(0, 0));
		player2.setPosition(new Position(1, 0));

		Tile[][] board = Setup.setupEmptyBoard(3);

		board[0][0] = new Tile('J');
		board[1][0] = new Tile('B');

		Game game = new Game(players, board);

		PlayerMove move = new PlayerMove('D');

		// when
		Boolean isValid = move.isValidFor(game.getBoard(), game.currentPlayer(), game.getPlayers());

		// then
		assertFalse(isValid);
	}

	@Test
	public void testMovePlayerRight() throws InvalidMoveException {
		// given
		Game game = Setup.setupGame();

		game.currentPlayer().setPosition(new Position(1, 1));
		game.getBoard()[1][1] = new Tile('F');

		PlayerMove move = new PlayerMove('R');

		// when
		Boolean isValid = move.isValidFor(game.getBoard(), game.currentPlayer(), game.getPlayers());
		move.execute(game.getBoard(), game.currentPlayer(), game.getPlayers());

		// then
		assertEquals(" ", game.getBoard()[1][1].toString());
		assertTrue(game.getBoard()[1][1].canMoveHere());
		assertEquals("F", game.getBoard()[1][2].toString());
		assertTrue(game.currentPlayer().getPosition().equals(new Position(1, 2)));
	}

	@Test(expected = InvalidMoveException.class)
	public void testThrowsOnInvalidMove() throws InvalidMoveException {
		// given
		Game game = Setup.setupGame();

		PlayerMove move = new PlayerMove('M');

		// when
		Boolean hasWon = move.execute(game.getBoard(), game.currentPlayer(), game.getPlayers());

		// then should throw InvalidMoveException
	}

	@Test(expected = InvalidMoveException.class)
	public void testThrowsOnMoveToWallTile() throws InvalidMoveException {
		// given
		Game game = Setup.setupGame();

		game.getBoard()[0][1] = new WallTile();
		game.getBoard()[1][1] = new Tile('F');
		game.currentPlayer().setPosition(new Position(1, 1));

		PlayerMove move = new PlayerMove('U');

		// when
		Boolean hasWon = move.execute(game.getBoard(), game.currentPlayer(), game.getPlayers());

		// then should throw InvalidMoveException
	}

	@Test(expected = InvalidMoveException.class)
	public void testThrowsOnMoveToOccupiedTile() throws InvalidMoveException {
		// given
		Player player = new Player("Joe Jackson", 'J', 4);
		player.setPosition(new Position(0, 1));
		Player player2 = new Player("Billie Kid", 'B', 4);
		player2.setPosition(new Position(1, 1));
		Player[] players = { player, player2 };

		Tile[][] board = Setup.setupEmptyBoard(3);

		board[0][1] = new Tile('J');
		board[1][1] = new Tile('B');

		Game game = new Game(players, board);

		PlayerMove move = new PlayerMove('D');

		// when
		Boolean hasWon = move.execute(game.getBoard(), player, game.getPlayers());

		// then should throw InvalidMoveException
	}

	@Test
	public void testDetectsPlayerHasWon() throws InvalidMoveException {
		// given
		Player player = new Player("Joe Jackson", 'J', 4);
		player.setPosition(new Position(0, 1));
		Player player2 = new Player("Billie Kid", 'B', 4);
		player2.setPosition(new Position(1, 1));
		Player[] players = { player, player2 };

		Tile[][] board = Setup.setupEmptyBoard(3);

		board[0][0] = new WinningTile('J');
		board[0][1] = new Tile('J');
		board[1][1] = new Tile('B');

		Game game = new Game(players, board);

		PlayerMove move = new PlayerMove('L');

		// when
		Boolean hasWon = move.execute(game.getBoard(), game.currentPlayer(), game.getPlayers());

		// then
		assertTrue(hasWon);
	}

	@Test(expected = InvalidMoveException.class)
	public void testThrowsOnMoveOffBoard() throws Exception {
		// given
		Game game = Setup.setupGame();

		PlayerMove move = new PlayerMove('R');

		// when
		Boolean hasWon = move.execute(game.getBoard(), game.currentPlayer(), game.getPlayers());

		// then should throw exception
	}
}
