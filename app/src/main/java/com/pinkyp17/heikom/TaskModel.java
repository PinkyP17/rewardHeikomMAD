package com.pinkyp17.heikom;
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

    public void setClicked(boolean clicked) {
        isClicked = clicked;
    }
}

