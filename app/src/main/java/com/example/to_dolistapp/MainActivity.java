package com.example.to_dolistapp;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.to_dolistapp.Adapter.ModelAdapter;
import com.example.to_dolistapp.model.TodoListModel;
import com.example.to_dolistapp.utils.DBHelper;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity implements OnDialogCloseListener {

    private FloatingActionButton fab;
    private DBHelper db;
    private ModelAdapter adapter;
    private TextView dateTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        setupRecyclerView();
        setupFabClickListener();
        displayCurrentDate();
    }

    private void initViews() {
        fab = findViewById(R.id.fab_btn);
        db = new DBHelper(this);
        adapter = new ModelAdapter(MainActivity.this, db);
        dateTextView = findViewById(R.id.dateTextView);
    }

    private void setupRecyclerView() {
        RecyclerView recyclerView = findViewById(R.id.rv_main);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setAdapter(adapter);
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new ItemTouchHelperClass(adapter));
        itemTouchHelper.attachToRecyclerView(recyclerView);
        refreshTaskList();
    }

    private void setupFabClickListener() {
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddNewTask.getInstance().show(getSupportFragmentManager(), AddNewTask.TAG);
            }
        });
    }

    private void displayCurrentDate() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("EEEE, MMMM d");
        String currentDate = dateFormat.format(new Date());
        dateTextView.setText(currentDate);
    }

    private void refreshTaskList() {
        List<TodoListModel> tasksList = db.getAllTasks();
        Collections.reverse(tasksList);
        adapter.setTasks(tasksList);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onDialogClose(DialogInterface dialogInterface) {
        refreshTaskList();
    }
}
