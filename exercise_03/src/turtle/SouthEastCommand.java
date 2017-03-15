package turtle;

/**
 * Created by ast on 14.03.17.
 */
public class SouthEastCommand extends Command implements ICommand {

    public static boolean canHandle(String instruction) {
        return (instruction.matches("south east ([0-9])*"));
    }

    public void parseFromString(String instruction) {
        String distance_str = instruction.replaceAll("[^0-9]", "");
        distance = Integer.parseInt(distance_str);
    }

    public boolean[][] executeOn(boolean[][] board, int lastColumn, int lastRow) throws ArrayIndexOutOfBoundsException, IndexOutOfBoundsException {
        this.lastColumn = lastColumn;
        this.lastRow = lastRow;

        if (lastColumn >= board[lastRow].length) throw new ArrayIndexOutOfBoundsException();
        if (lastRow >= board.length) throw new ArrayIndexOutOfBoundsException();

        int col = lastColumn;
        int row = lastRow;

        for (int i = 0; i < distance; i++) {
            if (lastColumn+i < 0) throw new IndexOutOfBoundsException();
            if (lastColumn+i >= board.length) throw new IndexOutOfBoundsException();

            if (lastRow+i < 0) throw new IndexOutOfBoundsException();
            if (lastRow+i >= board.length) throw new IndexOutOfBoundsException();

            board[lastColumn+i][lastRow+i] = true;
        }

        this.lastRow = lastRow + distance;
        this.lastColumn = lastColumn + distance;

        return board;
    }
}
