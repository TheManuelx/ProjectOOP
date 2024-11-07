/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.oop;

/**
 *
 * @author admin
 */
import java.awt.*;

public class Spaceship {
    private int x, y;
    private int dx, dy;
    private int width, height;
    
    public Spaceship(int x, int y) {
        this.x = x;
        this.y = y;
        this.dx = 0;
        this.dy = 0;
        this.width = 40;
        this.height = 40;
    }
    
    public void update() {
        x += dx;
        y += dy;
        
        // Keep spaceship within screen bounds
        x = Math.max(0, Math.min(x, 800 - width));
        y = Math.max(0, Math.min(y, 600 - height));
    }
    
    public void draw(Graphics g) {
        g.setColor(Color.GREEN);
        int[] xPoints = {x, x + width / 2, x + width};
        int[] yPoints = {y + height, y, y + height};
        g.fillPolygon(xPoints, yPoints, 3);
    }
    
    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }
    
    // Getters and setters
    public int getX() { return x; }
    public int getY() { return y; }
    public int getWidth() { return width; }
    public int getHeight() { return height; }
    public void setDx(int dx) { this.dx = dx; }
    public void setDy(int dy) { this.dy = dy; }
    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }
}