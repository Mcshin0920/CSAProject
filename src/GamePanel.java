import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Represents the panel where the game is displayed.
 */
public class GamePanel extends JPanel implements ActionListener, KeyListener {
    private final Timer timer;
    private Snake snake;
    private Food food;
    private boolean running = true;
    private JButton playAgainButton;

    /**
     * Constructs a new GamePanel object.
     */
    public GamePanel() {
        this.setPreferredSize(new Dimension(Board.WIDTH, Board.HEIGHT));
        this.setBackground(Color.BLACK);
        this.setFocusable(true);
        this.addKeyListener(this);

        initGame();

        timer = new Timer(100, this);
        timer.start();
    }

    /**
     * Initializes the game by creating a new snake and food instance.
     */
    private void initGame() {
        int UNIT_SIZE = 25;
        snake = new Snake(UNIT_SIZE);
        food = new Food(UNIT_SIZE);
        running = true;

        // Remove play again button if it exists
        if (playAgainButton != null) {
            this.remove(playAgainButton);
            playAgainButton = null;
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (running) {
            draw(g);
        } else {
            gameOver(g);
        }
    }

    /**
     * Draws the snake and food on the panel.
     *
     * @param g the Graphics context
     */
    public void draw(Graphics g) {
        snake.draw(g);
        food.draw(g);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (running) {
            if (!snake.checkCollision()) {
                snake.move();
                checkFoodCollision();
            } else {
                running = false;
                timer.stop();
                showPlayAgainButton();
            }
        }
        repaint();
    }

    /**
     * Checks if the snake has collided with the food and grows the snake if so.
     */
    public void checkFoodCollision() {
        if (snake.getHead().equals(new Point(food.getX(), food.getY()))) {
            snake.grow();
            food.createNewFood();
        }
    }

    /**
     * Displays the game over message and score on the panel.
     *
     * @param g the Graphics context
     */
    public void gameOver(Graphics g) {
        String gameOverText = "Game Over";
        g.setColor(Color.RED);
        g.setFont(new Font("Arial", Font.BOLD, 75));
        FontMetrics metrics = getFontMetrics(g.getFont());
        g.drawString(gameOverText, (Board.WIDTH - metrics.stringWidth(gameOverText)) / 2, Board.HEIGHT / 2);

        String scoreText = "Score: " + (snake.getBody().size() - 1);
        g.setFont(new Font("Arial", Font.BOLD, 50));
        metrics = getFontMetrics(g.getFont());
        g.drawString(scoreText, (Board.WIDTH - metrics.stringWidth(scoreText)) / 2, Board.HEIGHT / 2 + 100);
    }

    /**
     * Displays the "Play Again" button on the panel.
     */
    private void showPlayAgainButton() {
        playAgainButton = new JButton("Play Again");
        playAgainButton.setFont(new Font("Arial", Font.BOLD, 30));
        playAgainButton.setBounds((Board.WIDTH - 200) / 2, Board.HEIGHT / 2 + 150, 200, 50);
        playAgainButton.addActionListener(e -> {
            initGame();
            timer.start();
            repaint();
        });

        this.setLayout(null);
        this.add(playAgainButton);
        this.repaint();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (running) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_LEFT:
                    if (snake.getDirection() != Direction.RIGHT) {
                        snake.setDirection(Direction.LEFT);
                    }
                    break;
                case KeyEvent.VK_RIGHT:
                    if (snake.getDirection() != Direction.LEFT) {
                        snake.setDirection(Direction.RIGHT);
                    }
                    break;
                case KeyEvent.VK_UP:
                    if (snake.getDirection() != Direction.DOWN) {
                        snake.setDirection(Direction.UP);
                    }
                    break;
                case KeyEvent.VK_DOWN:
                    if (snake.getDirection() != Direction.UP) {
                        snake.setDirection(Direction.DOWN);
                    }
                    break;
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }
}
