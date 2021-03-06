package exercise05;

import java.io.PrintStream;

/**
 * Created by samuel on 16.05.17.
 */
public abstract class ServiceLocator {
    private static ServiceLocator instance;
    protected ServiceLocator() {}

    public static ServiceLocator instance() {
        if (instance == null) {
            instance = defaultServiceLocator();
        }
        return instance;
    }

    public static ServiceLocator defaultServiceLocator() {
        return new DefaultServiceLocator();
    }

    public static void setServiceLocator (ServiceLocator serviceLocator) {
        instance = serviceLocator;
    }

    public abstract IRenderer getRenderer();

    public abstract IParser getParser();

    public abstract PrintStream getPrintStream();
    //...
}