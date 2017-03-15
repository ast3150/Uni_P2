package turtle;

import java.util.ArrayList;

/**
 * Parses a string of instructions into executable commands
 *
 * Created by ast on 14.03.17.
 */
public interface ICommandParser {
    /**
     *
     * @param instructions A single- or multiline string of instructions. For available instructions check the COMMANDS constant
     *                     for the concrete implementation
     * @return A list of commands that could be executed on a board
     * @throws ParserException If one or more lines could not be parsed into a valid command
     */
    public ArrayList<ICommand> parse(String instructions) throws ParserException;
}
