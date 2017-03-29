
package snakes;
import org.junit.Test;

import snakes.Game;

import java.util.LinkedList;
import java.util.Queue;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

/**
 * Tests the Game#play(IDie) with mock(Die.class)
 *
 * @author Samuel Schwegler 16-119-695
 * @author Alain Stulz 16-119-414
 */
public class GamePlayIDieTest {

    @Test
    public void runGameMockTest() throws GameNotOverException{
        IDie die = mock(Die.class);

        Queue<Player> players = new LinkedList<>();
        players.add(new Player("Hans"));
        players.add(new Player("Heiri"));

        Game game = new Game(15, players, 6);

        when(die.roll()).thenReturn(2);
        assertTrue(game.notOver());

        game.play(die);

        assertTrue(game.isOver());
        /* Check if the winner is the first player (Hans), because the Die roles everytime 2, so there is no chance for Heiri to winn */
        assertEquals(game.winner(), players.element());
    }
}