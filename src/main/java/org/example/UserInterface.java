package org.example;

import ObjectsGame.Obj_Key;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class UserInterface {
    GamePanel gamePanel;
    Font arial_40;
    Graphics2D g2d;
    BufferedImage keyImage;

    public boolean messageOn = false;
    public String message = "";
    int messageCounter = 0;
    public boolean gameEnd = false;

    public UserInterface(GamePanel gamePanel) throws IOException {
        this.gamePanel = gamePanel;
        arial_40 = new Font("arial", Font.PLAIN, 40);
        Obj_Key key = new Obj_Key();
        keyImage = key.image;
    }

    public void showMessage(String text){
        message = text;
        messageOn = true;
    }

    public void draw(Graphics2D g2d) {
        if (!gameEnd) {
            // hien so key ma player dang co
            g2d.setFont(arial_40);
            g2d.setColor(Color.white);
            g2d.drawImage(keyImage, gamePanel.tileSize / 2, gamePanel.tileSize / 2, gamePanel.tileSize, gamePanel.tileSize, null);
            g2d.drawString("x " + gamePanel.player.hasKey, 74, 60);

            if (messageOn) {
                g2d.setFont(g2d.getFont().deriveFont(30));
                g2d.setColor(Color.yellow);
                g2d.drawString(message, gamePanel.player.screenX - gamePanel.tileSize * 3 / 2 - 10, gamePanel.player.screenY - gamePanel.tileSize);
                ++messageCounter;
                if (messageCounter > 120) {
                    messageCounter = 0;
                    messageOn = false;
                }
            }
        }
        else{
            String text = "You Win!!!";
            int textLength = text.length();
            int x = gamePanel.player.screenX - textLength/2, y = gamePanel.player.screenY - textLength/2;
            g2d.setFont(arial_40);
            g2d.setColor(Color.yellow);
            g2d.drawString(text, x, y);
            gamePanel.gameThread = null;
        }
        this.g2d = g2d;
        g2d.setFont(arial_40);
        g2d.setColor(Color.white);
        if(gamePanel.gameState == gamePanel.playState){

        }
        else if(gamePanel.gameState == gamePanel.pauseState){
            drawPauseScreen();
        }
    }

    public void drawPauseScreen(){
        g2d.setFont(g2d.getFont().deriveFont(Font.PLAIN, 80));
        String text = "PAUSED";
        int x = gamePanel.screenWidth/2 - text.length()/2 - 120;
        int y = gamePanel.screenHeight/2 - 48;
        g2d.drawString(text, x, y);
    }

}
