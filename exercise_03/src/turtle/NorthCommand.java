package turtle;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by ast on 14.03.17.
 */
public class NorthCommand implements ICommand {

    private int distance = 0;

    public static boolean canHandle(String instruction) {
        return (instruction.matches("north [0-9]{2}"));
    }

    public void parseFromString(String instruction) {
        String distance_str = instruction.replaceAll("[^0-9]", "");
        distance = Integer.parseInt(distance_str);
    }

    public boolean[][] execute(boolean[][] board, int lastRow, int lastColumn) throws ArrayIndexOutOfBoundsException, IndexOutOfBoundsException {
        if (lastRow >= board.length) throw new ArrayIndexOutOfBoundsException();
        if (lastColumn >= board[lastRow].length) throw new ArrayIndexOutOfBoundsException();

        for (int i = lastRow - 1; i >= lastRow - distance; i--) {
            if (i < 0) throw new IndexOutOfBoundsException();
            if (i >= board.length) throw new IndexOutOfBoundsException();

            board[i][lastColumn] = true;
        }

        return board;
    }

    public int getDistance() {
        return distance;
    }
}
