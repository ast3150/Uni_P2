import exercise05.*;

/**
 * Created by ast on 20.04.17.
 */
public abstract class Setup {
	public static Player setupPlayer() {
		Player player = new Player("Jimmy Fallon", 'F', 4);
		player.setPosition(new Position(2, 2));
		return player;
	}

	public static Tile[][] setupFallonBoard() {
		Tile e = new Tile();
		Tile F = new Tile('F');
		Tile f = new WinningTile('f');
		Tile W = new WallTile();
		Tile[][] board = {
				{f, e, e},
				{e, e, e},
				{e, e, F}
		};
		return board;
	}

	public static Tile[][] setupEmptyBoard(Integer size) {
		Tile e = new Tile();
		Tile[][] board = new Tile[size][size];

		for (int i=0; i<size; i++) {
			for (int j=0; j<size; j++) {
				board[i][j] = new Tile();
			}
		}

		return board;
	}

	public static Game setupGame() {
		Player player = setupPlayer();
		Player[] players = { player };

		Tile[][] board = setupFallonBoard();
		return new Game(players, board);
	}
}
