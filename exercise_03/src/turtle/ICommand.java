package turtle;

import com.sun.tools.corba.se.idl.constExpr.Not;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.io.InvalidClassException;

/**
 * Created by ast on 14.03.17.
 */
public interface ICommand {

    /**
     * Determines whether a concrete implementation of Command can handle the given instruction string.
     * To be shadowed in subclass with working instructions.
     *
     * @param instruction A string containing a command (e.g. 'north 5', 'south 13')
     * @return true iff the subclass can handle the command
     */
    public static boolean canHandle(String instruction) {
        throw new NotImplementedException();
    }

    /**
     * Reads the necessary values from a String so that the command could later be executed.
     *
     * @param instruction A string that could be turned into a command. 'canHandle' must always return true before
     *                    being able to generate a Command safely.
     */
    public void parseFromString(String instruction);

    /**
     * Execute the command and leave a trace of the turtle on the game board, meaning that all visited squares
     * are marked 'true' while untouched squares remain the value they were before.
     *
     * @param board      The game board that contains the current state of the game
     * @param lastRow    The last row (vertical) position of the turtle
     * @param lastColumn The last column (horizontal) position of the turtle
     * @throws ArrayIndexOutOfBoundsException if the given lastRow or lastColumn are not on the board and the move cannot be made
     * @throws IndexOutOfBoundsException if the command would run off the game board and could not be executed completely
     * @return The game board where the command was applied
     */
    public boolean[][] execute(boolean[][] board, int lastRow, int lastColumn) throws ArrayIndexOutOfBoundsException, IndexOutOfBoundsException;

    /**
     * Gets the absolute distance that was parsed from instruction string.
     *
     * Uses a simple norm that adds the distances in all directions,
     * e.g. North 8 has a distance of 8
     * e.g. SouthWest 3, 2 has a distance of (2+3) = 5
     * e.g. NorthEast 4, 1 has a distance of (4+1) = 5
     *
     * @return the absolute distance, or 0 if no valid command has been parsed
     */
    public int getDistance();
}
