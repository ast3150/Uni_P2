package snakes;

/*
 * this is the Square class
 * the class creates all the squares
 *
 * @author Samuel Schwegler 16-119-695
 * @author Alain Stulz
 *
 * @param game							Game instance of the Game class
 * @param position						int value of actual position
 * @param player						the actual player
 *
 * @see ISquare							The Interface of Square
 * @see position						returns the player position
 * @see moveAndLand						moves the player and checks landHereOrGoHome()
 * @see nextSquare						returns the player position plus one square
 * @see previousSquare					returns the player position minus one square
 * @see landHereOrGoHome				if the field is allready occupied (isOccupied()) the player is sent back to the start
 * @see isWormholeExit					if a child class is a WormholeExit this function gets important
 * @see toString						the square look on the playboard
 * @see isOccupied						checks if the field is occupied
 * @see player							represents the players name on the board
 */

public class Square implements ISquare {

	protected int position;
	protected Game game;
	private Player player;

	private boolean invariant() {
		return game != null
				&& game.isValidPosition(position);
	}

	public Square(Game game, int position) {
		this.game = game;
		this.position = position;
		assert invariant();
	}

	public int position() {
		return this.position;
	}

	public ISquare moveAndLand(int moves) {
		return game.findSquare(position, moves).landHereOrGoHome();
	}

	protected ISquare nextSquare() {
		return game.getSquare(position+1);
	}

	protected ISquare previousSquare() {
		return game.getSquare(position-1);
	}

	public ISquare landHereOrGoHome() {
		return this.isOccupied() ? game.firstSquare() : this;
	}

	@Override
	public boolean isWormholeExit() {
		return false;
	}

	public String toString() {
		return "[" + this.squareLabel() + this.player() + "]";
	}

	protected String squareLabel() {
		return Integer.toString(position);
	}

	public boolean isOccupied() {
		return player != null;
	}

	public void enter(Player player) {
		assert this.player == null;
		this.player = player;
	}

	public void leave(Player player) {
		assert this.player == player;
		this.player = null;
	}

	public boolean isFirstSquare() {
		return false;
	}

	public boolean isLastSquare() {
		return false;
	}

	protected String player() {
		return this.isOccupied() ? ("<" + this.player + ">") : "";
	}
}
