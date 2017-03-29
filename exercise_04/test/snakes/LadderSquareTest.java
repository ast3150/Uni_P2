package snakes;

import org.junit.Test;
import snakes.squares.Square;
import snakes.squares.LadderSquare;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class LadderSquareTest {

	@Test
	public void testLadderSquare()
	{
		Game testGame = mock(Game.class);
		LadderSquare testing = mock(LadderSquare.class);
		Square start = mock(Square.class);
		Square stop = mock(Square.class);

		when(testGame.isValidPosition(anyInt())).thenReturn(true);
		when(testing.isValidTransport(anyInt())).thenReturn(true);
		when(testGame.getSquare(anyInt())).thenReturn(start);
		when(start.landHereOrGoHome()).thenReturn(stop);
		LadderSquare testSquare = new LadderSquare(23, testGame, 7);

		Square finalSquare = testSquare.landHereOrGoHome();

		assertEquals(stop, finalSquare);
	}
}
