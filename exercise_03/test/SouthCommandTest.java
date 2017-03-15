import org.junit.Test;
import turtle.SouthCommand;

import static org.junit.Assert.*;


/**
 * Created by ast on 15.03.17.
 */
public class SouthCommandTest {

    @Test
    public void testCanHandleValidCommand() {
        // given
        String s = "south 1";
        SouthCommand c = new SouthCommand();

        // when
        boolean canHandle = c.canHandle(s);

        // then
        assertTrue(canHandle);
    }

    @Test
    public void testCanHandleValidCommand2() {
        // given
        String s = "south 10";
        SouthCommand c = new SouthCommand();

        // when
        boolean canHandle = c.canHandle(s);

        // then
        assertTrue(canHandle);
    }

    @Test
    public void testCannotHandleInvalidCommand() {
        // given
        String s = "south @";
        SouthCommand c = new SouthCommand();

        // when
        boolean canHandle = c.canHandle(s);

        // then
        assertFalse(canHandle);
    }

    @Test
    public void testCannotHandleDifferentCommand() {
        // given
        String s = "west 4";
        SouthCommand c = new SouthCommand();

        // when
        boolean canHandle = c.canHandle(s);

        // then
        assertFalse(canHandle);
    }

    @Test
    public void testCannotHandleNegativeCommand() {
        // given
        String s = "south -3";
        SouthCommand c = new SouthCommand();

        // when
        boolean canHandle = c.canHandle(s);

        // then
        assertFalse(canHandle);
    }


    @Test
    public void testGetsCorrectDistance() {
        // given
        String s = "south 3";

        // when
        SouthCommand c = new SouthCommand();
        c.parseFromString(s);

        // then
        assertEquals(3, c.getDistance());
    }

    @Test
    public void testDrawsOnBoardCorrectly() {
        // given
        boolean[][] board =  new boolean[10][10];
        String s = "south 2";
        SouthCommand c = new SouthCommand();

        // when
        c.parseFromString(s);
        board = c.executeOn(board, 5, 5);

        // then
        assertTrue(board[5][5]);
        assertTrue(board[5][6]);
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void testDetectsInvalidBoardBoundaries() {
        // given
        boolean[][] board = new boolean[5][5];
        String s = "south 2";
        SouthCommand c = new SouthCommand();
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
        String s = "south 10";
        SouthCommand c = new SouthCommand();
        c.parseFromString(s);

        // when
        try {
            board = c.executeOn(board, 1, 1);

            // then
            // assertion is thrown
        } catch (IndexOutOfBoundsException e) {
            assertTrue(board[1][1]);
            assertTrue(board[1][2]);
            assertTrue(board[1][3]);
            assertTrue(board[1][4]);
            assertTrue(board[1][5]);

            throw e;
        }
    }

}
