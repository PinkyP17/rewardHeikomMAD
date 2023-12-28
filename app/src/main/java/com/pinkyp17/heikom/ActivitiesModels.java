package com.pinkyp17.heikom;

public class ActivitiesModels {
    String activityRep, pointsRep;


    public ActivitiesModels(String activity, String points) {
        this.activityRep = activity;
        this.pointsRep = points;
    }

    public String getActivity() {
        return activityRep;
    }

    public String getPoints() {
        return pointsRep;
    }
}
