import exercise05.*;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by ast on 06.04.17.
 */
public class PlaceWallMoveTest {

	@Test
	public void testPlacingWallOnExistingWallIsInvalid() {
		// Given
		Player player = new Player("Joe Jackson", 'J', 4);
		Player[] players = { player };

		Tile e = new Tile();
		Tile j = new Tile('j');
		Tile W = new WallTile();
		Tile[][] board = {
				{j, e, e},
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

//	@Test
//	public void testPlacingWallOnEmptyTilesIsValid() {
//		// Given
//		Player player = new Player("Joe Jackson", "J", new Position(1, 1), 'R');
//		Player[] players = { player };
//		Position boardSize = new Position(3, 3);
//		Game game = new Game(players, boardSize);
//
//		Position pos1 = new Position(2, 2);
//		Position pos2 = new Position(2, 3);
//		Position[] placeWallPositions = {pos1, pos2};
//
//		PlaceWallMove move = new PlaceWallMove(placeWallPositions);
//
//		// When
//		Boolean isValid = move.isValidFor(game.getBoard(), player, game.getPlayers());
//
//		// Then
//		assertTrue(isValid);
//	}
//
//	@Test
//	public void testPlacingWallOnOccupiedTileIsInvalid() {
//		// Given
//		Player player1 = new Player("Joe Jackson", "J", new Position(1, 1), 'R');
//		Player player2 = new Player("Joe Jackson", "J", new Position(2, 3), 'L');
//		Player[] players = { player1, player2 };
//		Position boardSize = new Position(3, 3);
//		Game game = new Game(players, boardSize);
//
//		Position pos1 = new Position(2, 2);
//		Position pos2 = new Position(2, 3);
//		Position[] placeWallPositions = {pos1, pos2};
//
//		PlaceWallMove move = new PlaceWallMove(placeWallPositions);
//
//		// When
//		Boolean isValid = move.isValidFor(game.getBoard(), player1, game.getPlayers());
//
//		// Then
//		assertFalse(isValid);
//	}
//
//	@Test
//	public void testPlacingNonAdjacentWallIsInvalid() {
//		// Given
//		Player player = new Player("Joe Jackson", "J", new Position(1, 1), 'R');
//		Player[] players = { player };
//		Position boardSize = new Position(3, 3);
//		Game game = new Game(players, boardSize);
//
//		Position pos1 = new Position(1, 2);
//		Position pos2 = new Position(3, 2);
//		Position[] placeWallPositions = {pos1, pos2};
//
//		PlaceWallMove move = new PlaceWallMove(placeWallPositions);
//
//		// When
//		Boolean isValid = move.isValidFor(game.getBoard(), player, game.getPlayers());
//
//		// Then
//		assertFalse(isValid);
//	}
//
//	@Test
//	public void testPlacingDiagonalWallIsInvalid() {
//		// Given
//		Player player = new Player("Joe Jackson", "J", new Position(1, 1), 'R');
//		Player[] players = { player };
//		Position boardSize = new Position(3, 3);
//		Game game = new Game(players, boardSize);
//
//		Position pos1 = new Position(1, 2);
//		Position pos2 = new Position(2, 3);
//		Position[] placeWallPositions = {pos1, pos2};
//
//		PlaceWallMove move = new PlaceWallMove(placeWallPositions);
//
//		// When
//		Boolean isValid = move.isValidFor(game.getBoard(), player, game.getPlayers());
//
//		// Then
//		assertFalse(isValid);
//	}
//
//	@Test
//	public void testPlacingBlockingWallIsInvalid(){
//		// Given
//		Player player = new Player("Joe Jackson", "J", new Position(1, 2), 'D');
//		Player[] players = { player };
//		Position boardSize = new Position(3, 3);
//		Game game = new Game(players, boardSize);
//
//		game.getBoard()[2][2].setIsWall(true);
//		game.getBoard()[2][3].setIsWall(true);
//
//		Position pos1 = new Position(3, 1);
//		Position pos2 = new Position(3, 2);
//		Position[] placeWallPositions = {pos1, pos2};
//
//		PlaceWallMove move = new PlaceWallMove(placeWallPositions);
//
//		Renderer r = new Renderer();
//		r.render(game);
//
//		// When
//		Boolean isValid = move.isValidFor(game.getBoard(), player, game.getPlayers());
//
//		// Then
//		assertFalse(isValid);
//	}
//
//	@Test
//	public void testPlacingWallMarksTilesAsWall() throws Exception {
//		// Given
//		Player player = new Player("Joe Jackson", "J", new Position(1, 1), 'R');
//		Player[] players = { player };
//		Position boardSize = new Position(3, 3);
//		Game game = new Game(players, boardSize);
//
//		Position pos1 = new Position(1, 2);
//		Position pos2 = new Position(1, 3);
//		Position[] placeWallPositions = {pos1, pos2};
//
//		PlaceWallMove move = new PlaceWallMove(placeWallPositions);
//
//		// When
//		move.execute(game.getBoard(), player, game.getPlayers());
//
//		// Then
//		assertTrue(game.getBoard()[1][2].isWall());
//		assertTrue(game.getBoard()[1][3].isWall());
//	}

}
