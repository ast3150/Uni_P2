package snakes;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

/**
 * Created by samuel on 22.03.17.
 */
public class GamePlayIDieTest {
    @Test
    public void play() {
        IDie die = mock(Die.class);
        Game game = mock(Game.class);

        System.out.println("Initial state: " + game);
        while (game.notOver()) {
            int roll = die.roll();
            System.out.println(game.currentPlayer() + " rolls " + roll + ":  " + game);
            game.movePlayer(roll);
        }
        System.out.println("Final state:   " + game);
        try {
            System.out.println(game.winner() + " wins!");
        } catch (GameNotOverException e) {
            System.out.println("No winner.");
        }
    }
}