package snakes;

/**
 * Created by ast on 21.03.17.
 */
public class Die implements IDie {
    protected final int faces;

    /**
     * Initialize a new die with given faces
     * @param faces number of sides, must be > 0
     */
    public Die(int faces) {
        assert faces > 0;
        this.faces = faces;
    }

    /**
     * Roll the die and obtain the result. It makes no sense to test this method because it only does return a random numbe
     *
     * @return a random number between 1 and faces
     */
    @Override
    public int roll() {
        int result = 1 + (int) (faces * Math.random());
        assert result >= 1 && result <= faces;
        return result;
    }

    @Override
    public int getFaces() {
        return faces;
    }
}