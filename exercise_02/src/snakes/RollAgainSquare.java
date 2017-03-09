package snakes;

/*
 * This is the rollAgain class
 * The methods are inherited from Square and overridden to specify this sub-class
 * the player get a second die roll
 *
 * @author Samuel Schwegler 16-119-695
 * @author Alain Stulz
 *
 * @param game							Game instance of the Game class
 * @param position						int value of actual position
 *
 * @see Square							The mother-class
 * @see Game							Game class
 * @see landHereOrGoHome()				checks if the second roll is ok
 */

public class RollAgainSquare extends Square {
    public RollAgainSquare(Game game, int position) {
        super(game, position);
    }

    @Override
    protected String squareLabel() {
        return position + " (RollAgain)";
    }

    @Override
    public ISquare landHereOrGoHome() {
        return this.isOccupied() ? game.firstSquare() : game.findSquare(position, game.getDie().roll());
    }

}
