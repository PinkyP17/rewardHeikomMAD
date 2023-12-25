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

import java.util.ArrayList;


public class TaskPage extends Fragment {

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

        AA_TaskAdapter adapter = new AA_TaskAdapter(getActivity(), taskModel);

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
        return view;
    }

    private void setTaskModel(){
        taskText text = new taskText();

        for(int i=0;i< text.taskText.length;i++){
            taskModel.add(new TaskModel(text.taskText[i],text.icon[i],text.icon2[i]));
        }
    }

    class taskText{
        String[] taskText = {"Hell go to you","I am fucked", "What the hell"};
        int[] icon = {R.drawable.icon1,R.drawable.icon2,R.drawable.icon3};
        int[] icon2 = {R.drawable.icon1,R.drawable.icon2,R.drawable.icon3};
    }
}