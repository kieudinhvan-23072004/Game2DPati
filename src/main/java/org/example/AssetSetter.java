package org.example;

import ObjectsGame.Obj_Chest;
import ObjectsGame.Obj_Door;
import ObjectsGame.Obj_Key;
import ObjectsGame.Obj_boots;

import java.io.IOException;

public class AssetSetter {
    private GamePanel gamePanel;

    public AssetSetter(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    public void setObject() throws IOException {
        gamePanel.objects[0] = new Obj_Key();
        gamePanel.objects[0].worldX = 23 * gamePanel.tileSize;
        gamePanel.objects[0].worldY = 7 * gamePanel.tileSize;

        gamePanel.objects[1] = new Obj_Key();
        gamePanel.objects[1].worldX = 23 * gamePanel.tileSize;
        gamePanel.objects[1].worldY = 40 * gamePanel.tileSize;

        gamePanel.objects[2] = new Obj_Key();
        gamePanel.objects[2].worldX = 37 * gamePanel.tileSize;
        gamePanel.objects[2].worldY = 7 * gamePanel.tileSize;

        gamePanel.objects[3] = new Obj_Door();
        gamePanel.objects[3].worldX = 10 * gamePanel.tileSize;
        gamePanel.objects[3].worldY = 11 * gamePanel.tileSize;

        gamePanel.objects[4] = new Obj_Door();
        gamePanel.objects[4].worldX = 8  * gamePanel.tileSize;
        gamePanel.objects[4].worldY = 28 * gamePanel.tileSize;

        gamePanel.objects[5] = new Obj_Door();
        gamePanel.objects[5].worldX = 12 * gamePanel.tileSize;
        gamePanel.objects[5].worldY = 22 * gamePanel.tileSize;

        gamePanel.objects[6] = new Obj_Chest();
        gamePanel.objects[6].worldX = 10 * gamePanel.tileSize;
        gamePanel.objects[6].worldY = 7 * gamePanel.tileSize;

        gamePanel.objects[7] = new Obj_boots();
        gamePanel.objects[7].worldX = 37 * gamePanel.tileSize;
        gamePanel.objects[7].worldY = 42 * gamePanel.tileSize;
    }
}
