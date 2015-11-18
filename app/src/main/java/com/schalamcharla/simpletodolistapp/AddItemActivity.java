package com.schalamcharla.simpletodolistapp;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import com.schalamcharla.simpletodolistapp.util.Item;

public class AddItemActivity extends AppCompatActivity {
    private static final String TAG = "AddItem_LOG";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i(TAG, "onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);
        //int position = getIntent().getIntExtra("pos", 0);
        //String data = getIntent().getStringExtra("data");

        EditText etEditItem = (EditText) findViewById(R.id.tvEditItem2);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.save_item, menu);
        return true;
    }

    public void saveItem() {
        Log.i(TAG, "onSaveItem");
        Intent intent = new Intent();
        EditText etEditItem = (EditText) findViewById(R.id.tvEditItem2);
        String newData = etEditItem.getText().toString();
        Item item = new Item(newData);
        item.save();
        Log.i(TAG, newData);
        //TBD: write to DB
        if (!newData.equals("")) {
            intent.putExtra("data", newData);
            //intent.putExtra("pos", 0);
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
