package snakes;

/*
 * this is the Ladder class
 * the methods are inherited from Square and overridden to specify this sub-class
 * if a player lands on a ladder, he climps up to a higher field
 *
 * @author Samuel Schwegler 16-119-695
 * @author Alain Stulz
 *
 * @param game							Game instance of the Game class
 * @param position						int value of actual position
 * @param transport						int value of the transport range
 *
 * @see Square							The mother-class
 * @see Game							Game class
 * @see squareLabel()					Method changes the look of Ladder on the board and shoes where the ladder goes
 * @see landHereOrGoHome()				Checks if a field is allready ocupied
 * @see destination()					Gets the new position after the ladder
 */


public class Ladder extends Square {

	private int transport;

	private boolean invariant() {
		return isValidTransport(transport);
	}

	private boolean isValidTransport(int transport) {
		return transport != 0 && game.isValidPosition(position + transport);
	}

	public Ladder(int transport, Game game, int position) {
		super(game, position);
		assert isValidTransport(transport);
		this.transport = transport;
		assert invariant();
	}

	@Override
	protected String squareLabel() {
		return position + "->" + this.destination().position();
	}

	@Override
	public ISquare landHereOrGoHome() {
		return this.destination().landHereOrGoHome();
	}

	protected ISquare destination() {
		return game.getSquare(position+transport);
	}
}
