package com.schalamcharla.simpletodolistapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;
import android.app.AlertDialog;
import android.content.DialogInterface;

import com.schalamcharla.simpletodolistapp.util.Item;

import java.util.List;

public class ViewItemActivity extends AppCompatActivity {
    private static final String TAG = "ViewItem_Log";
    //private int position = 0;
    private String data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_item);

        //position = getIntent().getIntExtra("pos", 0);
        data = getIntent().getStringExtra("data");
        //Log.i(TAG, "Position: " + position);
        Log.i(TAG, "data: " + data);

        TextView textView = (TextView)findViewById(R.id.taskTextView);
        textView.setText(data);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.view_item_menu, menu);
        return true;
    }

    public void editItem() {
        Log.i(TAG, "onEditItem");
        Intent intent = new Intent(ViewItemActivity.this, EditItemActivity.class);
        TextView textView = (TextView)findViewById(R.id.taskTextView);
        String newData = textView.getText().toString();
        //intent.putExtra("pos", position);
        intent.putExtra("data", newData);
        Log.i(TAG, "in editItem fun - data: " + newData);
        startActivityForResult(intent, 101);
    }

    public void deleteItem() {
        Log.i(TAG, "onDeleteItem");
        Intent intent = new Intent();
        List<Item> specificItem = Item.find(Item.class, "name=?", data);
        specificItem.get(0).delete();
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
                TextView textView = (TextView)findViewById(R.id.taskTextView);
                textView.setText(newData);
            }
        }
        //finish the current activity and go back to main Activity.
        setResult(RESULT_OK, intent);
        finish();
    }
}
