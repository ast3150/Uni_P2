package exercise05;

import java.util.*;

import static java.lang.Math.abs;

public class PlaceWallMove implements IMove {
	Position[] positions;

	public PlaceWallMove(Position[] positions) {
		this.positions = positions;
	}

	@Override
	public Boolean isValidFor(Tile[][] board, Player currentPlayer, LinkedList<Player> players) {
		Boolean valid = true;

		valid &= currentPlayer.getNumberOfWalls() > 0;

		// Check tiles to be set to wall if they already are walls or have players on them
		for (Position p : positions) {
			valid &= board[p.x][p.y].canMoveHere();
		}

		// Check that all tiles are directly adjacent vertically xor horizontally
		for (int i = 1; i < positions.length; i++) {
			Boolean areTilesAdjacent = true;
			areTilesAdjacent = (abs(positions[i-1].x - positions[i].x) == 1) ^
					(abs(positions[i-1].y - positions[i].y) == 1);

			valid &= areTilesAdjacent;
		}

		// Check that the placement of the wall blocks none of the players from reaching destination
		for (Player player : players) {
			Boolean hasReachableWinningTile = false;

			// Using Breadth First Search algorithm
			Set<Position> checkedPositions = new HashSet<Position>();
			Queue<Position> reachablePositions = new LinkedList<Position>();

			for (Position p : this.positions) {
				// Tiles where the wall will be set cannot be reached
				checkedPositions.add(p);
			}
			reachablePositions.add(player.getPosition());

			while (!reachablePositions.isEmpty()) {
				Position pos = reachablePositions.remove();

				if (board[pos.x][pos.y].isWinningTileFor(player)) {
					// Player can reach at least 1 winning tile, stop searching
					hasReachableWinningTile = true;
					break;
				}

				Position leftPosition = pos.moveLeft();
				Position rightPosition = pos.moveRight();
				Position upPosition = pos.moveUp();
				Position downPosition = pos.moveDown();

				Position[] positionsToCheck = {leftPosition, rightPosition, upPosition, downPosition};

				for (Position positionToCheck : positionsToCheck) {
					if (!checkedPositions.contains(positionToCheck)) {
						Tile t = board[positionToCheck.x][positionToCheck.y];
						if (t.canMoveHere()) {
							reachablePositions.add(positionToCheck);
						}
						checkedPositions.add(positionToCheck);
					}


				}
			}

			valid &= hasReachableWinningTile;
		}

		return valid;
	}

	@Override
	public Boolean execute(Tile[][] board, Player currentPlayer, LinkedList<Player> players) throws Exception {
		if (! isValidFor(board, currentPlayer, players)) {
			throw new InvalidMoveException();
		}

		for (Position p : positions) {
			board[p.x][p.y].setIsWall(true);
		}

		currentPlayer.decrementNumberOfWalls();

		return false; // Players should never win just by placing a wall
	}
}
