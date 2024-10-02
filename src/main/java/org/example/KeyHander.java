package org.example;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHander implements KeyListener {

    GamePanel gamePanel;
    public boolean upPress, downPress, leftPress, rightPress, spacePress, enterPress;

    public KeyHander(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        if(code == KeyEvent.VK_W){
            upPress = true;
        }
        if(code == KeyEvent.VK_S){
            downPress = true;
        }
        if(code == KeyEvent.VK_A){
            leftPress = true;
        }
        if(code == KeyEvent.VK_D){
            rightPress = true;
        }
        if(code == KeyEvent.VK_SPACE){
            spacePress = true;
        }
        if(code == KeyEvent.VK_ENTER){
            enterPress = true;
        }
        if(code == KeyEvent.VK_P){
            if(gamePanel.gameState == gamePanel.playState){
                gamePanel.gameState = gamePanel.pauseState;
            }
            else if(gamePanel.gameState == gamePanel.pauseState){
                gamePanel.gameState = gamePanel.playState;
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        if(code == KeyEvent.VK_W){
            upPress = false;
        }
        if(code == KeyEvent.VK_S){
            downPress = false;
        }
        if(code == KeyEvent.VK_A){
            leftPress = false;
        }
        if(code == KeyEvent.VK_D){
            rightPress = false;
        }
        if(code == KeyEvent.VK_SPACE){
            spacePress = false;
        }
        if(code == KeyEvent.VK_ENTER){
            enterPress = false;
        }
    }
}
