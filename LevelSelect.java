/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.spaceshooter;

/**
 *
 * @author admin
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LevelSelect extends JPanel {
    private JButton level1Button, level2Button, level3Button;
    private Game game;
    
    public LevelSelect(Game game) {
        this.game = game;
        setLayout(new GridLayout(3, 1, 10, 10));
        setBackground(Color.BLACK);
        
        level1Button = createLevelButton("Level 1", 1);
        level2Button = createLevelButton("Level 2", 2);
        level3Button = createLevelButton("Level 3", 3);
        
        add(level1Button);
        add(level2Button);
        add(level3Button);
    }
    
    private JButton createLevelButton(String text, int level) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 20));
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                game.startLevel(level);
            }
        });
        return button;
    }
}
