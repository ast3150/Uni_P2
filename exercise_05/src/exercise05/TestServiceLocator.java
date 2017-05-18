package exercise05;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;

/**
 * Created by samuel on 16.05.17.
 */
public class TestServiceLocator extends ServiceLocator {
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
        try {
            return new PrintStream(new FileOutputStream("/dev/null"));
        } catch (Exception e) {
            return System.out;
        }
    }
}
