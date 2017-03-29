package snakes;

import org.junit.Test;
import snakes.*;

import java.util.LinkedList;
import java.util.Queue;

import static org.junit.Assert.*;

/**
 * Tests the Game#play(IDie) with a MockDie class
 *
 * @author Samuel Schwegler 16-119-695
 * @author Alain Stulz 16-119-414
 */
public class GamePlayMockDieTest {
    @Test
    public void runGameMockDieTest() throws GameNotOverException {
        final int FACES = 6;

        snakes.MockDie mockedDie = new snakes.MockDie(FACES);

        Queue<Player> players = new LinkedList<>();
        players.add(new Player("Hans"));
        players.add(new Player("Heiri"));

        Game game = new Game(15, players, 6);

        assertTrue(game.notOver());

        game.play(mockedDie);

        assertTrue(game.isOver());
         /* Check if the winner is the first player (Hans), because the Die roles everytime 2, so there is no chance for Heiri to winn */
        assertEquals(game.winner(), players.element());
    }
}