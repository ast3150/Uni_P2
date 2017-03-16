package snakes;

/**
 * Gives the player a second dice roll
 *
 * @author Samuel Schwegler 16-119-695
 * @author Alain Stulz 16-119-414
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
