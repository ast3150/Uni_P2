package exercise05;

import java.io.PrintStream;

/**
 * Created by samuel on 16.05.17.
 */
public class DefaultServiceLocator extends ServiceLocator {
    Game game;
    IRenderer renderer = new Renderer();
    IParser parser = new Parser();

    //TODO
    public Game getGame(){
        return game;
    }

    public IRenderer getRenderer(){
        return renderer;
    }

    public IParser getParser() {
        return parser;
    }

    public PrintStream getPrintStream() {
        return System.out;
    }
}