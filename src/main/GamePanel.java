package main;
import entity.Player;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable{
    //Screen settings
    final int originalTileSize = 16; // 16*16 tile size
    final int scale = 3; // we'll increase the tile according to our window
    public final int tileSize = originalTileSize * scale;  // 48 x 48  pixels
    final int maxScreenCol = 16;
    final int maxScreenRow = 12;
    final int screenWidth = tileSize * maxScreenCol;    // 768 pixels
    final int screenHeight = tileSize * maxScreenRow;   // 576 pixels

    //FPS
    int FPS = 60;


    Thread gameThread;
    KeyHandler KeyH = new KeyHandler();
    Player player = new Player(this, KeyH);

    //default posi of player
    int playerX = 100;
    int playerY = 100;
    int playerSpeed = 4;


    // GamePanel
    public GamePanel(){
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(KeyH);
        this.setFocusable(true);
    }

    public void startGameThread () {
        gameThread = new Thread(this);
        gameThread.start();
    }// to implement time to create 60 frames / second

    @Override
    public void run() {

        double drawInterval = (double) 1000000000 / FPS; // The duration of one frame in nanoseconds
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime ;
        while(gameThread != null){
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            lastTime = currentTime;

            if(delta >= 1){
                update();
                repaint();
                delta--;
            }
        }

    }
    public void update(){
        player.update();
    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g; // converting the graphics into 2d
        player.draw(g2);
        g2.dispose();

    }
}
