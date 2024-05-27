import java.awt.*;

/**
 * Represents the Food generation in the game
 */
public class Food {
    private int x;
    private int y;
    private final int UNIT_SIZE;

    /**
     * Constructs a new Food object
     *
     * @param unitSize the size of the food itself
     */
    public Food(int unitSize){
        this.UNIT_SIZE = unitSize;
        createNewFood();
    }

    /**
     * Generates a position for the food to spawn in
     */
    public void createNewFood() {
        this.x = (int) (Math.random() * (Board.WIDTH / UNIT_SIZE)) * UNIT_SIZE;
        this.y = (int) (Math.random() * (Board.HEIGHT / UNIT_SIZE)) * UNIT_SIZE;
    }

    /**
     * Draws the food object into the field
     *
     * @param g the graphics library to draw the food
     */
    public void draw(Graphics g){
        g.setColor(Color.RED);
        g.fillRect(x, y, UNIT_SIZE, UNIT_SIZE);
    }

    /**
     * Returns the x position for the food
     *
     * @return returns an integer for the x position of the food
     */
    public int getX(){
        return x;
    }

    /**
     * Returns the y position for the food
     *
     * @return returns an integer for the y position of the food
     */
    public int getY(){
        return y;
    }
}
