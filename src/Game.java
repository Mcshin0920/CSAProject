import javax.swing.*;

/**
 * The main class to start the Snake game.
 */
public class Game {
    public static void main(String[] args) {
        // Create a new JFrame
        JFrame frame = new JFrame();

        // Create a new GamePanel
        GamePanel gamePanel = new GamePanel();

        // Add the GamePanel to the JFrame
        frame.add(gamePanel);

        // Set default close operation
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Pack the frame to fit the components
        frame.pack();

        // Make the frame visible
        frame.setVisible(true);
    }
}
