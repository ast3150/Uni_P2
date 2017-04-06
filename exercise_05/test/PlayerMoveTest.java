import exercise05.*;
import org.junit.Test;

import java.util.Arrays;
import java.util.LinkedList;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

/**
 * Created by ast on 06.04.17.
 */
public class PlayerMoveTest {

	@Test
	public void testMoveToEmptyTileIsValid() {
		// given
		Player player = new Player("Joe Jackson", "J", new Position(1, 1), 'R');
		Player[] players = { player };

		Position boardSize = new Position(3, 3);
		Game game = new Game(players, boardSize);

		PlayerMove move = new PlayerMove('R');

		// when
		Boolean isValid = move.isValidFor(game.getBoard(), player, game.getPlayers());

		// then
		assertTrue(isValid);
	}

	@Test
	public void testMoveToOccupiedTileIsInvalid() {
		// given
		Player player = new Player("Joe Jackson", "J", new Position(1, 1), 'R');
		Player player2 = new Player("Billie Kid", "B", new Position(2, 1), 'D');
		Player[] players = { player, player2 };

		Position boardSize = new Position(3, 3);
		Game game = new Game(players, boardSize);

		PlayerMove move = new PlayerMove('D');

		// when
		Boolean isValid = move.isValidFor(game.getBoard(), player, game.getPlayers());

		// then
		assertFalse(isValid);
	}

	@Test
	public void testMovePlayerRight() throws InvalidMoveException {
		// given
		Player player = new Player("Joe Jackson", "J", new Position(1, 1), 'R');
		Player[] players = { player };
		Position boardSize = new Position(3, 3);
		Game game = new Game(players, boardSize);

		PlayerMove move = new PlayerMove('R');

		// when
		Boolean isValid = move.isValidFor(game.getBoard(), player, game.getPlayers());
		move.execute(game.getBoard(), player, game.getPlayers());

		// then
		assertNull(game.getBoard()[1][1].getPlayer());
		assertNotNull(game.getBoard()[1][2].getPlayer());
		assertTrue(game.currentPlayer().getPosition().equals(new Position(1, 2)));
	}

	@Test(expected = InvalidMoveException.class)
	public void testThrowsOnInvalidMove() throws InvalidMoveException {
		// given
		Player player = new Player("Joe Jackson", "J", new Position(1, 1), 'R');
		Player[] players = { player };
		Position boardSize = new Position(3, 3);
		Game game = new Game(players, boardSize);

		PlayerMove move = new PlayerMove('M');

		// when
		Boolean hasWon = move.execute(game.getBoard(), player, game.getPlayers());

		// then should throw InvalidMoveException
	}

	@Test(expected = InvalidMoveException.class)
	public void testThrowsOnMoveToWallTile() throws InvalidMoveException {
		// given
		Player player = new Player("Joe Jackson", "J", new Position(1, 1), 'R');
		Player[] players = { player };
		Position boardSize = new Position(3, 3);
		Game game = new Game(players, boardSize);

		PlayerMove move = new PlayerMove('U');

		// when
		Boolean hasWon = move.execute(game.getBoard(), player, game.getPlayers());

		// then should throw InvalidMoveException
	}

	@Test(expected = InvalidMoveException.class)
	public void testThrowsOnMoveToOccupiedTile() throws InvalidMoveException {
		// given
		Player player = new Player("Joe Jackson", "J", new Position(1, 1), 'R');
		Player player2 = new Player("Billie Kid", "B", new Position(2, 1), 'D');
		Player[] players = { player, player2 };

		Position boardSize = new Position(3, 3);
		Game game = new Game(players, boardSize);

		PlayerMove move = new PlayerMove('D');

		// when
		Boolean hasWon = move.execute(game.getBoard(), player, game.getPlayers());

		// then should throw InvalidMoveException
	}

	@Test
	public void testDetectsPlayerHasWon() throws InvalidMoveException {
		// given
		Player player = new Player("Joe Jackson", "J", new Position(1, 2), 'L');
		Player player2 = new Player("Billie Kid", "B", new Position(2, 1), 'D');
		Player[] players = { player, player2 };

		Position boardSize = new Position(3, 3);
		Game game = new Game(players, boardSize);

		PlayerMove move = new PlayerMove('L');

		// when
		Boolean hasWon = move.execute(game.getBoard(), player, game.getPlayers());

		// then
		assertTrue(hasWon);
	}
}
