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

    private RecyclerView recyclerView;
    //private PointsAdapter pointsAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_reward_report, container, false);

        // Find the RecyclerView in your layout
        recyclerView = view.findViewById(R.id.recyclerViewPoints);
        //declare adapter
        //pointsAdapter = new PointsAdapter(generatePointList());

        /*
        // Set up the layout manager for the RecyclerView
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(pointsAdapter);

         */
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

    /*
    private List<PointItems> generatePointList() {
        List<PointItems> items = new ArrayList<>();

        items.add(new PointItems("100 points"));
        items.add(new PointItems("100 points"));
        items.add(new PointItems("100 points"));
        items.add(new PointItems("100 points"));

        System.out.println("add success");
        return items;
    }

     */
}
