import org.junit.*;
import turtle.NorthCommand;

import static org.junit.Assert.assertTrue;


/**
 * Created by ast on 15.03.17.
 */
public class NorthCommandTest {

    @Test
    public void testCanHandleValidCommand() {
        // given
        String s = "north 10";
        NorthCommand c = new NorthCommand();

        // when
        boolean canHandle = c.canHandle(s);

        assertTrue(canHandle);
    }

}
