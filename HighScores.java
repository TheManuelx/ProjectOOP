/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.oop;

/**
 *
 * @author admin
 */
import java.io.*;
import java.util.*;

public class HighScores {
    private static final String FILE_NAME = "highscores.txt";
    private static final int MAX_SCORES = 10;
    
    private List<Integer> scores;
    
    public HighScores() {
        scores = new ArrayList<>();
        loadScores();
    }
    
    public void addScore(int score) {
        scores.add(score);
        Collections.sort(scores, Collections.reverseOrder());
        if (scores.size() > MAX_SCORES) {
            scores = scores.subList(0, MAX_SCORES);
        }
        saveScores();
    }
    
    public List<Integer> getScores() {
        return new ArrayList<>(scores);
    }
    
    private void loadScores() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                scores.add(Integer.parseInt(line));
            }
        } catch (IOException e) {
            System.out.println("Error loading high scores: " + e.getMessage());
        }
    }
    
    private void saveScores() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_NAME))) {
            for (int score : scores) {
                writer.println(score);
            }
        } catch (IOException e) {
            System.out.println("Error saving high scores: " + e.getMessage());
        }
    }
}