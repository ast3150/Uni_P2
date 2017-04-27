import com.sun.org.apache.regexp.internal.RE;
import exercise05.*;
import org.junit.Test;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by ast on 06.04.17.
 */
public class PlaceWallMoveTest {

	@Test
	public void testPlacingWallOnExistingWallIsInvalid() {
		// Given
		Player player = Setup.setupPlayer();
		Player[] players = { player };

		Tile e = new Tile();
		Tile J = new Tile('J');
		Tile W = new WallTile();
		Tile[][] board = {
				{J, e, e},
				{e, W, W},
				{e, e, e}
		};

		Game game = new Game(players, board);

		player.setPosition(new Position(0, 0));

		Position pos1 = new Position(1, 2);
		Position pos2 = new Position(2, 2);
		Position[] placeWallPositions = {pos1, pos2};

		PlaceWallMove move = new PlaceWallMove(placeWallPositions);

		// When
		Boolean isValid = move.isValidFor(game.getBoard(), player, game.getPlayers());

		// Then
		assertFalse(isValid);
	}

	@Test
	public void testPlacingWallOnEmptyTilesIsValid() {
		// Given
		Game game = Setup.setupGame();

		Position pos1 = new Position(0, 1);
		Position pos2 = new Position(1, 1);
		Position[] placeWallPositions = {pos1, pos2};

		PlaceWallMove move = new PlaceWallMove(placeWallPositions);

		// When
		Boolean isValid = move.isValidFor(game.getBoard(), game.currentPlayer(), game.getPlayers());

		// Then
		assertTrue(isValid);
	}

	@Test
	public void testPlacingWallOnOccupiedTileIsInvalid() {
		// Given
		Game game = Setup.setupGame();

		Position pos1 = new Position(1, 2);
		Position pos2 = new Position(2, 2);
		Position[] placeWallPositions = {pos1, pos2};

		PlaceWallMove move = new PlaceWallMove(placeWallPositions);

		// When
		Boolean isValid = move.isValidFor(game.getBoard(), game.currentPlayer(), game.getPlayers());

		// Then
		assertFalse(isValid);
	}

	@Test
	public void testPlacingNonAdjacentWallIsInvalid() {
		// Given
		Game game = Setup.setupGame();

		Position pos1 = new Position(0, 1);
		Position pos2 = new Position(2, 1);
		Position[] placeWallPositions = {pos1, pos2};

		PlaceWallMove move = new PlaceWallMove(placeWallPositions);

		// When
		Boolean isValid = move.isValidFor(game.getBoard(), game.currentPlayer(), game.getPlayers());

		// Then
		assertFalse(isValid);
	}

	@Test
	public void testPlacingDiagonalWallIsInvalid() {
		// Given
		Game game = Setup.setupGame();

		Position pos1 = new Position(2, 0);
		Position pos2 = new Position(1, 1);
		Position[] placeWallPositions = {pos1, pos2};

		PlaceWallMove move = new PlaceWallMove(placeWallPositions);

		// When
		Boolean isValid = move.isValidFor(game.getBoard(), game.currentPlayer(), game.getPlayers());

		// Then
		assertFalse(isValid);
	}

	@Test
	public void testPlacingBlockingWallIsInvalid(){
		// Given
		Game game = Setup.setupGame();

		game.getBoard()[1][1] = new WallTile();
		game.getBoard()[1][2] = new WallTile();

		Position pos1 = new Position(2, 0);
		Position pos2 = new Position(2, 1);
		Position[] placeWallPositions = {pos1, pos2};

		PlaceWallMove move = new PlaceWallMove(placeWallPositions);

		// When
		Boolean isValid = move.isValidFor(game.getBoard(), game.currentPlayer(), game.getPlayers());

		// Then
		assertFalse(isValid);
	}

	@Test
	public void testPlacingWallMarksTilesAsWall() throws Exception {
		// Given
		Game game = Setup.setupGame();

		Position pos1 = new Position(0, 1);
		Position pos2 = new Position(0, 2);
		Position[] placeWallPositions = {pos1, pos2};

		PlaceWallMove move = new PlaceWallMove(placeWallPositions);

		// When
		move.execute(game.getBoard(), game.currentPlayer(), game.getPlayers());

		// Then
		assertTrue(game.getBoard()[0][1] instanceof WallTile);
		assertTrue(game.getBoard()[0][2] instanceof WallTile);
	}

	@Test
	public void testPlacingWallDecrementsPlayerNumberOfWalls() throws Exception {
		// Given
		Game game = Setup.setupGame();

		Position pos1 = new Position(0, 1);
		Position pos2 = new Position(0, 2);
		Position[] placeWallPositions = {pos1, pos2};

		PlaceWallMove move = new PlaceWallMove(placeWallPositions);

		int wallsBeforeMove = game.currentPlayer().getNumberOfWalls();

		// When
		move.execute(game.getBoard(), game.currentPlayer(), game.getPlayers());

		int wallsAfterMove = game.currentPlayer().getNumberOfWalls();

		// Then
		assertEquals(--wallsBeforeMove, wallsAfterMove);
	}

	@Test
	public void testPlacingCutOffWallIsValidIfDoesNotBlock() throws Exception {
		// Given
		Game game = Setup.setupGame();

		game.getBoard()[0][2] = game.getBoard()[2][2];
		game.getBoard()[2][2] = new Tile();
		game.currentPlayer().setPosition(new Position(0, 2));

		game.getBoard()[1][1] = new WallTile();
		game.getBoard()[1][2] = new WallTile();

		Position pos1 = new Position(1, 0);
		Position pos2 = new Position(2, 0);
		Position[] placeWallPositions = {pos1, pos2};

		PlaceWallMove move = new PlaceWallMove(placeWallPositions);

		// When
		Boolean isValid = move.isValidFor(game.getBoard(), game.currentPlayer(), game.getPlayers());

		// Then
		assertTrue(isValid);
	}
}
