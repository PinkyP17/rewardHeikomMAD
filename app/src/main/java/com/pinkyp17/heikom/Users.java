package com.pinkyp17.heikom;
import java.util.Map;

public class Users {

    private String name;
    private String otherUserAttribute;
    private Map<String, Integer> points;
    private int tasksDone; // New field added

    public Users() {
        // Default constructor required for Firebase
    }

    public Users(String name, String otherUserAttribute, Map<String, Integer> points, int tasksDone) {
        this.name = name;
        this.otherUserAttribute = otherUserAttribute;
        this.points = points;
        this.tasksDone = tasksDone; // Initialize the new field
    }

    // Getters and Setters for name, otherUserAttribute, points, and tasksDone

    public Map<String, Integer> getPoints() {
        return points;
    }

    public void setPoints(Map<String, Integer> points) {
        this.points = points;
    }

    // Getter and setter for tasksDone
    public int getTasksDone() {
        return tasksDone;
    }

    public void setTasksDone(int tasksDone) {
        this.tasksDone = tasksDone;
    }

    // Include getters and setters for other fields as needed
}
