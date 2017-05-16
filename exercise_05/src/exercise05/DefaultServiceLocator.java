package exercise05;

/**
 * Created by samuel on 16.05.17.
 */
public class DefaultServiceLocator extends ServiceLocator {
    Game game;
    Renderer renderer = new Renderer();

    //TODO
    public Game getGame(){
        return game;
    }

    public IRenderer getRenderer(){
        return renderer;
    }
}