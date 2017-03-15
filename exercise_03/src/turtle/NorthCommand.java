package turtle;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by ast on 14.03.17.
 */
public class NorthCommand extends Command implements ICommand {

    public static boolean canHandle(String instruction) {
        return (instruction.matches("north ([0-9])*"));
    }

    public void parseFromString(String instruction) {
        String distance_str = instruction.replaceAll("[^0-9]", "");
        distance = Integer.parseInt(distance_str);
    }

    public boolean[][] executeOn(boolean[][] board, int lastColumn, int lastRow) throws ArrayIndexOutOfBoundsException, IndexOutOfBoundsException {
        this.lastColumn= lastColumn;
        this.lastRow = lastRow;

        if (lastColumn >= board[lastRow].length) throw new ArrayIndexOutOfBoundsException();
        if (lastRow >= board.length) throw new ArrayIndexOutOfBoundsException();

        for (int i = lastRow; i > lastRow - distance; i--) {
            if (i < 0) throw new IndexOutOfBoundsException();
            if (i >= board.length) throw new IndexOutOfBoundsException();

            board[lastColumn][i] = true;
        }

        this.lastRow = lastRow - distance;

        return board;
    }

}
