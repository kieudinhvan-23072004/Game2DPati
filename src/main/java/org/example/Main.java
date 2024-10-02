package org.example;

import javax.swing.*;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setTitle("Pati Game");
        //window.setResizable(false);
        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel);
        gamePanel.setupGame();
        gamePanel.startGameThread();
        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);
    }
}