package Entity;

import org.example.GamePanel;
import org.example.KeyHander;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Player extends Entity{
    private GamePanel gamePanel;
    private KeyHander keyHander;

    private BufferedImage up1, up2, down1, down2, left1, left2, right1,
            right2, upStand, leftStand,
            rightStand, downStand;
    private String stand;
    private int spriteCounter = 0;
    private int spriteNum = 1;

    public final int screenX, screenY;// vị trí nhân vật trên màn hình
    public int hasKey = 0;// so key ma ng choi co

    public Player(GamePanel gamePanel, KeyHander keyHander) throws IOException {
        this.gamePanel = gamePanel;
        this.keyHander = keyHander;
        screenX = (gamePanel.screenWidth - gamePanel.tileSize)/2;
        screenY = (gamePanel.screenHeight - gamePanel.tileSize)/2;
        solidArea = new Rectangle(10, 16, 32, 32);
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        setDefaultValues();
        getPlayerImage();
    }
    public void setDefaultValues(){
        worldX = gamePanel.tileSize * 23;
        worldY = gamePanel.tileSize * 21;
        speed = 4;
        direction = "down";
        stand = "down";
    }


    public void getPlayerImage() throws IOException {
        up1 = ImageIO.read(new File("data/cogi_up1.png"));
        up2 = ImageIO.read(new File("data/cogi_up2.png"));
        upStand = ImageIO.read(new File("data/cogi_up_stand.png"));
        down1 = ImageIO.read(new File("data/cogi_down1.png"));
        down2 = ImageIO.read(new File("data/cogi_down2.png"));
        downStand = ImageIO.read(new File("data/cogi_down_stand.png"));
        left1 = ImageIO.read(new File("data/cogi_left1.png"));
        left2 = ImageIO.read(new File("data/cogi_left2.png"));
        leftStand = ImageIO.read(new File("data/cogi_left_stand.png"));
        right1 = ImageIO.read(new File("data/cogi_right1.png"));
        right2 = ImageIO.read(new File("data/cogi_right2.png"));
        rightStand = ImageIO.read(new File("data/cogi_right_stand.png"));
    }

    public void update() throws IOException {
        if(keyHander.downPress || keyHander.upPress || keyHander.leftPress || keyHander.rightPress){
            if(keyHander.upPress){
                direction = "up";
                stand = "up";
            }

            if (keyHander.downPress) {
                direction = "down";
                stand = "down";
            }

            if (keyHander.leftPress) {
                direction = "left";
                stand = "left";
            }

            if (keyHander.rightPress) {
                direction = "right";
                stand = "right";
            }

            // check tile collison
            collisonOn = false;
             gamePanel.collisionCheck.checkTile(this);

            // check object collison
           int objIndex = gamePanel.collisionCheck.checkObject(this, true);
           pickUpObject(objIndex);
            if(collisonOn == false){
                switch (direction){
                    case "up":
                        worldY -= speed;
                        break;
                    case "down":
                        worldY += speed;
                        break;
                    case "left":
                        worldX -= speed;
                        break;
                    case "right":
                        worldX += speed;
                        break;
                }
            }
            spriteCounter++;
            if (spriteCounter > 15) {
                if(spriteNum != 3){
                    if (spriteNum == 1) spriteNum = 2;
                    else if (spriteNum == 2) spriteNum = 1;
                }
                spriteCounter = 0;
            }
        }
        else{
            switch (stand){
                case "up":
                    direction = "up";
                    spriteNum = 3;
                    break;
                case "down":
                    direction = "down";
                    spriteNum = 3;
                    break;
                case "left":
                    direction = "left";
                    spriteNum = 3;
                    break;
                case "right":
                    direction = "right";
                    spriteNum = 3;
                    break;
            }
        }

    }

    public void pickUpObject(int index) throws IOException {
        if(index != 999){
            String objectName = gamePanel.objects[index].name;
            switch (objectName){
                case "key":
                    gamePanel.playSoundEffect(1);
                    hasKey++;
                    gamePanel.objects[index] = null;
                    gamePanel.userInterface.showMessage("You got a key!!!");
                    break;
                case "door":
                    if(hasKey > 0){
                        gamePanel.playSoundEffect(3);
                        BufferedImage image = ImageIO.read(new File("data/door_opend.png"));
                        gamePanel.objects[index].setImage(image);
                        gamePanel.objects[index].setSolidArea(new Rectangle(0,0,0,0));
                        hasKey--;
                        gamePanel.userInterface.showMessage("You opened the door!!!");
                    }
                    else{
                        gamePanel.userInterface.showMessage("You don't have a key!!!");
                    }
                    break;
                case "boots":
                    gamePanel.playSoundEffect(2);
                    speed += 2;
                    gamePanel.objects[index] = null;
                    gamePanel.userInterface.showMessage("Speed Up!!!");
                    break;
                case "chest":
                    gamePanel.userInterface.gameEnd = true;
                    gamePanel.stopMusic();
                    gamePanel.playSoundEffect(4);
                    break;
            }
        }
    }
    public void draw(Graphics2D g2d){
        BufferedImage image = null;
             if(direction == "up") {
                if (spriteNum == 1) image = up1;
                if (spriteNum == 2) image = up2;
                if (spriteNum == 3) image = upStand;
             }

            else if(direction == "down") {
                if (spriteNum == 1) image = down1;
                if (spriteNum == 2) image = down2;
                if (spriteNum == 3) image = downStand;
            }

            else if(direction == "left"){
                if (spriteNum == 1) image = left1;
                if (spriteNum == 2) image = left2;
                if (spriteNum == 3) image = leftStand;
            }

            else if(direction == "right") {
                if (spriteNum == 1) image = right1;
                if (spriteNum == 2) image = right2;
                if (spriteNum == 3) image = rightStand;
            }

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
        g2d.drawImage(image,x, y, gamePanel.tileSize, gamePanel.tileSize, null);
            if(spriteNum == 3) spriteNum = 1;
    }
}
