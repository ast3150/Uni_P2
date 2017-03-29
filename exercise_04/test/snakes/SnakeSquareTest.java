package snakes;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Test;

import snakes.squares.SnakeSquare;
import snakes.squares.Square;

/**
 * Tests the SnakeSquare class
 *
 * @author Samuel Schwegler 16-119-695
 * @author Alain Stulz 16-119-414
 */

public class SnakeSquareTest {

	@Test
	public void testSnakeSquareNana() throws GameNotOverException {
		Game game = mock(Game.class);

		Square testSquare;
		Square start, ziel;

		when(game.isValidPosition(anyInt())).thenReturn(true);

		testSquare = new SnakeSquare(-2, game, 3);
		start = mock(Square.class);
		ziel = mock(Square.class);

		when(game.findSquare(3, -2)).thenReturn(start);
		when(start.landHereOrGoHome()).thenReturn(ziel);

		Square destination = testSquare.moveAndLand(-2);
		assertEquals(ziel, destination);
	}
}
