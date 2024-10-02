package org.example;

import Entity.Entity;
import ObjectsGame.SuperObject;

public class CollisionCheck {
    private GamePanel gamePanel;

    public CollisionCheck(GamePanel gamePanel){
        this.gamePanel = gamePanel;
    }

    public void checkTile(Entity entity){
        int leftWorldX = entity.worldX + entity.solidArea.x;
        int rightWorldX = entity.worldX + entity.solidArea.x + entity.solidArea.width;
        int topWorldY =  entity.worldY + entity.solidArea.y;
        int bottomWorldY = entity.worldY + entity.solidArea.y + entity.solidArea.height;

        int leftCol = leftWorldX/gamePanel.tileSize;
        int rightCol = rightWorldX/gamePanel.tileSize;
        int topRow = topWorldY/gamePanel.tileSize;
        int bottomRow = bottomWorldY/gamePanel.tileSize;

        int tileNum1, tileNum2;
        switch (entity.direction){
            case "up":
                topRow = (topWorldY - entity.speed)/gamePanel.tileSize;
                tileNum1 = gamePanel.tileManager.mapTileNumber[topRow][leftCol];
                tileNum2 = gamePanel.tileManager.mapTileNumber[topRow][rightCol];
                if(gamePanel.tileManager.tile[tileNum1].collision == true || gamePanel.tileManager.tile[tileNum2].collision == true){
                    entity.collisonOn = true;
                }
                break;
            case "down":
                bottomRow = (bottomWorldY + entity.speed)/gamePanel.tileSize;
                tileNum1 = gamePanel.tileManager.mapTileNumber[bottomRow][leftCol];
                tileNum2 = gamePanel.tileManager.mapTileNumber[bottomRow][rightCol];
                if(gamePanel.tileManager.tile[tileNum1].collision == true || gamePanel.tileManager.tile[tileNum2].collision == true){
                    entity.collisonOn = true;
                }
                break;
            case "left":
                leftCol = (leftWorldX - entity.speed)/gamePanel.tileSize;
                tileNum1 = gamePanel.tileManager.mapTileNumber[topRow][leftCol];
                tileNum2 = gamePanel.tileManager.mapTileNumber[bottomRow][leftCol];
                if(gamePanel.tileManager.tile[tileNum1].collision == true || gamePanel.tileManager.tile[tileNum2].collision == true){
                    entity.collisonOn = true;
                }
                break;
            case "right":
                rightCol = (rightWorldX + entity.speed)/gamePanel.tileSize;
                tileNum1 = gamePanel.tileManager.mapTileNumber[topRow][rightCol];
                tileNum2 = gamePanel.tileManager.mapTileNumber[bottomRow][rightCol];
                if(gamePanel.tileManager.tile[tileNum1].collision == true || gamePanel.tileManager.tile[tileNum2].collision == true){
                    entity.collisonOn = true;
                }
                break;
        }
    }

    public int checkObject(Entity entity, boolean player){// biến player đẻ check entity này có phải là player không
        int index = 999;
        int i = 0;
        for(SuperObject obj : gamePanel.objects){
            if(obj != null){
                // dự đoán xem vị trí của player nếu di chuyển(tm -> di chuyển)

                // lấy vị trí vùng rắn của entity trên world
                entity.solidArea.x += entity.worldX;
                entity.solidArea.y += entity.worldY;

                // lấy vị trí vùng rắn của object trên world
                obj.solidArea.x += obj.worldX;
                obj.solidArea.y += obj.worldY;

                switch (entity.direction){
                    case "up":
                        entity.solidArea.y -= entity.speed;
                        if(entity.solidArea.intersects(obj.solidArea)){
                            if(obj.collison == true){
                                entity.collisonOn = true;
                            }
                            if(player == true){
                                index = i;
                            }
                        }
                        break;
                    case "down":
                        entity.solidArea.y += entity.speed;
                        if(entity.solidArea.intersects(obj.solidArea)){
                            if(obj.collison == true){
                                entity.collisonOn = true;
                            }
                            if(player == true){
                                index = i;
                            }
                        }
                        break;
                    case "left":
                        entity.solidArea.x += entity.speed;
                        if(entity.solidArea.intersects(obj.solidArea)){
                            if(obj.collison == true){
                                entity.collisonOn = true;
                            }
                            if(player == true){
                                index = i;
                            }
                        }
                        break;
                    case "right":
                        entity.solidArea.x -= entity.speed;
                        if(entity.solidArea.intersects(obj.solidArea)){
                            if(obj.collison == true){
                                entity.collisonOn = true;
                            }
                            if(player == true){
                                index = i;
                            }
                        }
                        break;
                }
                // reset
                entity.solidArea.x = entity.solidAreaDefaultX;
                entity.solidArea.y = entity.solidAreaDefaultY;
                obj.solidArea.x = obj.solidAreaDefaultX;
                obj.solidArea.y = obj.solidAreaDefaultY;
            }
            ++i;
        }
        return index;
    }
}
