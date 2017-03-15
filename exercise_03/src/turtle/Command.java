package turtle;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 * Parent class for concrete implementations of commands. Needs to be subclassed and overridden with actual implementation
 *
 */
public class Command implements ICommand {
    protected int distance;
    protected int lastColumn;
    protected int lastRow;

    @Override
    public void parseFromString(String instruction) {
        throw new NotImplementedException();
    }

    @Override
    public boolean[][] executeOn(boolean[][] board, int lastColumn, int lastRow) throws IndexOutOfBoundsException {
        throw new NotImplementedException();
    }

    public int getDistance() {
        return distance;
    }
    public int getLastColumn() { return lastColumn; }
    public int getLastRow() { return lastRow; }
}
