package turtle;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.lang.*;
import java.util.*;

/**
 * Created by ast on 14.03.17.
 */
public class CommandParser implements ICommandParser {
    Command[] COMMANDS = {
            new NorthCommand()
    };

    @Override
    public ArrayList<Command> parse(String instructions) {
        ArrayList<Command> parsedCommands = new ArrayList<Command>();
        Scanner scanner = new Scanner(instructions);

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();

            for (Command c : COMMANDS) {
                if (c.canHandle(line)) {
                    parsedCommands.add(c);
                    break;
                }
            }
        }
        scanner.close();

        return parsedCommands;
    }
}
