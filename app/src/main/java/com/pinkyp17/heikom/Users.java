package com.pinkyp17.heikom;
import java.util.Map;

public class Users {

    private String name;
    private String otherUserAttribute;
    private Map<String, Integer> points;

    public Users() {
        // Default constructor required for Firebase
    }

    public Users(String name, String otherUserAttribute, Map<String, Integer> points) {
        this.name = name;
        this.otherUserAttribute = otherUserAttribute;
        this.points = points;
    }

    // Getters and Setters for name, otherUserAttribute, and points

    public Map<String, Integer> getPoints() {
        return points;
    }

    public void setPoints(Map<String, Integer> points) {
        this.points = points;
    }
}
