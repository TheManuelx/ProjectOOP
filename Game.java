/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.oop;

/**
 *
 * @author admin
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Game extends JPanel implements ActionListener, KeyListener {
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;
    private static final int DELAY = 10;
    
    private Timer timer;
    private Spaceship player;
    private List<Meteor> meteors;
    private List<Bullet> bullets;
    private int score;
    private int level;
    private boolean isRunning;
    private Random random;
    private LevelSelect levelSelect;
    private HighScores highScores;
    private GameState gameState;
    
    public void start() {
        isRunning = true;
        timer.start();
        System.out.println("Game started. Timer running: " + timer.isRunning());
    }
    
    private enum GameState {
        LEVEL_SELECT, PLAYING, GAME_OVER
    }
    
    public Game() {
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setBackground(Color.BLACK);
        setFocusable(true);
        addKeyListener(this);
        
        random = new Random();
        player = new Spaceship(WIDTH / 2, HEIGHT - 50);
        meteors = new ArrayList<>();
        bullets = new ArrayList<>();
        score = 0;
        level = 1;
        isRunning = false;
        
        levelSelect = new LevelSelect(this);
        highScores = new HighScores();
        gameState = GameState.LEVEL_SELECT;
        
        timer = new Timer(DELAY, this);
        timer.start();
    }
    
    public void startLevel(int selectedLevel) {
        level = selectedLevel;
        score = 0;
        meteors.clear();
        bullets.clear();
        player.setPosition(WIDTH / 2, HEIGHT - 50);
        isRunning = true;
        gameState = GameState.PLAYING;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (gameState == GameState.PLAYING) {
            update();
        }
        repaint();
    }
    
    private void update() {
        player.update();
        updateMeteors();
        updateBullets();
        checkCollisions();
        increaseLevel();
    }
    
    private void updateMeteors() {
        if (random.nextInt(100) < 5 + level) {
            meteors.add(new Meteor(random.nextInt(WIDTH), 0));
        }
        
        for (int i = meteors.size() - 1; i >= 0; i--) {
            Meteor meteor = meteors.get(i);
            meteor.update();
            if (meteor.getY() > HEIGHT) {
                meteors.remove(i);
            }
        }
    }
    
    private void updateBullets() {
        if (random.nextInt(100) < 10 + level) {
            bullets.add(new Bullet(player.getX() + player.getWidth() / 2, player.getY()));
        }
        
        for (int i = bullets.size() - 1; i >= 0; i--) {
            Bullet bullet = bullets.get(i);
            bullet.update();
            if (bullet.getY() < 0) {
                bullets.remove(i);
            }
        }
    }
    
    private void checkCollisions() {
        for (int i = meteors.size() - 1; i >= 0; i--) {
            Meteor meteor = meteors.get(i);
            if (player.getBounds().intersects(meteor.getBounds())) {
                gameOver();
                return;
            }
            
            for (int j = bullets.size() - 1; j >= 0; j--) {
                Bullet bullet = bullets.get(j);
                if (bullet.getBounds().intersects(meteor.getBounds())) {
                    meteors.remove(i);
                    bullets.remove(j);
                    score += 10;
                    break;
                }
            }
        }
    }
    
    private void increaseLevel() {
        if (score > level * 1000) {
            level++;
            // Increase game speed or difficulty here
        }
    }
    
    private void gameOver() {
        isRunning = false;
        gameState = GameState.GAME_OVER;
        highScores.addScore(score);
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        switch (gameState) {
            case LEVEL_SELECT:
                levelSelect.paint(g);
                break;
            case PLAYING:
                drawGame(g);
                break;
            case GAME_OVER:
                drawGameOver(g);
                break;
        }
    }
    
    private void drawGame(Graphics g) {
        player.draw(g);
        for (Meteor meteor : meteors) {
            meteor.draw(g);
        }
        for (Bullet bullet : bullets) {
            bullet.draw(g);
        }
        
        g.setColor(Color.WHITE);
        g.drawString("Score: " + score, 10, 20);
        g.drawString("Level: " + level, 10, 40);
    }
    
    private void drawGameOver(Graphics g) {
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 30));
        g.drawString("Game Over", WIDTH / 2 - 70, HEIGHT / 2);
        g.drawString("Score: " + score, WIDTH / 2 - 50, HEIGHT / 2 + 40);
        g.drawString("Press SPACE to restart", WIDTH / 2 - 120, HEIGHT / 2 + 80);
        
        g.setFont(new Font("Arial", Font.BOLD, 20));
        g.drawString("High Scores:", WIDTH / 2 - 50, HEIGHT / 2 + 120);
        List<Integer> scores = highScores.getScores();
        for (int i = 0; i < Math.min(5, scores.size()); i++) {
            g.drawString((i + 1) + ". " + scores.get(i), WIDTH / 2 - 30, HEIGHT / 2 + 150 + i * 25);
        }
    }
    
    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        
        if (gameState == GameState.PLAYING) {
            if (key == KeyEvent.VK_LEFT || key == KeyEvent.VK_A) {
                player.setDx(-5);
            }
            if (key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_D) {
                player.setDx(5);
            }
            if (key == KeyEvent.VK_UP || key == KeyEvent.VK_W) {
                player.setDy(-5);
            }
            if (key == KeyEvent.VK_DOWN || key == KeyEvent.VK_S) {
                player.setDy(5);
            }
        } else if (gameState == GameState.GAME_OVER) {
            if (key == KeyEvent.VK_SPACE) {
                gameState = GameState.LEVEL_SELECT;
            }
        }
    }
    
    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        
        if (key == KeyEvent.VK_LEFT || key == KeyEvent.VK_A || key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_D) {
            player.setDx(0);
        }
        if (key == KeyEvent.VK_UP || key == KeyEvent.VK_W || key == KeyEvent.VK_DOWN || key == KeyEvent.VK_S) {
            player.setDy(0);
        }
    }
    
    @Override
    public void keyTyped(KeyEvent e) {
        // Not used
    }
}