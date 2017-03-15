package turtle;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.ArrayList;

/**
 * Created by ast on 14.03.17.
 */
public class NorthCommand extends Command {

    private int distance = 0;

    public boolean canHandle(String instruction) {
        //Test
        return (instruction.equals("north [0-9]{2}"));
        //throw new NotImplementedException();
    }

    @Override
    public Command Command(String instruction) {
        String distance_str = instruction.replaceAll("[^0-9]", "");
        distance = Integer.parseInt(distance_str);
        System.out.println(distance);
        return this;
    }

    @Override
    public boolean[][] execute(boolean[][] board, int lastRow, int lastColumn) {
        //Test
        board[3][3] = true;
        return board;
    }
}
