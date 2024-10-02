package Entity;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Entity {
    public int worldX, worldY, speed;// vị trí trên world
    public String direction;
    public Rectangle solidArea;// vùng rắn
    public boolean collisonOn = false;
    public int solidAreaDefaultX, solidAreaDefaultY;
}
