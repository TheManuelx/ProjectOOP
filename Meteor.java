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

public class Meteor {
    private int x, y;
    private int speed;
    private int size;
    
    public Meteor(int x, int y) {
        this.x = x;
        this.y = y;
        this.speed = 2;
        this.size = 30;
    }
    
    public void update() {
        y += speed;
    }
    
    public void draw(Graphics g) {
        g.setColor(Color.GRAY);
        g.fillOval(x, y, size, size);
    }
    
    public Rectangle getBounds() {
        return new Rectangle(x, y, size, size);
    }
    
    // Getters
    public int getY() { return y; }
}