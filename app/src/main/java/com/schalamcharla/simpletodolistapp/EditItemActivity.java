package com.schalamcharla.simpletodolistapp;

import android.widget.Toast;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import com.schalamcharla.simpletodolistapp.util.EditTaskAdapter;
import com.schalamcharla.simpletodolistapp.util.Item;
import com.schalamcharla.simpletodolistapp.util.ViewTask;

public class EditItemActivity extends AppCompatActivity {
    private static final String TAG = "EditItem_LOG";
    //private int position = 0;
    private long itemID;

    private ArrayList<ViewTask> taskDetails;
    private EditTaskAdapter taskAdapter;
    private ListView taskViewList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i(TAG, "onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_item);

        itemID = getIntent().getLongExtra("id", 0);
        Log.i(TAG, "OnCreate - ID: " + itemID);

        taskDetails = new ArrayList<>();
        taskViewList = (ListView) findViewById(R.id.lvEditTask);
        taskAdapter = new EditTaskAdapter(this, taskDetails);
        taskViewList.setAdapter(taskAdapter);
        loadUI();
    }

    private void loadUI() {
        //clear the list.
        taskDetails.clear();
        Item specificItem = Item.findById(Item.class, itemID);
        Log.i(TAG, "test id: " + itemID);
        ViewTask vt = new ViewTask(
                "Task title: ",
                specificItem.getName());

        Log.i(TAG, "Id: " + specificItem.getID() + " Name: " + specificItem.getName());

        taskDetails.add(vt);
        Log.i(TAG, "taskDetails size: " + taskDetails.size());
        Log.i(TAG, "load UI - Label: " + taskDetails.get(0).getLabel());
        Log.i(TAG, "load UI - value: " + taskDetails.get(0).getValue());

        //TBD: more fields
        /*
        // priority field
        viewTask = new ViewTask(
                "Priority: ",
                specificItem.getPriority());
        taskDetails.add(viewTask);

        // Status Field
        viewTask = new ViewTask(
                "Status: ",
                specificItem.getStatus());
        taskDetails.add(viewTask);
         */

        taskAdapter.notifyDataSetChanged();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.save_item, menu);
        return true;
    }

    public void saveItem() {
        ViewTask v;
        Log.i(TAG, "onSaveItem");

        Intent intent = new Intent();
        Item newItem = Item.findById(Item.class, itemID);

        //read the edited content from views
        EditText etTaskTitle = (EditText) findViewById(R.id.etEditTaskValue);
        String taskTitle = etTaskTitle.getText().toString();

        //following check helps
        //1. avoids saving the null data
        //2. keeps the user stay on this activity.
        if (taskTitle != null && !taskTitle.isEmpty()) {
            newItem.setName(taskTitle);
            newItem.save();

            Toast.makeText(EditItemActivity.this,
                    "Task has been updated successfully",
                    Toast.LENGTH_SHORT).show();

            setResult(RESULT_OK, intent);
            finish();
        }
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.action_save_task:
                saveItem();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
