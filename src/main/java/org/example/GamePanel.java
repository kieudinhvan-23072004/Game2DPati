package org.example;

import Entity.Player;
import ObjectsGame.SuperObject;
import tiles.TileManager;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class GamePanel extends JPanel implements Runnable {

    private final int originalTileSize = 16; // 16 x 16
    private final int scale = 3;
    public final int tileSize = originalTileSize * scale; // 48 * 48
    public final int maxScreenCol = 25;
    public final int maxScreenRow = 15;
    public final int screenWidth = tileSize * maxScreenCol;
    public final int screenHeight = tileSize * maxScreenRow;

    // world setting
    public final int maxWorldCol = 50, maxWorldRow = 50;
    public final int worldWidth = tileSize * maxWorldCol, worldHeight = tileSize * maxWorldRow;

    //system
    TileManager tileManager = new TileManager(this);
    KeyHander keyHander = new KeyHander(this);
    Thread gameThread;
    public CollisionCheck collisionCheck = new CollisionCheck(this);
    public AssetSetter assetSetter = new AssetSetter(this);
    public UserInterface userInterface = new UserInterface(this);

    //entity and objects
    public Player player = new Player(this, keyHander);
    public SuperObject[] objects = new SuperObject[10];// có thể hiển thị 10 items 1 lúc

    // music
    private Sound soundEffect = new Sound();
    private Sound music = new Sound();

    public int gameState;
    public final int playState = 1;
    public final int pauseState = 2;


    public GamePanel() throws IOException {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.gray);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyHander);
        this.setFocusable(true);
    }

    public void setupGame() throws IOException {
        assetSetter.setObject();
        playMusic(0);
        gameState = playState;
    }
    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }

    public void update() throws IOException {
        if(gameState == playState) {
            player.update();
        }
        if(gameState == pauseState){

        }
    }

    public void paint(Graphics g){
        Graphics2D g2d = (Graphics2D) g;
        //tile
        tileManager.draw(g2d);
        //object
        for(SuperObject x : objects){
            if(x != null){
                x.draw(g2d, this);
            }
        }
        player.draw(g2d);
        userInterface.draw(g2d);
    }

    public void playMusic(int i){
        music.setFile(i);
        music.play();
        music.loop();
    }
    public void stopMusic(){
        music.stop();
    }
    public void playSoundEffect(int i){
        soundEffect.setFile(i);
        soundEffect.play();
    }
    public void run(){
        long FPS = 60;
        long chuKi = 1000 * 1000000/FPS;
        long beginTime;
        long sleepTime;
        beginTime = System.nanoTime();
        while (gameThread != null) {
            //update game + render game
            try {
                update();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            repaint();// goi lai ham paint de ve lai
            long deltaTime = System.nanoTime() - beginTime;//tim time update + render
            sleepTime = chuKi- deltaTime;
            try {
                if(sleepTime > 0) Thread.sleep(sleepTime/1000000);
                else Thread.sleep(chuKi/2000000);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
            beginTime = System.nanoTime();
        }

    }
}
