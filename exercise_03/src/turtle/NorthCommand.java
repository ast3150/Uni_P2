package turtle;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.ArrayList;

/**
 * Created by ast on 14.03.17.
 */
public class NorthCommand extends Command {

    public boolean canHandle(String instruction) {
        return (instruction.matches("north [0-9]{2}"));
    }

    @Override
    public Command Command(String instruction) {
        
        int distance;
        throw new NotImplementedException();
    }

    @Override
    public boolean[][] execute(boolean[][] board, int lastRow, int lastColumn) {
        //Test
        board[3][3] = true;
        return board;
    }
}
