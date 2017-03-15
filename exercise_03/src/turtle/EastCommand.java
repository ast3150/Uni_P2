package turtle;

/**
 * Checks if input is in the right format. Extracts the number out of the String instruction. Moves the number on the board
 *
 * @author Samuel Schwegler 16-119-695
 * @author Alain Stulz 16-119-414
 */
public class EastCommand  extends Command implements ICommand {
    public static boolean canHandle(String instruction) {
        return (instruction.matches("east ([0-9])*"));
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

        for (int i = lastColumn; i < lastColumn + distance; i++) {
            if (i < 0) throw new IndexOutOfBoundsException();
            if (i >= board.length) throw new IndexOutOfBoundsException();

            board[i][lastRow] = true;
        }

        this.lastColumn = lastColumn + distance;

        return board;
    }
}