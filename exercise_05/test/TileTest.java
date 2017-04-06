import exercise05.Player;
import exercise05.Position;
import exercise05.Tile;
import org.junit.Test;

import java.text.ParseException;

import static org.mockito.Mockito.mock;
import static org.junit.Assert.*;

public class TileTest {

	@Test
	public void testEqualTilesWithPlayers() {
		// given
		Tile tile1 = new Tile();
		Tile tile2 = new Tile();

		Player player = new Player("Jane Junit", "J", new Position(2, 1), 'R');

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
	public void testCannotMoveToWall() {
		// given
		Tile tile1 = new Tile();
		tile1.setIsWall(true);

		// when
		Boolean canMoveHere = tile1.canMoveHere();

		// then
		assertFalse(canMoveHere);
	}

	@Test
	public void testCannotMoveToOccupiedTile() {
		// given
		Tile tile1 = new Tile();
		Player player = new Player("Tim Tester", "T", new Position(1, 1), 'R');
		tile1.moveHere(player);

		// when
		Boolean canMoveHere = tile1.canMoveHere();

		// then
		assertFalse(canMoveHere);
	}

}
