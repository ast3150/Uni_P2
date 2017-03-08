package snakes;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by ast on 08.03.17.
 */
public class SpeedUpSquareTest extends SquareTest {
    @Override
    @Before
    public void newGame() {
        initializeGame(15);
        game.setSquare(3, new SpeedUpSquare(game, 3));
    }

    @Test
    public void speedUpSquare() {
        game.movePlayer(2); // moves Jack
        assertEquals(3, jack.position());
        assertEquals(1, jill.position());
        game.movePlayer(4); // moves Jill
        assertEquals(3, jack.position());
        assertEquals(5, jill.position());
        game.movePlayer(3); // moves Jack
        assertEquals(3+(2*3), jack.position());
        assertEquals(5, jill.position());
    }
}