package com.schalamcharla.simpletodolistapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import java.util.List;

import com.schalamcharla.simpletodolistapp.util.Item;

public class EditItemActivity extends AppCompatActivity {
    private static final String TAG = "EditItem_LOG";
    //private int position = 0;
    private String oldData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i(TAG, "onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_item);
        //position = getIntent().getIntExtra("pos", 0);
        oldData = getIntent().getStringExtra("data");
        //Log.i(TAG, "Position: " + position);
        Log.i(TAG, "data: " + oldData);

        EditText etEditItem = (EditText) findViewById(R.id.tvEditItem);
        etEditItem.append(oldData);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.save_item, menu);
        return true;
    }

    public void saveItem() {


        Log.i(TAG, "onSaveItem");
        Intent intent = new Intent();
        EditText etEditItem = (EditText) findViewById(R.id.tvEditItem);
        String newData = etEditItem.getText().toString();
        Log.i(TAG, "onSaveItem, data: " + newData);
        List<Item> specificItem = Item.find(Item.class, "name=?", oldData);
        specificItem.get(0).setName(newData);
        specificItem.get(0).save();
        intent.putExtra("data", newData);
        setResult(RESULT_OK, intent);
        finish();
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
