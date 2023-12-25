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

public class AA_TaskAdapter extends RecyclerView.Adapter<AA_TaskAdapter.MyViewHolder> {
    Context context;
    ArrayList<TaskModel> taskModels;

    public AA_TaskAdapter(Context context, ArrayList<TaskModel> taskModels){
        this.context = context;
        this.taskModels = taskModels;
    }

    @NonNull
    @Override
    public AA_TaskAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //inflate layout and show the row
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.activity_task_card, parent, false);
        return new AA_TaskAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AA_TaskAdapter.MyViewHolder holder, int position) {
        //assign value to view that we have created
        holder.textView.setText(taskModels.get(position).getTaskText());
        holder.imageView.setImageResource(taskModels.get(position).getImageFirst());
        holder.imageView2.setImageResource(taskModels.get(position).getImageSecond());

    }

    @Override
    public int getItemCount() {
        return taskModels.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        //grabbing view from recycler view and put data into layout

        ImageView imageView, imageView2;
        TextView textView;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.taskIcon1);
            imageView2 = itemView.findViewById(R.id.taskIcon2);
            textView = itemView.findViewById(R.id.TVTaskView);
        }
    }
}
