package com.pinkyp17.heikom;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Firebase;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.pinkyp17.heikom.databinding.ActivityMainBinding;
import com.pinkyp17.heikom.databinding.FragmentRewardRedeemBinding;


public class RewardRedeem extends Fragment {


    FragmentRewardRedeemBinding binding;
    String name, userPoints;

    FirebaseDatabase db;
    DatabaseReference reference;

    private String userName;



    public RewardRedeem() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        /*
            binding = FragmentRewardRedeemBinding.inflate(inflater, container, false);
            View view = binding.getRoot();

            binding.sentButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    name = binding.firstName.getText().toString();
                    userPoints = binding.userPoints.getText().toString();


                    if(!name.isEmpty() && !userPoints.isEmpty()){
                        Users userUse = new Users(name, userPoints);
                        db = FirebaseDatabase.getInstance();
                        reference = db.getReference("Users");
                        reference.child(name).setValue(userUse).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    // Data was successfully saved
                                    binding.firstName.setText("");
                                    binding.userPoints.setText("");
                                    Toast.makeText(getContext(), "Data saved successfully", Toast.LENGTH_SHORT).show();
                                } else {
                                    // Handle the error
                                    Toast.makeText(getContext(), "Failed to save data", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });

                    }
                    else {
                        System.out.println("Something wong");
                    }
                }
            });


        Button loginButton = view.findViewById(R.id.loginButton);
        EditText retrieveUser = view.findViewById(R.id.retrieveUser);
        EditText retrievePoint = view.findViewById(R.id.retrievePoint);
        //adding points to the user
        Button addingPoints = view.findViewById(R.id.addingPoints);
        FBPointManager pointManager = new FBPointManager(); // Initialize FBPointManager

        loginButton.setOnClickListener(v -> {
            userName = retrieveUser.getText().toString().trim();
            if (!userName.isEmpty()) {
                pointManager.fetchPointsForUser(userName, new FBPointManager.PointFetchListener() {
                    @Override
                    public void onPointFetchSuccess(int points) {
                        retrievePoint.setText(String.valueOf(points));
                    }

                    @Override
                    public void onPointFetchFailure(String message) {
                        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show();
                    }
                });
            } else {
                Toast.makeText(requireContext(), "Please enter a name", Toast.LENGTH_SHORT).show();
            }
        });

        addingPoints.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Add 10 points to the current user
                pointManager.addPointsToCurrentUser(10);
                System.out.println("fuck");

                // Fetch the updated points and update the UI
                pointManager.fetchPointsForUser(userName, new FBPointManager.PointFetchListener() {
                    @Override
                    public void onPointFetchSuccess(int updatedPoints) {
                        retrievePoint.setText(String.valueOf(updatedPoints));
                        DatabaseReference userRef = FirebaseDatabase.getInstance().getReference("Users").child(userName);
                        userRef.child("points").setValue(updatedPoints);
                    }

                    @Override
                    public void onPointFetchFailure(String errorMessage) {
                        System.out.println("Point bangang");
                    }
                });
            }
        });





        */
        View view = inflater.inflate(R.layout.fragment_reward_redeem, container, false);

        Button addingPointsButton = view.findViewById(R.id.addingPoints);
        addingPointsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to the fragment_test_database fragment
                Navigation.findNavController(v).navigate(R.id.testDatabase);
            }
        });

        return view;
        }





}
