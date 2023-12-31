package com.pinkyp17.heikom;

import android.content.Context;
import android.content.SharedPreferences;
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

    // Save the clicked states of coupons in SharedPreferences
    private void saveClickedStates() {
        SharedPreferences sharedPreferences = requireActivity().getSharedPreferences("ClickedStates", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        for (int i = 0; i < cardModels.size(); i++) {
            CardModel currentCard = cardModels.get(i);
            editor.putBoolean("clicked_" + i, currentCard.isClicked());
        }

        editor.apply();
    }

    // Load the clicked states of coupons from SharedPreferences
    private void loadClickedStates() {
        SharedPreferences sharedPreferences = requireActivity().getSharedPreferences("ClickedStates", Context.MODE_PRIVATE);

        for (int i = 0; i < cardModels.size(); i++) {
            boolean isClicked = sharedPreferences.getBoolean("clicked_" + i, false);
            float alphaValue = sharedPreferences.getFloat("alpha_" + i, 1.0f);

            cardModels.get(i).setClicked(isClicked);
            cardModels.get(i).setAlphaValue(alphaValue);
        }
    }


    @Override
    public void onPause() {
        super.onPause();
        saveClickedStates(); // Save clicked states when the fragment is paused
    }

    @Override
    public void onResume() {
        super.onResume();
        loadClickedStates(); // Load clicked states when the fragment is resumed
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