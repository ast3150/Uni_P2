import exercise05.*;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

public class RendererTest {

	@Test
	public void testConvertToString() {
		// given
		Player player = mock(Player.class);
		when(player.toString()).thenReturn("H");

		Tile normalTile = new Tile();
		Tile wallTile = new Tile();
		wallTile.setIsWall(true);
		Tile playerTile = new Tile();
		playerTile.moveHere(player);

		Tile[] row = {wallTile, playerTile, normalTile, normalTile, wallTile};

		// when
		String renderedRow = Renderer.convertToString(row);

		// then
		String expectedRow = "#H  #";
		assertEquals(expectedRow, renderedRow);
	}

	@Test
	public void testRenderGame() {
		// given
		Player player1 = new Player( "John Doe", "D", new Position(1, 1), "R");
		Player player2 = new Player( "Louis CK", "L", new Position(3, 3), "R");
		Player[] players = {player1, player2};

		Game game = new Game(players, new Position(3, 3));

		// when
		String renderedGame = Renderer.render(game);

		// then
		String expectedGame = "#####\n#D  #\n#   #\n#  L#\n#####\n";
		assert(renderedGame.equals(expectedGame));
	}

}
