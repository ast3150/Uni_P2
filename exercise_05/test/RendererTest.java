import exercise05.*;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

public class RendererTest {
	@Before
	public void setup() {
		ServiceLocator test = new TestServiceLocator();
		ServiceLocator.setServiceLocator(test);
	}

	@Test
	public void testConvertToString() {
		// given
		Player player = mock(Player.class);
		when(player.getSymbol()).thenReturn('H');

		Tile normalTile = new Tile();
		Tile wallTile = new WallTile();
		Tile playerTile = new Tile('H');

		Tile[] row = {wallTile, playerTile, normalTile, normalTile, wallTile};

		Renderer renderer = new Renderer();

		// when
		String renderedRow = renderer.convertToString(row);

		// then
		String expectedRow = "#H  #";
		assertEquals(expectedRow, renderedRow);
	}


	@Test
	public void testRenderGame() {
		// given
		Player[] players = {};

		Tile[][] board = Setup.setupEmptyBoard(5);

		for (int i=0; i<board.length; i++) {
			// Set up top and bottom walls
			board[0][i] = new WallTile();
			board[board.length-1][i] = new WallTile();
		}
		// Place players
		board[1][0] = new Tile('D');
		board[3][3] = new Tile('L');

		Game game = new Game(players, board);

		//set to testServiceLocator and get a silentRenderer
		IRenderer renderer = ServiceLocator.instance().getRenderer();

		// when

		String renderedGame = renderer.render(game);

		// then
		String expectedGame = "#####\nD    \n     \n   L \n#####\n";
		assert(renderedGame.equals(expectedGame));
	}

}
