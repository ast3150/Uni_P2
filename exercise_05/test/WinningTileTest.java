import exercise05.PlaceWallMove;
import exercise05.Player;
import exercise05.Tile;
import exercise05.WinningTile;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by ast on 20.04.17.
 */
public class WinningTileTest {

	@Test
	public void testEmptyWinningTileIsNotWinning() {
		// given
		Tile tile1 = new WinningTile('A');

		// when
		Boolean isWinning = tile1.isInWinningPosition();

		// then
		assertFalse(isWinning);
	}

	@Test
	public void testEmptyWinningTileCanBeWinningPositionForPlayer() {
		// given
		Tile tile1 = new WinningTile('A');

		// when
		Boolean isWinningPositionForPlayer = tile1.isWinningTileFor('A');

		// then
		assertTrue(isWinningPositionForPlayer);
	}

	@Test
	public void testPlayerOnCorrectWinningTileWins() {
		// given
		Tile tile1 = new WinningTile('M');
		Player player1 = new Player("Marty McFly", 'M', 1);

		// when
		tile1.moveHere(player1);
		Boolean hasWon = tile1.isInWinningPosition();

		// then
		assertTrue(hasWon);
	}

	@Test
	public void testPlayerOnOtherWInningTileDoesNotWin() {
		// given
		Tile tile1 = new WinningTile('A');
		Player player1 = new Player("Marty McFly", 'M', 1);

		// when
		tile1.moveHere(player1);
		Boolean hasWon = tile1.isInWinningPosition();

		// then
		assertFalse(hasWon);
	}
}
