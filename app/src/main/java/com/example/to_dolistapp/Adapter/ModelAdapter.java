package com.example.to_dolistapp.Adapter;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.to_dolistapp.AddNewTask;
import com.example.to_dolistapp.MainActivity;
import com.example.to_dolistapp.R;
import com.example.to_dolistapp.model.TodoListModel;
import com.example.to_dolistapp.utils.DBHelper;

import java.util.List;

public class ModelAdapter extends RecyclerView.Adapter<ModelAdapter.ModelViewHolder> {

    private List<TodoListModel> tasks;
    private MainActivity activity;
    private DBHelper db;

    public ModelAdapter(MainActivity activity, DBHelper db) {
        this.activity = activity;
        this.db = db;
    }

    @NonNull
    @Override
    public ModelViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ModelViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.task_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ModelViewHolder holder, int position) {
        final TodoListModel modelItem = tasks.get(position);

        holder.box.setText(modelItem.getTask());
        holder.box.setChecked(toBoolean(modelItem.getStatus()));

        if (toBoolean(modelItem.getStatus())) {
            holder.line.setVisibility(View.VISIBLE);
        } else {
            holder.line.setVisibility(View.GONE);
        }

        holder.box.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    db.updateStatus(modelItem.getId(), 1);
                    holder.line.setVisibility(View.VISIBLE);
                } else {
                    db.updateStatus(modelItem.getId(), 0);
                    holder.line.setVisibility(View.GONE);
                }
            }
        });
    }

    public boolean toBoolean(int num) {
        return num != 0;
    }

    public Context getContext() {
        return activity;
    }

    public void setTasks(List<TodoListModel> tasks) {
        this.tasks = tasks;
        notifyDataSetChanged();
    }

    public void deleteTask(int position) {
        TodoListModel item = tasks.get(position);
        db.deleteTask(item.getId());
        tasks.remove(position);
        notifyItemRemoved(position);
    }

    public void editTask(int position) {
        TodoListModel item = tasks.get(position);

        Bundle bundle = new Bundle();
        bundle.putInt("id", item.getId());
        bundle.putString("task", item.getTask());

        AddNewTask task = new AddNewTask();
        task.setArguments(bundle);
        task.show(activity.getSupportFragmentManager(), task.getTag());
    }

    @Override
    public int getItemCount() {
        return tasks.size();
    }

    public static class ModelViewHolder extends RecyclerView.ViewHolder {
        CheckBox box;
        TextView line;

        public ModelViewHolder(@NonNull View itemView) {
            super(itemView);
            box = itemView.findViewById(R.id.checkbox_task);
            line = itemView.findViewById(R.id.line_view);
        }
    }

}
