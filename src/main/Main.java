package main;

import javax.swing.JFrame;

public class Main {
    public static void main(String[] args) {

        // 1. Create the window
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // So the program stops when you close the window
        window.setResizable(false);
        window.setTitle("My 2D Game");

        // 2. Create and add your game panel
        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel);

        // 3. Size the window to fit the preferred size of the GamePanel
        window.pack();

        // 4. Show the window
        window.setLocationRelativeTo(null); // Center the window on the screen
        window.setVisible(true);

        // 5. Start the game thread *after* the window is visible
        gamePanel.startGameThread();
    }
}