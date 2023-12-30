package com.pinkyp17.heikom;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class RewardHome extends Fragment {

    ArrayList<CardModel> cardModels = new ArrayList<>();

    int[] couponImages = {R.drawable.coupon1,R.drawable.coupon2,R.drawable.coupon3};

    public RewardHome() {
        // Required empty public constructor
    }

    public static RewardHome newInstance() {
        RewardHome fragment = new RewardHome();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);


        //RecyclerView recyclerView = findViewById(R.id.couponRV);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_reward_home, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.couponRV);

        setCardModels();

        AA_CouponCardAdapter adapter = new AA_CouponCardAdapter(getActivity(), cardModels);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        return view; // Return the inflated view, not a new inflation
    }


    private void setCardModels(){
        //String[] imageName = getResources().getStringArray(R.array.couponImage);
        String[] cardText = getResources().getStringArray(R.array.couponText);
        String[] cardDesc = getResources().getStringArray(R.array.couponDesc);
        int[] points = {100,200,300};

        for(int i=0; i< cardText.length; i++){
            cardModels.add(new CardModel(couponImages[i], cardText[i], cardDesc[i], points[i]));
        }
    }
}