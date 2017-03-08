package snakes;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class RollAgainSquareTest extends SquareTest {
	private static int TESTSQUAREPOSITION = 7;

	@Override
	@Before
	public void newGame() {
		initializeGame(15);
		game.setSquare(TESTSQUAREPOSITION, new RollAgainSquare(game, TESTSQUAREPOSITION));
	}

	@Test
	public void moveToRollAgainSquare() {
		assertEquals(1, jack.position());
		assertEquals(1, jill.position());
		game.movePlayer(6); // moves Jack
		assertTrue("Jack moves at least one over RollAgainSquare", jack.position() > TESTSQUAREPOSITION);
		assertTrue("Jack moves no more than die allows", jack.position() <= TESTSQUAREPOSITION + Die.FACES);
		assertEquals(1, jill.position());
		int jackPosition = jack.position();
		game.movePlayer(5); // moves Jill
		assertEquals(jackPosition, jack.position());
		assertEquals(6, jill.position());
		game.movePlayer(2); // moves Jack
		assertEquals(jackPosition + 2, jack.position());
		assertEquals(6, jill.position());
		assertTrue(jack.position() > 7);
	}

	@Test
	public void moveToRollAgainSquareByLadder() {
		game.setSquareToLadder(3, TESTSQUAREPOSITION);
		assertEquals(1, jack.position());
		assertEquals(1, jill.position());

		game.movePlayer(2); // moves Jack
		assertTrue(jack.position() > TESTSQUAREPOSITION);
		assertEquals(1, jill.position());
	}

}
