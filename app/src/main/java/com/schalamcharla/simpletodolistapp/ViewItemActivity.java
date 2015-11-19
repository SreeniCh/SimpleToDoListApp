package com.schalamcharla.simpletodolistapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
//import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.widget.Toast;

import com.schalamcharla.simpletodolistapp.util.Item;
//import com.schalamcharla.simpletodolistapp.util.ItemsAdapter;
import com.schalamcharla.simpletodolistapp.util.ViewTask;
import com.schalamcharla.simpletodolistapp.util.ViewTaskAdapter;

import java.util.ArrayList;
//import java.util.List;

public class ViewItemActivity extends AppCompatActivity {
    private static final String TAG = "ViewItem_Log";
    //private int position = 0;
    private long itemID;

    private ArrayList<ViewTask> taskDetails;
    private ViewTaskAdapter taskAdapter;
    private ListView taskViewList;
    private ViewTask viewTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_item);

        itemID = getIntent().getLongExtra("id", 0);
        Log.i(TAG, "OnCreate - ID: " + itemID);

        taskDetails = new ArrayList<>();
        taskViewList = (ListView) findViewById(R.id.lvViewTask);
        taskAdapter = new ViewTaskAdapter(this, taskDetails);
        taskViewList.setAdapter(taskAdapter);
        loadUI();


        //data = getIntent().getStringExtra("data");
        //Log.i(TAG, "data: " + data);

        //TextView textView = (TextView)findViewById(R.id.taskTextView);
        //textView.setText(data);
    }

    private void loadUI() {
        //clear the list.
        taskDetails.clear();
        Item specificItem = Item.findById(Item.class, itemID);
        viewTask = new ViewTask(
                "Task title: ",
                specificItem.getName());
        taskDetails.add(viewTask);

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
        getMenuInflater().inflate(R.menu.view_item_menu, menu);
        return true;
    }

    public void editItem() {
        Log.i(TAG, "onEditItem");
        Intent intent = new Intent(ViewItemActivity.this, EditItemActivity.class);
        //TextView textView = (TextView)findViewById(R.id.taskTextView);
        //String newData = textView.getText().toString();
        //intent.putExtra("pos", position);
        intent.putExtra("id", itemID);
        Log.i(TAG, "in editItem fun - id: " + itemID);
        startActivityForResult(intent, 101);
    }

    public void deleteItem() {
        Log.i(TAG, "onDeleteItem");
        Intent intent = new Intent();
        Item specificItem = Item.findById(Item.class, itemID);
        //List<Item> specificItem = Item.find(Item.class, "name=?", data);
        specificItem.delete();
        Toast.makeText(ViewItemActivity.this,
                "Task has been removed successfully",
                Toast.LENGTH_SHORT).show();
        setResult(RESULT_OK, intent);
        finish();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.action_edit_task:
                editItem();
                return true;
            case R.id.action_delete_edit:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Delete a task");
                builder.setMessage("What do you want to do?");
                final TextView txtView = new TextView(this);
                builder.setView(txtView);
                builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Log.d(TAG, txtView.getText().toString());
                        deleteItem();
                    }
                });
                builder.setNegativeButton("Cancel", null);
                builder.create().show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        Log.i(TAG, "onActivityResult");
        super.onActivityResult(requestCode, resultCode, intent);
        if (requestCode == 101) {
            if (resultCode == RESULT_OK) {
                String newData = intent.getStringExtra("data");
                int position = intent.getIntExtra("pos", -1);
                Log.i(TAG, "newData - " + newData);
                Log.i(TAG, "position - " + position);
                loadUI();
            }
        }
        //finish the current activity and go back to main Activity.
        setResult(RESULT_OK, intent);
        finish();
    }
}
