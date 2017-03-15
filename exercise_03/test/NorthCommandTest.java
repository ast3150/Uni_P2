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
        c.execute(board, 5, 5);

        // then
        assertTrue(board[5][4]);
        assertTrue(board[5][3]);
        assertTrue(board[5][2]);
    }

}
