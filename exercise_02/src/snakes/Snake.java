package snakes;

/*
 * this is the Snake class
 * a snake is a reverse ladder
 *
 * @author Samuel Schwegler 16-119-695
 * @author Alain Stulz
 *
 * @param game							Game instance of the Game class
 * @param position						int value of actual position
 * @param transport						int value of the transport range
 *
 * @see Ladder							The mother-class
 * @see Game							Game class
 * @see squareLabel()					Method changes the look of snake fields on the board and shoes where the snake goes
 */
public class Snake extends Ladder {

	public Snake(int transport, Game game, int position) {
		super(transport, game, position);
	}

	@Override
	protected String squareLabel() {
		return this.destination().position() + "<-" + position;
	}

}
