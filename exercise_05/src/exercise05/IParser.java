package exercise05;

import java.text.ParseException;
import java.util.regex.Matcher;

/**
 * Created by ast on 18.05.17.
 */
public interface IParser {
	/**
	 * Reads input from a text file and sets up a {@link Game} object based on the input.
	 * @param path A valid file path
	 * @return The created Game object
	 * @throws ParseException If the file does not exist or the input can not be read.
	 */
	Game parseGameFromFile(String path) throws ParseException;

	/**
	 * Creates a game object from a multiline input string
	 * @param s The string to parse and generate the game from
	 * @return The created Game object
	 * @throws ParseException If the input can not be read.
	 */
	Game parseGameFromString(String s) throws ParseException;

	/**
	 * Parses the first line of an input String, to obtain the board size,
	 * number of players and number of walls for each player.
	 * Sets the corresponding variables to be processed in other methods.
	 * @param l A single line String
	 * @throws ParseException If the input can not be read
	 */
	void parseGameSpecificationFromLine(String l) throws ParseException;

	/**
	 * Parses a player specification from an input String
	 * @param l A single line String
	 * @return A {@link Player} representing the state that was read from the input string.
	 * @throws ParseException If the input can not be read
	 */
	Player parsePlayerFromLine(String l) throws ParseException;

	/**
	 * Parses a move from an input String
	 * @param l A single line String
	 * @return The move matching the specified actions, if possible
	 * @throws ParseException If the input can not be read
	 */
	IMove parseMoveFromLine(String l) throws ParseException;

	/**
	 * Helper method to parse PlaceWallMoves
	 * @param matcher A pattern matcher that matches the specification for a PlaceWallMove,
	 *                   used to access the groups of the matcher input.
	 * @return The specified PlaceWallMove
	 * @throws ParseException If the input can not be read
	 */
	PlaceWallMove parsePlaceWallMoveFromMatcher(Matcher matcher) throws ParseException;
}
