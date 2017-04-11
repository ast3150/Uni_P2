package exercise05;

import java.util.*;

import static java.lang.Math.abs;

public class PlaceWallMove implements IMove {
	Position[] wallPositions;

	public PlaceWallMove(Position[] wallPositions) {
		this.wallPositions = wallPositions;
	}

	@Override
	public Boolean isValidFor(Tile[][] board, Player currentPlayer, LinkedList<Player> players) {
		Boolean valid = true;

		valid &= currentPlayer.getNumberOfWalls() > 0;

		// Check that tiles to be set to wall are not already are walls or have players on them
		for (Position p : wallPositions) {
			valid &= board[p.x][p.y].canMoveHere();
		}

		// Check that all tiles are directly adjacent either vertically or horizontally
		for (int i = 1; i < wallPositions.length; i++) {
			Boolean areTilesAdjacent;

			areTilesAdjacent = (abs(wallPositions[i-1].x - wallPositions[i].x) == 1) ^
					(abs(wallPositions[i-1].y - wallPositions[i].y) == 1);

			valid &= areTilesAdjacent;
		}

		// Check that the placement of the wall blocks none of the players from reaching destination
		for (Player player : players) {
			valid &= canReachWinningTile(board, player);
		}

		return valid;
	}

	@Override
	public Boolean execute(Tile[][] board, Player currentPlayer, LinkedList<Player> players) throws InvalidMoveException {
		if (! isValidFor(board, currentPlayer, players)) {
			throw new InvalidMoveException();
		}

		for (Position p : wallPositions) {
			board[p.x][p.y].setIsWall(true);
		}

		currentPlayer.decrementNumberOfWalls();

		return false; // Players should never win just by placing a wall
	}

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

			if (board[pos.x][pos.y].isWinningTileFor(player)) {
				// Player can reach at least 1 winning tile, stop searching
				canReachWinningTile = true;
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

		return canReachWinningTile;
	}

	public Position[] getWallPositions() {
		return wallPositions;
	}
}
