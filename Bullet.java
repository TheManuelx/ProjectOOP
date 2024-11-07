/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.spaceshooter;

/**
 *
 * @author admin
 */
import java.awt.*;

public class Bullet {
    private int x, y;
    private int speed;
    private int size;
    
    public Bullet(int x, int y) {
        this.x = x;
        this.y = y;
        this.speed = 5;
        this.size = 5;
    }
    
    public void update() {
        y -= speed;
    }
    
    public void draw(Graphics g) {
        g.setColor(Color.RED);
        g.fillOval(x, y, size, size);
    }
    
    public Rectangle getBounds() {
        return new Rectangle(x, y, size, size);
    }
    
    // Getters
    public int getY() { return y; }
}
