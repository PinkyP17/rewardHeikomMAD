package com.pinkyp17.heikom;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class TestDatabase extends Fragment {

    private EditText firstNameEditText, secondNameEditText, pointsInputEditText;
    private Button submitButton;

    // Initialize Firebase Database reference
    private DatabaseReference usersRef;
    private FirebasePointManager firebasePointManager; // Instance of FirebasePointManager


    public TestDatabase() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Initialize Firebase Database reference
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        usersRef = database.getReference("Users");

        // Initialize FirebasePointManager
        firebasePointManager = new FirebasePointManager();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_test_database, container, false);

        firstNameEditText = view.findViewById(R.id.firstName);
        secondNameEditText = view.findViewById(R.id.secondName);
        pointsInputEditText = view.findViewById(R.id.pointsInput);
        submitButton = view.findViewById(R.id.submitButton);



        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String firstName = firstNameEditText.getText().toString().trim();
                String secondName = secondNameEditText.getText().toString().trim();
                String pointsInput = pointsInputEditText.getText().toString().trim();

                if (!firstName.isEmpty() && !secondName.isEmpty() && !pointsInput.isEmpty()) {
                    String uniqueIdentifier = firstName + "_" + System.currentTimeMillis();
                    Map<String, Integer> pointsMap = new HashMap<>();
                    pointsMap.put(uniqueIdentifier, Integer.parseInt(pointsInput));

                    // Use FirebasePointManager to save data
                    firebasePointManager.saveUserWithPoints(firstName, secondName, pointsMap);

                    // Clear input fields
                    firstNameEditText.setText("");
                    secondNameEditText.setText("");
                    pointsInputEditText.setText("");

                    Toast.makeText(requireContext(), "Data submitted successfully", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(requireContext(), "Please fill in all fields", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Find references to the UI elements in onCreateView
        EditText getFirstNameEditText = view.findViewById(R.id.getFirstName);
        EditText getPointsEditText = view.findViewById(R.id.getPoints);
        Button fetchUserButton = view.findViewById(R.id.fetchUser);

        // Set an OnClickListener for the "Get Data" button
        fetchUserButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userName = getFirstNameEditText.getText().toString().trim();

                if (!userName.isEmpty()) {
                    // Use the FirebasePointManager to fetch points for the user
                    firebasePointManager.fetchPointsForUser(userName, new FirebasePointManager.PointFetchListener() {
                        @Override
                        public void onPointFetchSuccess(int points) {
                            // Display the fetched points in the getPointsEditText
                            getPointsEditText.setText("Points: " + points);
                        }

                        @Override
                        public void onPointFetchFailure(String message) {
                            // Handle the fetch failure, e.g., display an error message
                            getPointsEditText.setText("Fetch Failed: " + message);
                        }
                    });
                } else {
                    getPointsEditText.setText("Please enter a name");
                }
            }
        });

        Button addPointsBtn = view.findViewById(R.id.addPointsBtn);
        EditText pointsAddingTest = view.findViewById(R.id.pointsAddingTest);

        addPointsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userName = getFirstNameEditText.getText().toString().trim();
                String additionalPointsText = pointsAddingTest.getText().toString().trim();

                if (!userName.isEmpty() && !additionalPointsText.isEmpty()) {
                    int additionalPoints = Integer.parseInt(additionalPointsText);

                    // Use FirebasePointManager to add points to the user
                    firebasePointManager.addPointsToUser(userName, additionalPoints);

                    // Clear the input field
                    pointsAddingTest.setText("");

                    Toast.makeText(requireContext(), "Points added successfully", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(requireContext(), "Please fill in all fields", Toast.LENGTH_SHORT).show();
                }
            }
        });

        Button deductPointsBtn = view.findViewById(R.id.deductingPointBtn);

        deductPointsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = getFirstNameEditText.getText().toString().trim();


                //testing the function
                if(!username.isEmpty()){
                    int deductPoints = 30;

                    //Use FirebasePointManager to deduct point
                    firebasePointManager.removePointsFromUser(username,deductPoints);
                    Toast.makeText(requireContext(), "Points deducted successfully", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(requireContext(), "Please fill in all fields", Toast.LENGTH_SHORT).show();

                }
            }
        });





        return view;
    }
}

