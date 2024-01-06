package com.pinkyp17.heikom;
import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import android.content.Context;
import android.widget.Toast;


public class FBPointManager {

    private DatabaseReference usersRef;
    private String currentUserUid; // Store the current user's UID
    private Context context;

    public FBPointManager() {
        // Initialize Firebase Database reference
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        usersRef = database.getReference("Users");

        // Get the current user's UID
        FirebaseAuth auth = FirebaseAuth.getInstance();
        if (auth.getCurrentUser() != null) {
            currentUserUid = auth.getCurrentUser().getUid();
        }
    }

    // Add points to the current user
    /*
    public void addPointsToCurrentUser(int pointsToAdd) {
        if (currentUserUid != null) {
            usersRef.child(currentUserUid).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if (dataSnapshot.exists()) {
                        int currentPoints = dataSnapshot.child("points").getValue(Integer.class);
                        int updatedPoints = currentPoints + pointsToAdd;
                        usersRef.child(currentUserUid).child("points").setValue(updatedPoints);
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    // Handle error
                }
            });
        }
    }

     */

    // Deduct points from the current user
    public void deductPointsFromCurrentUser(int pointsToDeduct) {
        if (currentUserUid != null) {
            usersRef.child(currentUserUid).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if (dataSnapshot.exists()) {
                        int currentPoints = dataSnapshot.child("points").getValue(Integer.class);
                        int updatedPoints = Math.max(0, currentPoints - pointsToDeduct);
                        usersRef.child(currentUserUid).child("points").setValue(updatedPoints);
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    // Handle error
                }
            });
        }
    }

    // Fetch points for a specific user by their name
    public void fetchPointsForUser(String userName, PointFetchListener listener) {
        usersRef.orderByChild("name").equalTo(userName).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        int points = snapshot.child("points").getValue(Integer.class);
                        listener.onPointFetchSuccess(points);
                        return; // Found the user, no need to continue
                    }
                }
                listener.onPointFetchFailure("User not found");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                listener.onPointFetchFailure(databaseError.getMessage());
            }
        });
    }

    public interface PointFetchListener {
        void onPointFetchSuccess(int points);
        void onPointFetchFailure(String message);
    }



    public FBPointManager(Context context) {
        this.context = context.getApplicationContext();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        usersRef = database.getReference("Users");

        // Get the current user's UID
        FirebaseAuth auth = FirebaseAuth.getInstance();
        if (auth.getCurrentUser() != null) {
            currentUserUid = auth.getCurrentUser().getUid();
        }
    }

    public void addPointsToCurrentUser(int pointsToAdd) {
        if (currentUserUid != null) {
            usersRef.child(currentUserUid).child("points").addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if (dataSnapshot.exists()) {
                        int currentPoints = dataSnapshot.getValue(Integer.class);
                        int updatedPoints = currentPoints + pointsToAdd;

                        // Set the updated points back to the database
                        usersRef.child(currentUserUid).child("points").setValue(updatedPoints)
                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if (task.isSuccessful()) {
                                            // Points updated successfully
                                            showToast("Points added successfully");
                                        } else {
                                            // Handle error while updating points
                                            showToast("Failed to add points");
                                        }
                                    }
                                });
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    // Handle error
                    showToast("Database operation cancelled");
                }
            });
        }
    }


    private void showToast(String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }






}

