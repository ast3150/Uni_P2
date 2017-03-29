package snakes;

import org.junit.Test;
import snakes.squares.SnakeSquare;
import snakes.squares.Square;
import snakes.squares.TikTokSquare;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

/**
 * @author Samuel Schwegler 16-119-695
 * @author Alain Stulz 16-119-414
 */

public class TikTokSquareTest {

	@Test
	public void testLandHereOrGoHome() {
		Game game = mock(Game.class);
		Square start = mock(Square.class);
		Square ziel = mock(Square.class);

		when(game.isValidPosition(anyInt())).thenReturn(true);

		when(game.getSquare(anyInt())).thenReturn(start);
		when(start.landHereOrGoHome()).thenReturn(ziel);

		TikTokSquare testSquare = new TikTokSquare(game, 5, 6, 7);

		Square anySquare = testSquare.landHereOrGoHome();

		assertEquals(ziel, anySquare);
	}
}
