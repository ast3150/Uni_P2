package exercise05;

import java.util.LinkedList;

/**
 * Created by ast on 05.04.17.
 */
public interface IMove {

	public Boolean isValidFor(Tile[][] board, Player currentPlayer, LinkedList<Player> players);
	public Boolean execute(Tile[][] board, Player currentPlayer, LinkedList<Player> players) throws Exception;

//	public Type type;
//	public char movePlayerDirection;
//	public Position[] placeWallPositions;
//
//	public Move(Type type) {
//		this.type = type;
//	}
//
//	public Type getType() {
//		return type;
//	}
//
//	public char getMovePlayerDirection() {
//		assert (this.type == Type.MOVEPLAYER);
//		return movePlayerDirection;
//	}
//
//	public Position[] getPlaceWallPositions() {
//		assert (this.type == Type.PLACEWALL);
//		return placeWallPositions;
//	}
//
//	public void setMovePlayerDirection(char movePlayerDirection) {
//		assert (this.type == Type.MOVEPLAYER);
//		this.movePlayerDirection = movePlayerDirection;
//	}
//
//	public void setPlaceWallPositions(Position[] placeWallPositions) {
//		assert (this.type == Type.PLACEWALL);
//		this.placeWallPositions = placeWallPositions;
//	}
}

