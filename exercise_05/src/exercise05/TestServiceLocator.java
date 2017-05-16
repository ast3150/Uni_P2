package exercise05;

/**
 * Created by samuel on 16.05.17.
 */
public class TestServiceLocator extends ServiceLocator {
    Game game;
    Renderer renderer;

    //TODO
    public Game getGame(){
        return game;
    }

    public IRenderer getRenderer(){
        return renderer;
    }
}
