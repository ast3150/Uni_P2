package turtle;

import java.util.ArrayList;

public class BoardMaker {
	private boolean[][] board;
	private final static int SIZE = 100;

	/**
	 * Parse the given turtle program and evaluate it. Render the trail as
	 * described in the problem description and return a SIZExSIZE board
	 * corresponding to the evaluated path.
	 *
	 * @param turtleProgram input program according to specification. may also contain invalid text!
	 * @return SIZExSIZE boolean board, where true values denote "red trail".
	 */
	public boolean[][] makeBoardFrom(String turtleProgram) throws ParserException {
		board = new boolean[SIZE][SIZE];

		// Create a parser that accepts a program and creates individual
		// programs.
		ICommandParser parser = new CommandParser();

		// Parse the commands
		ArrayList<ICommand> commands = parser.parse(turtleProgram);

		// Iterate over the parsed commands and keep track of the turtle.
		int lastColumn = 0;
		int lastRow = 0;

		for (ICommand c : commands) {
			try {
				c.executeOn(board, lastColumn, lastRow);
				lastColumn = c.getLastColumn();
				lastRow = c.getLastRow();
			} catch (Exception e) {
				throw new ParserException();
			}
		}

		return board;
	}

	/**
	 * Create a new board and return it.
	 * @return board, must be of size SIZExSIZE.
	 */
	public boolean[][] initialBoard() {
		return new boolean[SIZE][SIZE];
	}
}