package snakes;

/**
 * Transfering player to a random WormholeExit.
 *
 * @see WormholeExit
 * @see wormholeExits()     gets the list of exits
 *
 * @author Samuel Schwegler 16-119-695
 * @author Alain Stulz 16-119-414
 */

import java.util.Random;

public class WormholeEntrance extends Square {
    Random randomGenerator = new Random();

    public WormholeEntrance(Game game, int position)
    {
        super(game, position);
	}

    int exit_rand = randomGenerator.nextInt(game.wormholeExits().size()+1);

    public ISquare landHereOrGoHome() {
        ISquare a = game.wormholeExits().get(exit_rand);
        return this.isOccupied() ? game.firstSquare() : a.landHereOrGoHome();
    }

    @Override
    protected String squareLabel() {
        return position + " (Entrance)";
    }
}
