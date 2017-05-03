package exercise05;

import java.util.*;

import static java.lang.Math.abs;

/**
 * {@inheritDoc}
 *
 * Places a wall on the game board that cannot be passed by players.
 */
public class PlaceWallMove implements IMove {
	Position[] wallPositions;

	public PlaceWallMove(Position[] wallPositions) {
		this.wallPositions = wallPositions;
	}

	/**
	 * {@inheritDoc}
	 *
	 * Performs additional checks to make sure that a Wall consists only of adjacent tiles and
	 * does not block any players from reaching at least one goal tile.
	 */
	@Override
	public Boolean isValidFor(Tile[][] board, Player currentPlayer, LinkedList<Player> players) {
		Boolean valid = true;

		valid &= currentPlayer.getNumberOfWalls() > 0;

		//Checks if input is in range
		for(Position p : wallPositions){
			if(!(p.row < board.length) && (p.col < board[0].length)){
				return false;
			}
		}

		// Check that tiles to be set to wall are not already are walls or have players on them
		for (Position p : wallPositions) {
			valid &= board[p.row][p.col].canMoveHere();
		}

		// Check that all tiles are directly adjacent either vertically or horizontally
		for (int i = 1; i < wallPositions.length; i++) {
			Boolean areTilesAdjacent;

			areTilesAdjacent = (abs(wallPositions[i - 1].row - wallPositions[i].row) == 1) ^
					(abs(wallPositions[i - 1].col - wallPositions[i].col) == 1);

			valid &= areTilesAdjacent;
		}

		// Check that the placement of the wall blocks none of the players from reaching destination
		for (Player player : players) {
			valid &= canReachWinningTile(board, player);
		}

		return valid;
	}

	/**
	 * {@inheritDoc}
	 *
	 * Also decreases the player's number of walls;
	 * @return False, since a player should not win the game just by placing a wall
	 */
	@Override
	public Boolean execute(Tile[][] board, Player currentPlayer, LinkedList<Player> players) throws InvalidMoveException {
		if (! isValidFor(board, currentPlayer, players)) {
			throw new InvalidMoveException();
		}

		for (Position p : wallPositions) {
			board[p.row][p.col] = new WallTile();
		}

		currentPlayer.decrementNumberOfWalls();

		return false;
	}

	/**
	 * Checks that a player can reach at least one winning tile.
	 * Uses Breadth First Search algorithm, be aware of the possible performance impact for large boards
	 *
	 * @param board The board to check on
	 * @param player The player to check
	 * @return True if there exists a path to at least one winning tile
	 */
	private Boolean canReachWinningTile(Tile[][] board, Player player) {
		Boolean canReachWinningTile = false;

		// Using Breadth First Search algorithm
		HashSet<Position> checkedPositions = new HashSet<>();
		Queue<Position> reachablePositions = new LinkedList<>();

		for (Position p : this.wallPositions) {
			// Tiles where the wall will be set cannot be reached
			checkedPositions.add(p);
		}

		checkedPositions.add(player.getPosition());
		reachablePositions.add(player.getPosition());

		while (!reachablePositions.isEmpty()) {
			Position pos = reachablePositions.remove();

			if (board[pos.row][pos.col].isWinningTileFor(player.getSymbol())) {
				// Player can reach at least 1 winning tile, stop searching
				canReachWinningTile = true;
				break;
			}

			ArrayList<Position> positionsToCheck = new ArrayList<>();

			try { positionsToCheck.add(pos.moveLeft(board.length - 1, board[0].length - 1)); } catch (InvalidMoveException e) {}
			try { positionsToCheck.add(pos.moveRight(board.length - 1, board[0].length - 1)); } catch (InvalidMoveException e) {}
			try { positionsToCheck.add(pos.moveUp(board.length - 1, board[0].length - 1)); } catch (InvalidMoveException e) {}
			try { positionsToCheck.add(pos.moveDown(board.length - 1, board[0].length - 1)); } catch (InvalidMoveException e) {}

			for (Position positionToCheck : positionsToCheck) {
				if (!checkedPositions.contains(positionToCheck)) {
					Tile t = board[positionToCheck.row][positionToCheck.col];
					if (! (t instanceof WallTile)) {
						reachablePositions.add(positionToCheck);
					}
					checkedPositions.add(positionToCheck);
				}
			}
		}

		return canReachWinningTile;
	}

	public Position[] getWallPositions() {
		return wallPositions;
	}
}
