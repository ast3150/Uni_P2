/**
 * Created by samuel on 15.03.17.
 */
import org.junit.Test;
import turtle.EastCommand;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class EastCommandTest {
    @Test
    public void testCanHandleValidCommand() {
        // given
        String s = "east 1";
        EastCommand c = new EastCommand();

        // when
        boolean canHandle = c.canHandle(s);

        // then
        assertTrue(canHandle);
    }

    @Test
    public void testCanHandleValidCommand2() {
        // given
        String s = "east 10";
        EastCommand c = new EastCommand();

        // when
        boolean canHandle = c.canHandle(s);

        // then
        assertTrue(canHandle);
    }

    @Test
    public void testCannotHandleInvalidCommand() {
        // given
        String s = "east @";
        EastCommand c = new EastCommand();

        // when
        boolean canHandle = c.canHandle(s);

        // then
        assertFalse(canHandle);
    }

    @Test
    public void testCannotHandleDifferentCommand() {
        // given
        String s = "north 4";
        EastCommand c = new EastCommand();

        // when
        boolean canHandle = c.canHandle(s);

        // then
        assertFalse(canHandle);
    }

    @Test
    public void testCannotHandleNegativeCommand() {
        // given
        String s = "east -3";
        EastCommand c = new EastCommand();

        // when
        boolean canHandle = c.canHandle(s);

        // then
        assertFalse(canHandle);
    }


    @Test
    public void testGetsCorrectDistance() {
        // given
        String s = "east 3";

        // when
        EastCommand c = new EastCommand();
        c.parseFromString(s);

        // then
        assertEquals(3, c.getDistance());
    }

    @Test
    public void testDrawsOnBoardCorrectly() {
        // given
        boolean[][] board =  new boolean[10][10];
        String s = "east 2";
        EastCommand c = new EastCommand();

        // when
        c.parseFromString(s);
        board = c.executeOn(board, 5, 5);

        // then
        assertTrue(board[5][5]);
        assertTrue(board[6][5]);
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void testDetectsInvalidBoardBoundaries() {
        // given
        boolean[][] board = new boolean[5][5];
        String s = "east 2";
        EastCommand c = new EastCommand();
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
        String s = "east 10";
        EastCommand c = new EastCommand();
        c.parseFromString(s);

        // when
        try {
            board = c.executeOn(board, 1, 1);

            // then
            // assertion is thrown
        } catch (IndexOutOfBoundsException e) {
            assertTrue(board[1][1]);
            assertTrue(board[2][1]);
            assertTrue(board[3][1]);
            assertTrue(board[4][1]);
            assertTrue(board[5][1]);
            throw e;
        }
    }

}
