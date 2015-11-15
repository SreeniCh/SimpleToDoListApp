package com.schalamcharla.simpletodolistapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class EditItemActivity extends AppCompatActivity {
    private static final String TAG = "EditItem_LOG";
    private int position = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_item);
        position = getIntent().getIntExtra("pos", 0);
        String data = getIntent().getStringExtra("data");
        Log.i(TAG, "Position: " + position);
        Log.i(TAG, "data: " + data);

        EditText etEditItem = (EditText) findViewById(R.id.tvEditItem);
        etEditItem.setText(data);
    }

    public void onSaveItem(View v) {
        Intent intent = new Intent();
        EditText etEditItem = (EditText) findViewById(R.id.tvEditItem);
        String newData = etEditItem.getText().toString();
        if (!newData.equals("")) {
            intent.putExtra("data", newData);
            intent.putExtra("pos", position);
            setResult(RESULT_OK, intent);
            finish();
        }
    }

    /*public void onSubmit(View v) {
        // closes the activity and returns to first screen
        Log.i(TAG,"onSubmit");
        this.finish();
    }*/
}
