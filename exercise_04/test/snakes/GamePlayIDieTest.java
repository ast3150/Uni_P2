
package snakes;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.junit.Assert.*;

/**
 * Created by samuel on 22.03.17.
 */
public class GamePlayIDieTest {
    Die die = mock(Die.class);

    private static final int FACES = 6;
    //Times the die gets rolled
    private static final int MAX = 6;

    @Test
    public void moveMin(){
        assertTrue(hit(1));
    }

    @Test
    public void moveMax(){
        assertTrue(hit(FACES));
    }

    @Test
    public void maxMin(){
        assertTrue(range());
    }

    /**
     * @return true, if the value has been rolled, false otherwise
     */
    private boolean hit(int num){
        for(int i = 1; i <= MAX; i++){
            if (die.roll() == num) return true;
        }
        return false;
    }

    /**
     * @return false, if a value out of range occurs, true otherwise
     */
    private boolean range(){
        for(int i = 1; i <= MAX; i++){
            if (die.roll() < 1 || die.roll() > FACES) return false;
        }
        return true;
    }
}