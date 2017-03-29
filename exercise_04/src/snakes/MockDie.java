package snakes;

/**
 * creates a MockDie element for testing
 *
 * @author Samuel Schwegler 16-119-695
 * @author Alain Stulz 16-119-414
 */
public class MockDie implements IDie {
    public int count = 0;
    protected int faces;

    /**
     * Initialize a new die with given faces
     * @param faces number of sides, must be > 0
     */
    public MockDie(int faces) {
        assert faces > 0;
        this.faces = faces;
    }

    /**
     * Roll the die and obtain the result
     *
     * @return a random number between 1 and faces
     */
    @Override
    public int roll() {
        count++;
        return 2;
    }

    @Override
    public int getFaces(){
        return faces;
    }

    public void setFaces(int new_faces){
        this.faces = new_faces;
    }

    /**
     * @return times function roll is called
     */
    public int getRollTimes(){
        return count;
    }
}
