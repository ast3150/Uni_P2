package turtle;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by ast on 15.03.17.
 */
public class SouthEastCommandTest {

    @Test
    public void testCanHandleValidCommand() {
        // given
        String s = "south east 1";
        SouthEastCommand c = new SouthEastCommand();

        // when
        boolean canHandle = c.canHandle(s);

        // then
        assertTrue(canHandle);
    }

    @Test
    public void testCanHandleValidCommand2() {
        // given
        String s = "south east 10";
        SouthEastCommand c = new SouthEastCommand();

        // when
        boolean canHandle = c.canHandle(s);

        // then
        assertTrue(canHandle);
    }

    @Test
    public void testCannotHandleInvalidCommand() {
        // given
        String s = "south east @";
        SouthEastCommand c = new SouthEastCommand();

        // when
        boolean canHandle = c.canHandle(s);

        // then
        assertFalse(canHandle);
    }

    @Test
    public void testCannotHandleDifferentCommand() {
        // given
        String s = "west 4";
        SouthEastCommand c = new SouthEastCommand();

        // when
        boolean canHandle = c.canHandle(s);

        // then
        assertFalse(canHandle);
    }

    @Test
    public void testCannotHandleNegativeCommand() {
        // given
        String s = "south east -3";
        SouthEastCommand c = new SouthEastCommand();

        // when
        boolean canHandle = c.canHandle(s);

        // then
        assertFalse(canHandle);
    }


    @Test
    public void testGetsCorrectDistance() {
        // given
        String s = "south east 3";

        // when
        SouthEastCommand c = new SouthEastCommand();
        c.parseFromString(s);

        // then
        assertEquals(3, c.getDistance());
    }

    @Test
    public void testDrawsOnBoardCorrectly() {
        // given
        boolean[][] board =  new boolean[10][10];
        String s = "south east 3";
        SouthEastCommand c = new SouthEastCommand();

        // when
        c.parseFromString(s);
        board = c.executeOn(board, 0, 0);

        // then
        assertTrue(board[0][0]);
        assertTrue(board[1][1]);
        assertTrue(board[2][2]);
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void testDetectsInvalidBoardBoundaries() {
        // given
        boolean[][] board = new boolean[5][5];
        String s = "south east 2";
        SouthEastCommand c = new SouthEastCommand();
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
        String s = "south east 10";
        SouthEastCommand c = new SouthEastCommand();
        c.parseFromString(s);

        // when
        try {
            board = c.executeOn(board, 1, 1);

            // then
            // assertion is thrown
        } catch (IndexOutOfBoundsException e) {
            assertTrue(board[1][1]);
            assertTrue(board[2][2]);
            assertTrue(board[3][3]);
            assertTrue(board[4][4]);
            assertTrue(board[5][5]);

            throw e;
        }
    }

}