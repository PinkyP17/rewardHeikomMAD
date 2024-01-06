package com.pinkyp17.heikom;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.HashMap;
import java.util.Map;

public class FirebasePointManager {

    private DatabaseReference usersRef;

    public FirebasePointManager() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        usersRef = database.getReference("Users");
    }

    public void saveUserWithPoints(String userName, String otherUserAttribute, Map<String, Integer> points) {
        Map<String, Object> userData = new HashMap<>();
        userData.put("name", userName);
        userData.put("fatherName", otherUserAttribute);
        userData.put("points", points);

        usersRef.child(userName).setValue(userData);
    }

    public void fetchPointsForUser(String userName, PointFetchListener listener) {
        usersRef.child(userName).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    Users user = dataSnapshot.getValue(Users.class);
                    if (user != null && user.getPoints() != null) {
                        int totalPoints = calculateTotalPoints(user.getPoints());
                        listener.onPointFetchSuccess(totalPoints);
                    } else {
                        listener.onPointFetchFailure("User data or points are null");
                    }
                } else {
                    listener.onPointFetchFailure("User not found");
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                listener.onPointFetchFailure(databaseError.getMessage());
            }
        });
    }

    public void addPointsToUser(String userName, int additionalPoints) {
        usersRef.child(userName).child("points").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    Map<String, Object> currentPointsMap = (Map<String, Object>) dataSnapshot.getValue();

                    // Ensure the currentPointsMap is not null
                    if (currentPointsMap == null) {
                        currentPointsMap = new HashMap<>();
                    }

                    // Iterate over the keys in the points map
                    for (String pointKey : currentPointsMap.keySet()) {
                        // Get the current point value for each key
                        Object value = currentPointsMap.get(pointKey);

                        // Check if the value is an Integer or a Long
                        if (value instanceof Integer) {
                            Integer currentPointValue = (Integer) value;
                            // Add the additional points
                            currentPointValue += additionalPoints;
                            // Update the current points map with the new value
                            currentPointsMap.put(pointKey, currentPointValue);
                        } else if (value instanceof Long) {
                            Long currentPointValue = (Long) value;
                            // Add the additional points
                            currentPointValue += additionalPoints;
                            // Update the current points map with the new value
                            currentPointsMap.put(pointKey, currentPointValue);
                        }
                    }

                    // Update the user's points map in the database
                    usersRef.child(userName).child("points").updateChildren(currentPointsMap);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Handle database error here if needed
            }
        });
    }




    public void removePointsFromUser(String userName, int pointsToRemove) {
        usersRef.child(userName).child("points").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    Map<String, Object> currentPointsMap = (Map<String, Object>) dataSnapshot.getValue();

                    // Ensure the currentPointsMap is not null
                    if (currentPointsMap == null) {
                        currentPointsMap = new HashMap<>();
                    }

                    // Iterate over the keys in the points map
                    for (String pointKey : currentPointsMap.keySet()) {
                        // Get the current point value for each key
                        Object value = currentPointsMap.get(pointKey);

                        // Check if the value is an Integer or a Long
                        if (value instanceof Integer) {
                            Integer currentPointValue = (Integer) value;
                            // Subtract the points to remove
                            currentPointValue -= pointsToRemove;
                            // Ensure the total points do not go below zero
                            if (currentPointValue < 0) {
                                currentPointValue = 0;
                            }
                            // Update the current points map with the new value
                            currentPointsMap.put(pointKey, currentPointValue);
                        } else if (value instanceof Long) {
                            Long currentPointValue = (Long) value;
                            // Subtract the points to remove
                            currentPointValue -= pointsToRemove;
                            // Ensure the total points do not go below zero
                            if (currentPointValue < 0) {
                                currentPointValue = 0L;
                            }
                            // Update the current points map with the new value
                            currentPointsMap.put(pointKey, currentPointValue);
                        }
                    }

                    // Update the user's points map in the database
                    usersRef.child(userName).child("points").updateChildren(currentPointsMap);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Handle database error here if needed
            }
        });
    }




    private int calculateTotalPoints(Map<String, Integer> points) {
        int totalPoints = 0;
        for (int value : points.values()) {
            totalPoints += value;
        }
        return totalPoints;
    }

    public interface PointFetchListener {
        void onPointFetchSuccess(int points);
        void onPointFetchFailure(String message);
    }
}

