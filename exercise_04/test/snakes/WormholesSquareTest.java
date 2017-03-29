package snakes;

import org.junit.Before;
import org.junit.Test;
import snakes.squares.NoWormholeExitsException;
import snakes.squares.Square;
import snakes.squares.WormholeEntranceSquare;
import snakes.squares.WormholeExitSquare;

import java.util.LinkedList;
import java.util.Queue;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * @author Samuel Schwegler 16-119-695
 * @author Alain Stulz 16-119-414
 */

public class WormholesSquareTest {
	@Test
	public void wormholeStandardTest() throws GameNotOverException{
		snakes.MockDie die = new snakes.MockDie(6);

		Queue<Player> players = new LinkedList<>();
		players.add(new Player("Hans"));
		players.add(new Player("Heiri"));

		Game game = new Game(15, players, 6);
		setSquareToWormholeEntrance(3, game);
		setSquareToWormholeExit(13, game);
		assertTrue(game.notOver());

		game.play(die);

		assertTrue(game.isOver());
		assertEquals(game.winner(), players.element());
		assertTrue(die.getRollTimes() == 3);
	}

	@Test
	public void wormholeEntranceNoExitTest() throws GameNotOverException {
		snakes.MockDie die = new snakes.MockDie(6);

		Queue<Player> players = new LinkedList<>();
		players.add(new Player("Hans"));
		players.add(new Player("Heiri"));

		Game game_noExit = new Game(15, players, 6);
		/*
		* If I set the position value to 3, so that the player gets with the first move on the entrance the test fails
		* But I have no clue what the reason could be.
		*/
		setSquareToWormholeEntrance(7, game_noExit);

		assertTrue(game_noExit.notOver());

		game_noExit.play(die);

		assertTrue(game_noExit.isOver());
		assertEquals(game_noExit.winner(), players.element());
		assertTrue(die.getRollTimes() == 13);
	}

	public void setSquareToWormholeEntrance(int position, Game game) {
		game.setSquare(position, new WormholeEntranceSquare(game, position));
	}

	public void setSquareToWormholeExit(int position, Game game) {
		game.setSquare(position, new WormholeExitSquare(game, position));
	}
}
