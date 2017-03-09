package snakes;

import java.util.Random;

import static snakes.Game.*;

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
        return position + "~|O|~";
    }
}
