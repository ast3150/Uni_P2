package exercise05;

/**
 * Created by samuel on 16.05.17.
 */
public abstract class ServiceLocator {
    //TODO
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

    public abstract Game getGame();

    public abstract IRenderer getRenderer();
    //...
}