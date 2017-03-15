package turtle;

import org.junit.*;
import turtle.BoardMaker;
import turtle.NorthCommand;

import static org.junit.Assert.*;


/**
 * Created by ast on 15.03.17.
 */
public class NorthCommandTest {

    @Test
    public void testCanHandleValidCommand() {
        // given
        String s = "north 1";
        NorthCommand c = new NorthCommand();

        // when
        boolean canHandle = c.canHandle(s);

        // then
        assertTrue(canHandle);
    }

    @Test
    public void testCanHandleValidCommand2() {
        // given
        String s = "north 10";
        NorthCommand c = new NorthCommand();

        // when
        boolean canHandle = c.canHandle(s);

        // then
        assertTrue(canHandle);
    }

    @Test
    public void testCannotHandleInvalidCommand() {
        // given
        String s = "north &";
        NorthCommand c = new NorthCommand();

        // when
        boolean canHandle = c.canHandle(s);

        // then
        assertFalse(canHandle);
    }

    @Test
    public void testCannotHandleDifferentCommand() {
        // given
        String s = "south 4";
        NorthCommand c = new NorthCommand();

        // when
        boolean canHandle = c.canHandle(s);

        // then
        assertFalse(canHandle);
    }

    @Test
    public void testCannotHandleNegativeCommand() {
        // given
        String s = "north -3";
        NorthCommand c = new NorthCommand();

        // when
        boolean canHandle = c.canHandle(s);

        // then
        assertFalse(canHandle);
    }


    @Test
    public void testGetsCorrectDistance() {
        // given
        String s = "north 3";

        // when
        NorthCommand c = new NorthCommand();
        c.parseFromString(s);

        // then
        assertEquals(3, c.getDistance());
    }

    @Test
    public void testDrawsOnBoardCorrectly() {
        // given
        boolean[][] board =  new boolean[10][10];
        String s = "north 2";
        NorthCommand c = new NorthCommand();

        // when
        c.parseFromString(s);
        board = c.executeOn(board, 5, 5);

        // then
        assertTrue(board[5][5]);
        assertTrue(board[5][4]);
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void testDetectsInvalidBoardBoundaries() {
        // given
        boolean[][] board = new boolean[5][5];
        String s = "north 2";
        NorthCommand c = new NorthCommand();
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
        String s = "north 10";
        NorthCommand c = new NorthCommand();
        c.parseFromString(s);

        // when
        try {
            board = c.executeOn(board, 1, 4);

            // then
            // assertion is thrown
        } catch (IndexOutOfBoundsException e) {
            assertTrue(board[1][3]);
            assertTrue(board[1][2]);
            assertTrue(board[1][1]);
            assertTrue(board[1][0]);

            throw e;
        }
    }

}
