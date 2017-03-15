package turtle;

/**
 * Created by ast on 15.03.17.
 */
public class Command {
    protected int distance;
    protected int lastColumn;
    protected int lastRow;

    public int getDistance() {
        return distance;
    }
    public int getLastColumn() { return lastColumn; }
    public int getLastRow() { return lastRow; }
}
