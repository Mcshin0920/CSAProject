import java.awt.*;
import java.util.ArrayList;

/**
 * Represents the snake in the game.
 */
public class Snake {
    private final ArrayList<Point> body;
    private Direction direction;
    private final int UNIT_SIZE;

    /**
     * Constructs a new Snake object.
     *
     * @param unitSize the size of each unit of the snake.
     */
    public Snake(int unitSize) {
        this.UNIT_SIZE = unitSize;
        body = new ArrayList<>();
        body.add(new Point(UNIT_SIZE, UNIT_SIZE));
        direction = Direction.RIGHT;
    }

    /**
     * Moves the snake in the current direction by adding a new head
     * and removing the last element of the body.
     */
    public void move() {
        moveInDirection();
        body.remove(body.size() - 1);
    }

    /**
     * Grows the snake by adding a new head in the current direction.
     */
    public void grow() {
        moveInDirection();
    }

    /**
     * Draws the snake on the given Graphics context.
     *
     * @param g the Graphics context
     */
    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        // Draw body
        g.setColor(Color.GREEN);
        for (int i = 1; i < body.size(); i++) {
            Point point = body.get(i);
            g.fillRect(point.x, point.y, UNIT_SIZE, UNIT_SIZE);
        }

        // Draw head
        Point head = body.get(0);
        g.setColor(Color.GREEN);
        if (body.size() == 1) {
            // Draw head as a circle if there is no body
            g.fillOval(head.x, head.y, UNIT_SIZE, UNIT_SIZE);
        } else {
            // Draw head as a semicircle if there is a body
            int startAngle = 0;
            int arcAngle = 180;
            switch (direction) {
                case LEFT -> startAngle = 90;
                case DOWN -> startAngle = 180;
                case RIGHT -> startAngle = 270;
            }
            g2d.fillArc(head.x, head.y, UNIT_SIZE, UNIT_SIZE, startAngle, arcAngle);
        }
    }

    /**
     * Checks if the snake has collided with itself or the boundaries.
     *
     * @return true if a collision is detected, false otherwise
     */
    public boolean checkCollision() {
        Point head = body.get(0);
        for (int i = 1; i < body.size(); i++) {
            if (head.equals(body.get(i))) {
                return true;
            }
        }
        return head.x < 0 || head.x >= Board.WIDTH || head.y < 0 || head.y >= Board.HEIGHT;
    }

    /**
     * Sets the direction of the snake.
     *
     * @param direction the new direction
     */
    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    /**
     * Gets the current direction of the snake.
     *
     * @return the current direction
     */
    public Direction getDirection() {
        return direction;
    }

    /**
     * Gets the head of the snake.
     *
     * @return the head of the snake as a Point
     */
    public Point getHead() {
        return body.get(0);
    }

    /**
     * Gets the body of the snake.
     *
     * @return the body of the snake as an ArrayList of Points
     */
    public ArrayList<Point> getBody() {
        return body;
    }

    /**
     * Moves the snake in the current direction by adding a new head
     * based on the direction and keeping the rest of the body unchanged.
     */
    private void moveInDirection() {
        Point head = body.get(0);
        Point newHead = new Point(head);
        switch (direction) {
            case UP -> newHead.y -= UNIT_SIZE;
            case DOWN -> newHead.y += UNIT_SIZE;
            case LEFT -> newHead.x -= UNIT_SIZE;
            case RIGHT -> newHead.x += UNIT_SIZE;
        }
        body.add(0, newHead);
    }
}
