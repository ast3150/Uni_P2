package exercise05;

/**
 * Created by ast on 17.04.17.
 */
public class WinningTile extends Tile {

	/**
	 * A character matching the symbol of the player that should win if reaching this tile
	 */
	Character winningPlayerSymbol;

	public WinningTile(Character playerSymbol) {
		this.winningPlayerSymbol = playerSymbol;
	}

	@Override
	public Boolean isWinningTileFor(Character playerSymbol) {
		return Character.toLowerCase(winningPlayerSymbol) == Character.toLowerCase(playerSymbol);
	}

	// Standard Helpers
	@Override
	public boolean equals(Object otherObject) {
		if (! (otherObject instanceof WinningTile)) {
			return false;
		}

		return ((WinningTile)otherObject).winningPlayerSymbol == this.winningPlayerSymbol;
	}

	@Override
	public String toString() {
		return this.playerSymbol != null ? playerSymbol.toString() : winningPlayerSymbol.toString();
	}
}
