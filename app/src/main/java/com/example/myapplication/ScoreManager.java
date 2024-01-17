package com.example.myapplication;

public class ScoreManager {
    private int points;
    private int maxPoints = 5000;

    public ScoreManager() {
        points = 0;
    }

    public int getPoints() {
        return points;
    }

    public void increasePoints(int amount) {
        points += amount;
    }

    public void decreasePoints(int amount) {
        points -= amount;
    }

    public void resetPoints() {
        points = 0;
    }

    public int getMaxPoints() {
        return maxPoints;
    }
}
