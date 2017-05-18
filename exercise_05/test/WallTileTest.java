import exercise05.ServiceLocator;
import exercise05.TestServiceLocator;
import exercise05.Tile;
import exercise05.WallTile;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;

/**
 * Created by ast on 20.04.17.
 */
public class WallTileTest {
	@Before
	public void setup() {
		ServiceLocator test = new TestServiceLocator();
		ServiceLocator.setServiceLocator(test);
	}

	@Test
	public void testCannotMoveToWall() {
		// given
		Tile tile1 = new WallTile();

		// when
		Boolean canMoveHere = tile1.canMoveHere();

		// then
		assertFalse(canMoveHere);
	}
}
