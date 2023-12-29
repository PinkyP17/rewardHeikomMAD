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

    String userId;

    private PointAdditionListener pointAdditionListener;



    public AA_TaskAdapter(Context context, ArrayList<TaskModel> taskModels, String userId){
        this.context = context;
        this.taskModels = taskModels;
        this.userId = userId;
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
        TaskModel currentTask = taskModels.get(position);

        holder.textView.setText(currentTask.getTaskText());
        holder.imageView.setImageResource(currentTask.getImageFirst());
        holder.imageView2.setImageResource(currentTask.getImageSecond());
        holder.bindIntValue(currentTask.getPointsVal());

        boolean isClicked = currentTask.isClicked(context);
        holder.itemView.setAlpha(isClicked ? 0.5f : 1.0f);
        holder.itemView.setClickable(!isClicked);

        holder.itemView.setOnClickListener(v -> {
            if (!isClicked) {
                currentTask.setClicked(context, true);

                int pointsToAdd = currentTask.getPointsVal();

                if (pointAdditionListener != null) {
                    pointAdditionListener.onAddPointClicked(position, pointsToAdd);
                }

                notifyDataSetChanged();
            }
        });
    }




    @Override
    public int getItemCount() {
        return taskModels.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        //grabbing view from recycler view and put data into layout

        ImageView imageView, imageView2;
        TextView textView, textView2;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.taskIcon1);
            imageView2 = itemView.findViewById(R.id.taskIcon2);
            textView = itemView.findViewById(R.id.TVTaskView);
            textView2 = itemView.findViewById(R.id.taskPoints);
        }

        public void bindIntValue(int value){
            textView2.setText(String.valueOf(value));
        }
    }
    public interface PointAdditionListener {
        void onAddPointClicked(int position, int pointsToAdd);
    }

    public void setPointAdditionListener(PointAdditionListener listener) {
        this.pointAdditionListener = listener;
    }
}
