package turtle;

import java.lang.*;
import java.util.*;
import java.lang.reflect.*;

/**
 * Parses a string of instructions into executable commands
 *
 */
public class CommandParser implements ICommandParser {
    Class[] COMMANDS = {
            NorthCommand.class,
            SouthCommand.class,
            WestCommand.class,
            EastCommand.class,
            SouthEastCommand.class
    };

    public ArrayList<ICommand> parse(String instructions) throws ParserException {
        ArrayList<ICommand> parsedCommands = new ArrayList<ICommand>();
        Scanner scanner = new Scanner(instructions);

        while (scanner.hasNextLine()) {
            String instructionString = scanner.nextLine();

            try {
                ICommand command = findCommandForString(instructionString);
                parsedCommands.add(command);
            } catch (Exception e) {
                throw e;
            }
        }

        scanner.close();
        return parsedCommands;
    }

    private ICommand findCommandForString(String instruction) throws ParserException {
        for (Class possibleCommand : COMMANDS ) {
            assert ICommand.class.isAssignableFrom(possibleCommand);

            try {
                Method canHandleMethod = possibleCommand.getMethod("canHandle", String.class);
                boolean canHandle = (boolean)canHandleMethod.invoke(possibleCommand, instruction);

                if (canHandle) {
                    ICommand parsedCommand = (ICommand)possibleCommand.newInstance();
                    parsedCommand.parseFromString(instruction);
                    return parsedCommand;
                }
            } catch (Exception e) {
                continue;
            }
        }

        throw new ParserException();
    }
}
