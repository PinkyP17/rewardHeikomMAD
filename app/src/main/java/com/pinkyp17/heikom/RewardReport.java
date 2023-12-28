package com.pinkyp17.heikom;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.LinearLayoutManager;
import java.util.ArrayList;
import java.util.List;

public class RewardReport extends Fragment {


    //declaring arraylist for the recycler view
    ArrayList<ActivitiesModels> activitiesModels = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_reward_report, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.recyclerViewPoints);
        setActivitiesModel();

        AA_ActivitiesAdapter adapter = new AA_ActivitiesAdapter(getActivity(), activitiesModels);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));




        TextView pointTextView = view.findViewById(R.id.redeemPoints);
        ImageButton pointButton = view.findViewById(R.id.rewardReportButton);
        final int[] userPoints = { PointManager.getPoints(requireContext(), "userId") };

        // Set the initial points to the TextView
        pointTextView.setText(String.valueOf(userPoints[0]));
        View.OnClickListener OCLpoint = new View.OnClickListener() {
            @Override
            public void onClick(View view){
                Navigation.findNavController(view).navigate(R.id.rewardRedeem);
            }
        };
        pointButton.setOnClickListener(OCLpoint);

        return view;
    }

    private void setActivitiesModel(){
        Activities activity = new Activities();

        for(int i=0; i< activity.activities.length; i++){
            activitiesModels.add(new ActivitiesModels(activity.activities[i], activity.points[i]));
        }
    }
}
