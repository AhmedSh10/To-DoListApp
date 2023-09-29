package com.example.to_dolistapp;

import android.app.Activity;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.to_dolistapp.model.TodoListModel;
import com.example.to_dolistapp.utils.DBHelper;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Date;

public class AddNewTask extends BottomSheetDialogFragment {

    public static final String TAG = "AddNewTask";

    public static AddNewTask getInstance() {
        return new AddNewTask();
    }

    private TextInputEditText editText;
    private Button saveButton;
    private DBHelper db;
    private boolean isUpdate;
    private int taskId;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.add_task_layout, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        editText = view.findViewById(R.id.editTextTask);
        saveButton = view.findViewById(R.id.buttonAddTask);

        db = new DBHelper(requireActivity());
        isUpdate = false;

        Bundle bundle = getArguments();
        if (bundle != null) {
            isUpdate = true;
            taskId = bundle.getInt("id");
            String task = bundle.getString("task");

            editText.setText(task);

            if (task != null && !task.isEmpty()) {
                saveButton.setEnabled(false);
                saveButton.setBackgroundColor(Color.GRAY);
            }
        }

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.toString().isEmpty()) {
                    saveButton.setEnabled(false);
                    saveButton.setBackgroundColor(Color.GRAY);
                } else {
                    saveButton.setEnabled(true);
                    saveButton.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                    saveButton.setTextColor(Color.WHITE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = editText.getText().toString();

                if (isUpdate) {
                    db.updateTask(taskId, text);
                } else {
                    TodoListModel item = new TodoListModel();
                    item.setTask(text);
                    item.setStatus(0);
                    item.setTimestamp(new Date().getTime()); // Set the current timestamp
                    db.insertTask(item);
                }
                dismiss();
            }
        });
    }

    @Override
    public void onDismiss(@NonNull DialogInterface dialog) {
        super.onDismiss(dialog);

        Activity activity = getActivity();
        if (activity instanceof OnDialogCloseListener) {
            ((OnDialogCloseListener) activity).onDialogClose(dialog);
        }
    }

    public static AddNewTask newInstance(int taskId, String taskText) {
        AddNewTask fragment = new AddNewTask();
        Bundle bundle = new Bundle();
        bundle.putInt("id", taskId);
        bundle.putString("task", taskText);
        fragment.setArguments(bundle);
        return fragment;
    }
}
