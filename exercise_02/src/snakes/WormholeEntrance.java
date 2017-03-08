package snakes;

import java.util.Random;

import static snakes.Game.*;

public class WormholeEntrance extends Square {

    Random randomGenerator = new Random();
    private int exit = randomGenerator.nextInt(2);

    public WormholeEntrance(Game game, int position)
	{
		super(game, position);
	}

	game.exits

    @Override
    protected String squareLabel() {
        return position + "~|O|~" + exit;
    }

    @Override
    public ISquare landHereOrGoHome() {
        return this.destination().landHereOrGoHome();
    }

    protected ISquare destination() {
        return game.getSquare(exit);
    }
}
