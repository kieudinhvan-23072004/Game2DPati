package ObjectsGame;

import org.example.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;

public class SuperObject {
    public BufferedImage image;
    public String name;
    public boolean collison = false;
    public int worldX, worldY;
    public Rectangle solidArea = new Rectangle(0,0, 48, 48);
    public int solidAreaDefaultX = 0, solidAreaDefaultY = 0;

    public void draw(Graphics2D g2d, GamePanel gamePanel){
        int screenX = worldX - gamePanel.player.worldX + gamePanel.player.screenX;
        int screenY = worldY - gamePanel.player.worldY + gamePanel.player.screenY;

        int x = screenX;
        int y = screenY;
        if(screenX > worldX){
            x = worldX;
        }
        if(screenY > worldY){
            y = worldY;
        }

        int rightOffset = gamePanel.screenWidth - screenX;
        if(rightOffset > gamePanel.worldWidth - worldX){
            x = gamePanel.screenWidth - (gamePanel.worldWidth - worldX);
        }
        int bottomOffset = gamePanel.screenHeight - screenY;
        if(bottomOffset > gamePanel.worldHeight - worldY){
            y = gamePanel.screenHeight - (gamePanel.worldHeight - worldY);
        }
        if(worldX + gamePanel.tileSize> gamePanel.player.worldX - gamePanel.player.screenX
                && worldX - gamePanel.tileSize < gamePanel.player.worldX + gamePanel.player.screenX
                && worldY + gamePanel.tileSize > gamePanel.player.worldY - gamePanel.player.screenY
                && worldY  - gamePanel.tileSize< gamePanel.player.worldY + gamePanel.player.screenY
        ) {
            g2d.drawImage(image, x, y, gamePanel.tileSize, gamePanel.tileSize, null);
        }
        else if(gamePanel.player.screenX > gamePanel.player.worldX || gamePanel.player.screenY > gamePanel.player.worldY
                || rightOffset > gamePanel.worldWidth - gamePanel.player.worldX
                || bottomOffset > gamePanel.worldHeight - gamePanel.player.worldY){
            g2d.drawImage(image, x, y, gamePanel.tileSize, gamePanel.tileSize, null);
        }
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }

    public void setSolidArea(Rectangle solidArea) {
        this.solidArea = solidArea;
    }
}
