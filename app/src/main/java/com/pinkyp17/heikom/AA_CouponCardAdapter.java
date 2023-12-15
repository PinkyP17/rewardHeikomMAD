package com.pinkyp17.heikom;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AA_CouponCardAdapter extends RecyclerView.Adapter<AA_CouponCardAdapter.MyViewHolder> {
    Context context;
    ArrayList<CardModel> cardModels;

    public AA_CouponCardAdapter(Context context, ArrayList<CardModel> cardModels){
        this.context = context;
        this.cardModels = cardModels;
    }


    @NonNull
    @Override
    public AA_CouponCardAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //inflate layout and show the row
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.coupon_card, parent, false);

        return new AA_CouponCardAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@  NonNull AA_CouponCardAdapter.MyViewHolder holder, int position) {
        //assign value to view we created
        holder.imageView.setImageResource(cardModels.get(position).getCouponImage());
        holder.textTitle.setText(cardModels.get(position).getCouponText());
        holder.textDesc.setText(cardModels.get(position).getCouponDesc());

    }

    @Override
    public int getItemCount() {
        return cardModels.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        //grabbing view from recycle view and put the data into the layout


        ImageView imageView;
        TextView textTitle, textDesc;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.IVRewardImage);
            textTitle = itemView.findViewById(R.id.TVRewardName);
            textDesc = itemView.findViewById(R.id.TVRewardDescription);

        }
    }
}
