import exercise05.Player;
import exercise05.Position;
import exercise05.Tile;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class TileTest {

	@Test
	public void testEqualTilesWithPlayers() {
		// given
		Tile tile1 = new Tile();
		Tile tile2 = new Tile();

		Player player = new Player("Jane Junit", 'J', 1);

		tile1.moveHere(player);
		tile2.moveHere(player);

		// when
		boolean equals = tile1.equals(tile2);

		// then
		assertTrue(equals);
	}

	@Test
	public void testCanMoveToEmptyTile() {
		// given
		Tile tile1 = new Tile();

		// when
		Boolean canMoveHere = tile1.canMoveHere();

		// then
		assertTrue(canMoveHere);
	}

	@Test
	public void testCannotMoveToOccupiedTile() {
		// given
		Tile tile1 = new Tile();
		Player player = new Player("Tim Tester", 'T', 1);
		tile1.moveHere(player);

		// when
		Boolean canMoveHere = tile1.canMoveHere();

		// then
		assertFalse(canMoveHere);
	}

	@Test
	public void testIsNotInWinningPosition() {
		// given
		Tile tile1 = new Tile();
		Player player = new Player("Tim Tester", 'T', 1);
		tile1.moveHere(player);

		// when
		Boolean isWinningPosition = tile1.isInWinningPosition();
		Boolean isWinningTileForPlayer = tile1.isWinningTileFor('T');

		// then
		assertFalse(isWinningPosition);
		assertFalse(isWinningTileForPlayer);
	}

}
