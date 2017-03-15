package turtle;

import java.lang.*;
import java.util.*;
import java.lang.reflect.*;

/**
 * Created by ast on 14.03.17.
 */
public class CommandParser implements ICommandParser {
    Class[] COMMANDS = {
            NorthCommand.class
    };

    @Override
    public ArrayList<ICommand> parse(String instructions) {
        ArrayList<ICommand> parsedCommands = new ArrayList<ICommand>();
        Scanner scanner = new Scanner(instructions);

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();

            for (Class c : COMMANDS ) {
                assert ICommand.class.isAssignableFrom(c);

                try {
                    Method m = c.getMethod("canHandle", String.class);
                    Object result = m.invoke(null, 1.5, "foo");
                } catch (Exception e) {
                    continue;
                }


                /*if (c.canHandle(line)) {
                    parsedCommands.add(c);
                    break;
                }*/
            }
        }
        scanner.close();

        return parsedCommands;
    }
}
