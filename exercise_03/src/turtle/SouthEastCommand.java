package turtle;

/**
 * Checks if input is in the right format. Extracts the number out of the String instruction. Moves the number on the board
 *
 * @author Samuel Schwegler 16-119-695
 * @author Alain Stulz 16-119-414
 */
public class SouthEastCommand extends Command implements ICommand {

    public static boolean canHandle(String instruction) {
        System.out.println(instruction.matches("south east ([0-9])*"));
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

        int i = lastColumn;
        for (int j = lastRow; j < lastRow + distance; j++) {
            if (i < 0) throw new IndexOutOfBoundsException();
            if (i >= board[j].length) throw new IndexOutOfBoundsException();

            if (j < 0) throw new IndexOutOfBoundsException();
            if (j >= board.length) throw new IndexOutOfBoundsException();

            board[i][j] = true;
            i++;
        }

        this.lastRow = lastRow + distance;
        this.lastColumn = lastColumn + distance;

        return board;
    }
}
