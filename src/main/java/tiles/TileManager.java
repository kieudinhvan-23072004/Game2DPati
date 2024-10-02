package tiles;

import org.example.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.*;

public class TileManager {
    GamePanel gamePanel;
    public Tile[] tile;// lưu số loại tile
    public int[][] mapTileNumber;

    public TileManager(GamePanel gamePanel) throws IOException {
        this.gamePanel = gamePanel;
        this.tile = new Tile[100];
        getTileImage();
        mapTileNumber = new int[gamePanel.maxWorldRow][gamePanel.maxWorldCol];
        loadMap("data/world.txt");
    }

    public void loadMap(String path) throws IOException {
        InputStream inputStream = new FileInputStream(path);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

            for(int i = 0; i < gamePanel.maxWorldRow; i++){
                String line = bufferedReader.readLine();
                String[] str = line.split(" ");
                for(int j = 0; j < gamePanel.maxWorldCol; j++){
                    int x = Integer.parseInt(str[j]);
                    mapTileNumber[i][j] = x;
                }
            }
        bufferedReader.close();
    }

    public void getTileImage() throws IOException {
        tile[0] = new Tile();
        tile[0].image = ImageIO.read(new File("data/grass.png"));

        tile[1] = new Tile();
        tile[1].image = ImageIO.read(new File("data/wall.png"));
        tile[1].collision = true;

        tile[2] = new Tile();
        tile[2].image = ImageIO.read(new File("data/water.png"));
        tile[2].collision = true;

        tile[3] = new Tile();
        tile[3].image = ImageIO.read(new File("data/earth.png"));

        tile[4] = new Tile();
        tile[4].image = ImageIO.read(new File("data/tree.png"));
        tile[4].collision = true;

        tile[5] = new Tile();
        tile[5].image = ImageIO.read(new File("data/sand.png"));

        tile[16] = new Tile();
        tile[16].image = ImageIO.read(new File("data/016.png"));

        tile[18] = new Tile();
        tile[18].image = ImageIO.read(new File("data/018.png"));

        tile[32] = new Tile();
        tile[32].image = ImageIO.read(new File("data/032.png"));

    }

    public void draw(Graphics g2d) {

        for (int worldRow = 0; worldRow < gamePanel.maxWorldRow; worldRow++) {
            int worldY = worldRow * gamePanel.tileSize;
            for (int worldCol = 0; worldCol < gamePanel.maxWorldCol; worldCol++) {
                int tileNum = mapTileNumber[worldRow][worldCol];
                int worldX = worldCol * gamePanel.tileSize;
                int screenX = worldX - gamePanel.player.worldX + gamePanel.player.screenX;
                int screenY = worldY - gamePanel.player.worldY + gamePanel.player.screenY;
                //stop moving the camera at the edge
                if(gamePanel.player.screenX > gamePanel.player.worldX){
                    screenX = worldX;
                }
                if(gamePanel.player.screenY > gamePanel.player.worldY){
                    screenY = worldY;
                }
                int rightOffset = gamePanel.screenWidth - gamePanel.player.screenX;
                if(rightOffset > gamePanel.worldWidth - gamePanel.player.worldX){
                    screenX = gamePanel.screenWidth - (gamePanel.worldWidth - worldX);
                }
                int bottomOffset = gamePanel.screenHeight - gamePanel.player.screenY;
                if(bottomOffset > gamePanel.worldHeight - gamePanel.player.worldY){
                    screenY = gamePanel.screenHeight - (gamePanel.worldHeight - worldY);
                }

                if(worldX + gamePanel.tileSize> gamePanel.player.worldX - gamePanel.player.screenX
                        && worldX - gamePanel.tileSize < gamePanel.player.worldX + gamePanel.player.screenX
                        && worldY + gamePanel.tileSize > gamePanel.player.worldY - gamePanel.player.screenY
                        && worldY  - gamePanel.tileSize< gamePanel.player.worldY + gamePanel.player.screenY
                  ) {
                    g2d.drawImage(tile[tileNum].image, screenX, screenY, gamePanel.tileSize, gamePanel.tileSize, null);
                }
                else if(gamePanel.player.screenX > gamePanel.player.worldX || gamePanel.player.screenY > gamePanel.player.worldY
                        || rightOffset > gamePanel.worldWidth - gamePanel.player.worldX
                        || bottomOffset > gamePanel.worldHeight - gamePanel.player.worldY){
                    g2d.drawImage(tile[tileNum].image, screenX, screenY, gamePanel.tileSize, gamePanel.tileSize, null);
                }
            }
        }

    }
}
