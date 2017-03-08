package snakes;

/**
 * Created by ast on 08.03.17.
 */
public class RollAgainSquare extends Square {
    public RollAgainSquare(Game game, int position) {
        super(game, position);
    }

    @Override
    public ISquare landHereOrGoHome() {
        return this.isOccupied() ? game.firstSquare() : game.findSquare(position, game.getDie().roll());
    }

}
