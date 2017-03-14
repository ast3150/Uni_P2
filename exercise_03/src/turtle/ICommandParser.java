package turtle;

import java.util.ArrayList;

/**
 * Created by ast on 14.03.17.
 */
public interface ICommandParser {
    public ArrayList<Command> parse(String instructions);
}
