package com.pinkyp17.heikom;

import android.content.Context;
import android.content.SharedPreferences;

public class TaskModel {
    private String taskText;
    private int imageFirst;
    private int imageSecond;

    private int pointsVal;
    private boolean isClicked; // New field to track the clicked state

    public TaskModel(String taskText, int imageFirst, int imageSecond,int pointsVal) {
        this.taskText = taskText;
        this.imageFirst = imageFirst;
        this.imageSecond = imageSecond;
        this.pointsVal = pointsVal;
        this.isClicked = false; // Initialize as not clicked
    }

    public String getTaskText() {
        return taskText;
    }

    public int getImageFirst() {
        return imageFirst;
    }

    public int getImageSecond() {
        return imageSecond;
    }

    public boolean isClicked() {
        return isClicked;
    }

    public int getPointsVal() {
        return pointsVal;
    }

    public void setClicked() {
        isClicked = !isClicked;
    }

    //testing new logic/method for clickable
    public boolean isClicked(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("ClickedStates", Context.MODE_PRIVATE);
        return sharedPreferences.getBoolean("clicked_" + getId(), false);
    }

    public void setClicked(Context context, boolean clicked) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("ClickedStates", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("clicked_" + getId(), clicked);
        editor.apply();
    }

    private String getId() {
        // Implement a unique identifier logic here
        // Example: return a unique ID based on the task's properties
        return taskText + "_" + pointsVal; // This should be a unique identifier for each task
    }
}

