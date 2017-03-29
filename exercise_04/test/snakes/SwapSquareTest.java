package snakes;

import org.junit.Test;
import snakes.squares.SwapSquare;
import snakes.squares.Square;

import java.util.LinkedList;
import java.util.Queue;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * @author Samuel Schwegler 16-119-695
 * @author Alain Stulz 16-119-414
 */

public class SwapSquareTest {

	/*
	SwapSquare works only if there exists multiple players, but it makes no sense to play this game alone, so nothing to change
	 */

	@Test
	public void testLandHereOrGoHome() {
		Game game = mock(Game.class);
		Square testSquare;

		Square destination;
		Player player;
		Square start;
		when(game.isValidPosition(anyInt())).thenReturn(true);
		testSquare = new SwapSquare(game, 2);
		player = mock(Player.class);
		start = mock(Square.class);

		//sends player back to start (where the other player should be
		when(game.nextPlayer()).thenReturn(player);
		when(player.position()).thenReturn(2);
		when(game.getSquare(2)).thenReturn(start);


		destination = testSquare.landHereOrGoHome();
		assertEquals(start, destination);
	}

	/* Same test without mocking the players */
	@Test
	public void swapTest() throws GameNotOverException{
		IDie die = mock(Die.class);

		Queue<Player> players = new LinkedList<>();
		players.add(new Player("Hans"));
		players.add(new Player("Heiri"));

		Game game = new Game(15, players, 6);
		setSquareToSwap(5, game);

		when(die.roll()).thenReturn(2);
		when(die.getFaces()).thenReturn(5);

		assertTrue(game.notOver());

		game.play(die);

		assertTrue(game.isOver());
		assertEquals(game.winner(), players.element());
	}

	@Test
	public void swapThreePlayersTest() throws GameNotOverException{
		IDie die = mock(Die.class);

		Queue<Player> players = new LinkedList<>();
		players.add(new Player("Hans"));
		players.add(new Player("Heiri"));
		players.add(new Player("Heinz"));

		Game game = new Game(15, players, 6);
		setSquareToSwap(5, game);

		when(die.roll()).thenReturn(2);
		when(die.getFaces()).thenReturn(5);

		assertTrue(game.notOver());

		game.play(die);

		assertTrue(game.isOver());
		//Remove Hans because Heiri has to win
		players.remove();
		assertEquals(game.winner(),players.element());
	}

	public void setSquareToSwap(int position, Game game) {
		game.setSquare(position, new SwapSquare(game, position));
	}
}
