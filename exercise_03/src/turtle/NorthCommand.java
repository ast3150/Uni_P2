package turtle;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 * Created by ast on 14.03.17.
 */
public class NorthCommand extends Command {

    public boolean canHandle(String instruction) {
        throw new NotImplementedException();
    }

    @Override
    public Command Command(String instruction) {
        throw new NotImplementedException();
    }

    @Override
    public boolean[][] execute(boolean[][] board, int lastRow, int lastColumn) {
        throw new NotImplementedException();
    }
}
