package com.pinkyp17.heikom;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;



public class TaskPage extends Fragment implements AA_TaskAdapter.PointAdditionListener {
    ArrayList<TaskModel> taskModel = new ArrayList<>();


    //for progress bar
    private int CurrentProgress = 0;
    private ProgressBar progressBar;
    private Button startProgress;
    public TaskPage() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static TaskPage newInstance() {
        TaskPage fragment = new TaskPage();
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.task_page, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.taskRecycleView);

        setTaskModel();
        String userId = "userId";
        //AA_TaskAdapter adapter = new AA_TaskAdapter(getActivity(), taskModel, userId);

        AA_TaskAdapter adapter = new AA_TaskAdapter(requireContext(), taskModel, userId);
        adapter.setPointAdditionListener(this); // Set the listener to this fragment
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        progressBar = view.findViewById(R.id.progressBar);
        /*startProgress = view.findViewById(R.id.addProgress);

        startProgress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CurrentProgress = CurrentProgress + 10;
                progressBar.setProgress(CurrentProgress);
                progressBar.setMax(100);
            }
        });
        */

        //testing the point system
        TextView pointTextView = view.findViewById(R.id.pointTest);
        Button addPoint = view.findViewById(R.id.addPoints);

        // Declare an array to hold the user points
        final int[] userPoints = {PointManager.getPoints(requireContext(), "userId")};

        // Set the initial points to the TextView
        pointTextView.setText(String.valueOf(userPoints[0]));

        addPoint.setOnClickListener(v -> {
            // Increase points when the button is clicked
            userPoints[0] += 10; // Increase by 10, modify as needed

            // Update points in the SharedPreferences
            PointManager.setPoints(requireContext(), "userId", userPoints[0]);

            // Update the TextView with the new points
            pointTextView.setText(String.valueOf(userPoints[0]));
        });


        return view;
    }

    // Method to update points displayed in TextView
    private void updatePointsDisplay() {
        // Update points displayed in TextView (pointTextView)
        TextView pointTextView = requireView().findViewById(R.id.pointTest);
        int userPoints = PointManager.getPoints(requireContext(), "userId");
        pointTextView.setText(String.valueOf(userPoints));
    }

    public void onAddPointClicked(int position, int pointsToAdd) {
        // Implement logic to add points for the user
        String userId = "userId"; // Simulating a user ID for testing purposes
        PointManager.addPoints(requireContext(), userId, pointsToAdd);

        // Notify any UI changes or perform any actions needed after adding points
        updatePointsDisplay(); // For example, update points displayed in TextView
    }



    private void setTaskModel(){
        taskText text = new taskText();

        for(int i=0;i< text.taskText.length;i++){
            taskModel.add(new TaskModel(text.taskText[i],text.icon[i],text.icon2[i],text.points[i]));
        }
    }

    class taskText{
        String[] taskText = {"Hell go to you","I am fucked", "What the hell"};
        int[] icon = {R.drawable.icon1,R.drawable.icon2,R.drawable.icon3};
        int[] icon2 = {R.drawable.icon1,R.drawable.icon2,R.drawable.icon3};

        int[] points ={300,100,10};
    }
}