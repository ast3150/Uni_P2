package turtle;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.ArrayList;

/**
 * Created by ast on 14.03.17.
 */
public class NorthCommand extends Command {

    public boolean canHandle(String instruction) {
        //Test
        if (instruction == "north [0-9]{2}"){
            return true;
        } else {
            return false;
        }
        //throw new NotImplementedException();
    }

    @Override
    public Command Command(String instruction) {
        throw new NotImplementedException();
    }

    @Override
    public boolean[][] execute(boolean[][] board, int lastRow, int lastColumn) {
        //Test
        board[3][3] = true;
        return board;
    }
}
