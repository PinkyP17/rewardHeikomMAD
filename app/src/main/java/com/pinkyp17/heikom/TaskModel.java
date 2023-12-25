package com.pinkyp17.heikom;

public class TaskModel {
    String taskText;
    int imageFirst, imageSecond;


    public TaskModel(String taskText, int imageFirst, int imageSecond) {
        this.taskText = taskText;
        this.imageFirst = imageFirst;
        this.imageSecond = imageSecond;

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




}
