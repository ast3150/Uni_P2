package turtle;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 * Created by ast on 14.03.17.
 */
public abstract class Command {

    /**
     * Determines whether a concrete implementation of Command can handle the given instruction string.
     * To be shadowed in subclass with working instructions.
     *
     * @param instruction A string containing a command (e.g. 'north 5', 'south 13')
     * @return true iff the subclass can handle the command
     */
    public abstract boolean canHandle(String instruction);

    /**
     * Constructor
     *
     * @param instruction A string that could be turned into a command. 'canHandle' must always return true before
     *                    being able to generate a Command safely.
     * @return a command that could later be executed.
     */
    public abstract Command Command(String instruction);

    /**
     * Executes the command and leaves a trace of the turtle on the game board, meaning that all visited squares
     * are marked 'true' while untouched squares remain 'false'
     *
     * @param board The game board that contains the current state of the game
     * @param lastRow The last row (vertical) position of the turtle
     * @param lastColumn The last column (horizontal) position of the turtle
     * @return The game board where the command was applied
     */
    public abstract boolean[][] execute(boolean[][] board, int lastRow, int lastColumn);
}
