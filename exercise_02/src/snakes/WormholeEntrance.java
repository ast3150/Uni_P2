package snakes;

/*
 * This is the WormholeEntrance class
 * The methods are inherited from Square and overridden to specify this sub-class
 * From here the players are transfered to random WormholeExits
 *
 * @author Samuel Schwegler 16-119-695
 * @author Alain Stulz
 *
 * @param game							Game instance of the Game class
 * @param position						int value of actual position
 * @param exit_rand						a random number in the range of the size of wormholeExits
 *
 * @see Square							The mother-class
 * @see Game							Game class
 * @see wormholeExits()					Method contains the list of WormholeExit
 * @see squareLabel()					Method changes the look of WormholeExits on the board.
 * @see landHereOrGoHome()				Method checks if the randomly selected WormholeExit is not ocupied
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
