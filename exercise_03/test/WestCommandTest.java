import org.junit.Test;
import turtle.WestCommand;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by samuel on 15.03.17.
 */
public class WestCommandTest {

    @Test
    public void testCanHandleValidCommand() {
        // given
        String s = "west 1";
        WestCommand c = new WestCommand();

        // when
        boolean canHandle = c.canHandle(s);

        // then
        assertTrue(canHandle);
    }

    @Test
    public void testCanHandleValidCommand2() {
        // given
        String s = "west 10";
        WestCommand c = new WestCommand();

        // when
        boolean canHandle = c.canHandle(s);

        // then
        assertTrue(canHandle);
    }

    @Test
    public void testCannotHandleInvalidCommand() {
        // given
        String s = "west @";
        WestCommand c = new WestCommand();

        // when
        boolean canHandle = c.canHandle(s);

        // then
        assertFalse(canHandle);
    }

    @Test
    public void testCannotHandleDifferentCommand() {
        // given
        String s = "north 4";
        WestCommand c = new WestCommand();

        // when
        boolean canHandle = c.canHandle(s);

        // then
        assertFalse(canHandle);
    }

    @Test
    public void testCannotHandleNegativeCommand() {
        // given
        String s = "west -3";
        WestCommand c = new WestCommand();

        // when
        boolean canHandle = c.canHandle(s);

        // then
        assertFalse(canHandle);
    }


    @Test
    public void testGetsCorrectDistance() {
        // given
        String s = "west 3";

        // when
        WestCommand c = new WestCommand();
        c.parseFromString(s);

        // then
        assertEquals(3, c.getDistance());
    }

    @Test
    public void testDrawsOnBoardCorrectly() {
        // given
        boolean[][] board =  new boolean[10][10];
        String s = "west 2";
        WestCommand c = new WestCommand();

        // when
        c.parseFromString(s);
        board = c.executeOn(board, 5, 5);

        // then
        assertTrue(board[5][5]);
        assertTrue(board[4][5]);
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void testDetectsInvalidBoardBoundaries() {
        // given
        boolean[][] board = new boolean[5][5];
        String s = "west 2";
        WestCommand c = new WestCommand();
        c.parseFromString(s);

        // when
        c.executeOn(board, 7, 10);

        // then
        // assertion is thrown
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testDetectsCommandRunsOutOfBounds() {
        // given
        boolean[][] board = new boolean[5][5];
        String s = "west 10";
        WestCommand c = new WestCommand();
        c.parseFromString(s);

        // when
        try {
            board = c.executeOn(board, 1, 1);

            // then
            // assertion is thrown
        } catch (IndexOutOfBoundsException e) {
            assertTrue(board[1][1]);
            throw e;
        }
    }

}