package turtle;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

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
        System.out.println(distance);
    }

    public boolean[][] execute(boolean[][] board, int lastRow, int lastColumn) {
        assert lastRow < board.length;
        assert lastColumn <= board[0].length;

        for (int i = lastRow; i >= lastRow - distance && i >= 0; i--) {
            board[i][lastColumn] = true;
        }

        return board;
    }

    public int getDistance() {
        return distance;
    }
}
